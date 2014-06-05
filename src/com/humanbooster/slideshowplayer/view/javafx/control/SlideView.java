package com.humanbooster.slideshowplayer.view.javafx.control;

import com.humanbooster.slideshowplayer.model.ImageSlideElement;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.SlideElement;
import com.humanbooster.slideshowplayer.model.TextSlideElement;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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

    @Override protected void layoutChildren() {
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

        if(slide == null) {
            drawNoSlide(gc, width, height);
            return;
        }

        for (SlideElement element : slide.getSlideElements()) {
            if (element instanceof TextSlideElement) {
                TextSlideElement ts = (TextSlideElement) element;
                gc.fillText(ts.getContent(), width * ts.getX(), width * ts.getY());
            } else if(element instanceof ImageSlideElement) {
                ImageSlideElement is = (ImageSlideElement) element;
                String url = String.format("http://placehold.it/%.0fx%.0f", width * is.getWidth(), height * is.getHeight());
                Image image = new Image(url);
                gc.drawImage(image, width * is.getX(), height * is.getY());
                System.out.println(url);
                System.out.println(width * is.getX());
                System.out.println(height * is.getY());
            }
        }
    }

    private void drawNoSlide(GraphicsContext gc, double width, double height) {
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
        draw();
    }
}
