package com.humanbooster.slideshowplayer.view.javafx;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowEngine;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.view.javafx.control.SlideView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SlideshowJavaFXController implements CurrentSlideChangedListener, ChangeListener<Boolean>, Initializable {

    @FXML
    private Button playPauseButton;

    @FXML
    private StackPane slidePane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuBar menuBar;

    private Stage stage;

    private SlideView slideView;

    private SlideshowEngine slideshowEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideView = new SlideView();
        slidePane.getChildren().add(slideView);
    }

    @FXML
    public void handlePrevious(ActionEvent e) {
        //slideshowController.previousSlide();
    }

    @FXML
    public void handleNext(ActionEvent e) {

        try {
            if(! slideshowEngine.isCurrentSlideLastSlide()) {
                slideshowEngine.nextSlide();
            }
        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    @FXML
    public void handlePlayPause(ActionEvent e) {
        try {
            switch(slideshowEngine.getStatus()) {
                case PLAYING:
                    playPauseButton.setText("Play"); // TODO pour une meilleure synchro, réagir à un SlideshowController status changed event...
                    slideshowEngine.pause();
                    break;
                case PAUSED:
                case STOPPED:
                    playPauseButton.setText("Pause");
                    stage.setFullScreen(true);
                    slideshowEngine.play();
                    break;
            }
        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    @FXML
    public void handleStop(ActionEvent e ) {
        try {
            slideshowEngine.stop();
            playPauseButton.setText("Play");
            stage.setFullScreen(false);

        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        if(this.stage != null) {
            stage.fullScreenProperty().removeListener(this);
        }

        this.stage = stage;

        if(this.stage != null) {
            stage.fullScreenProperty().addListener(this);
        }
    }

    // listener to Stage fullscreen mode
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            borderPane.setTop(null);
        } else {
            borderPane.setTop(menuBar);
        }
    }

    public SlideshowEngine getSlideshowEngine() {
        return slideshowEngine;
    }

    /**
     *
     * @param slideshowEngine
     * @throws java.lang.NullPointerException is slideshowController is null
     */
    public void setSlideshowEngine(SlideshowEngine slideshowEngine) {
        Objects.requireNonNull(slideshowEngine);

        if(this.slideshowEngine != null) {
            this.slideshowEngine.removeCurrentSlideChangedListener(this);
        }
        this.slideshowEngine = slideshowEngine;

        if(slideshowEngine.getSlideshow() != null && slideshowEngine.getSlideshow().getNumberOfSlides() > 0) {
            try {
                slideView.setSlide(slideshowEngine.getCurrentSlide()); // TODO faire évoluer getCurrentSlide() pour renvoyer Optional<Slide> ?
            } catch (Exception e) {
                popupExceptionDialog(e);
            }
        }

        if(this.slideshowEngine != null) {
            this.slideshowEngine.addCurrentSlideChangedListener(this);
        }

    }

    @Override
    public void currentSlideChanged(SlideshowEngine source, Slide oldSlide, Slide newSlide) {
        Platform.runLater(() -> {
                slideView.setSlide(newSlide);
        });
    }

    private void popupExceptionDialog(Exception e) {
        //TODO poper un dialogue
        System.err.println(e.getMessage());
        e.printStackTrace();
    }


}
