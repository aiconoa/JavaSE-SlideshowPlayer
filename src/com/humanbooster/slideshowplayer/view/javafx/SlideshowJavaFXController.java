package com.humanbooster.slideshowplayer.view.javafx;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.controller.SlideshowControllerStatusException;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
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

    private SlideshowController slideshowController;

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
            if(! slideshowController.isCurrentSlideLastSlide()) {
                slideshowController.nextSlide();
            }
        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    @FXML
    public void handlePlayPause(ActionEvent e) {
        try {
            switch(slideshowController.getStatus()) {
                case PLAYING:
                    playPauseButton.setText("Play"); // TODO pour une meilleure synchro, réagir à un SlideshowController status changed event...
                    slideshowController.pause();
                    break;
                case PAUSED:
                case STOPPED:
                    playPauseButton.setText("Pause");
                    stage.setFullScreen(true);
                    slideshowController.play();
                    break;
            }
        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    @FXML
    public void handleStop(ActionEvent e ) {
        try {
            slideshowController.stop();
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

    // listenier to Stage fullscreen mode
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            borderPane.setTop(null);
        } else {
            borderPane.setTop(menuBar);
        }
    }

    public SlideshowController getSlideshowController() {
        return slideshowController;
    }

    public void setSlideshowController(SlideshowController slideshowController) {
        if(this.slideshowController != null) {
            this.slideshowController.removeCurrentSlideChangedListener(this);
        }
        this.slideshowController = slideshowController;
        if(slideshowController.getSlideshow() != null && slideshowController.getSlideshow().getNumberOfSlides() != 0) {
            try {
                slideView.setSlide(slideshowController.getCurrentSlide());
            } catch (Exception e) {
                popupExceptionDialog(e);
            }
        }

        if(this.slideshowController != null) {
            this.slideshowController.addCurrentSlideChangedListener(this);
        }

    }

    @Override
    public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
        Platform.runLater(() -> {
            slideView.setSlide(newSlide);
            System.out.println(
                    "##### Current slide index: " + source.getCurrentSlideIndex() + "#####"
                    + "\n"
                    + newSlide.toString());
        });
    }

    private void popupExceptionDialog(Exception e) {
        //TODO poper un dialogue
        System.err.println(e.getMessage());
        e.printStackTrace();
    }


}
