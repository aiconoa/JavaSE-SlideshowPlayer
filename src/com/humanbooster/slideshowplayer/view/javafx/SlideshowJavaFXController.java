package com.humanbooster.slideshowplayer.view.javafx;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.controller.SlideshowControllerStatusException;
import com.humanbooster.slideshowplayer.model.Slide;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class SlideshowJavaFXController implements CurrentSlideChangedListener {

    @FXML
    private Button playPauseButton;

    @FXML
    private TextArea slideDebugArea;

    private SlideshowController slideshowController;

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
                    playPauseButton.setText("play"); // TODO pour une meilleure synchro, réagir à un SlideshowController status changed event...
                    slideshowController.pause();
                    break;
                case PAUSED:
                case STOPPED:
                    playPauseButton.setText("pause");
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
        } catch (Exception ex) {
            popupExceptionDialog(ex);
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

        if(this.slideshowController != null) {
            this.slideshowController.addCurrentSlideChangedListener(this);
        }

    }

    @Override
    public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
        Platform.runLater(() -> {
            slideDebugArea.setText(slideDebugArea.getText()
                    + "\n"
                    + "##### Current slide index: " + source.getCurrentSlideIndex() + "#####"
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
