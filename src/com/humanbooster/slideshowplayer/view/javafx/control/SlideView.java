package com.humanbooster.slideshowplayer.view.javafx.control;

import com.humanbooster.slideshowplayer.model.ImageSlideElement;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.SlideElement;
import com.humanbooster.slideshowplayer.model.TextSlideElement;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Inspired from http://fxexperience.com/2014/05/resizable-grid-using-canvas/
 */
public class SlideView extends Pane {
    private Canvas canvas;
    private Slide slide;

    public SlideView() {
        canvas = new Canvas();
        getChildren().add(canvas);
    }

    @Override
    protected void layoutChildren() {
        final int top = (int)snappedTopInset();
        final int right = (int)snappedRightInset();
        final int bottom = (int)snappedBottomInset();
        final int left = (int)snappedLeftInset();
        final int w = (int)getWidth() - left - right;
        final int h = (int)getHeight() - top - bottom;
        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            draw();
        }
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, width, height);

        if(slide == null) {
            drawNoSlide(gc, width, height);
            return;
        }

        for (SlideElement element : slide.getSlideElements()) {
            if (element instanceof TextSlideElement) {
                TextSlideElement ts = (TextSlideElement) element;

                gc.setFont(Font.font("Helvetica", 20));
                gc.setFill(Color.WHITE);

                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.fillText(ts.getContent(), width / 2 , height * ts.getY() + (height * ts.getHeight()) / 2);

            } else if(element instanceof ImageSlideElement) {
                ImageSlideElement is = (ImageSlideElement) element;
                Image image = new Image(is.getContent());

                gc.drawImage(image, width * is.getX(), height * is.getY(), width * is.getWidth(), height * is.getHeight());
            }
        }
    }

    private void drawNoSlide(GraphicsContext gc, double width, double height) {
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
    }

    public void setSlide(Slide slide) {
        // ici on plonge dans le noir
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), this);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();

        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SlideView.this.slide = slide;
                draw();

                // ici on rallume la lumière
                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), SlideView.this);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            }
        });
    }
}
