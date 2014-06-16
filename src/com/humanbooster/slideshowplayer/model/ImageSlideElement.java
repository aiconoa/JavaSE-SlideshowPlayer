package com.humanbooster.slideshowplayer.model;

import java.awt.*;

/**
 * Un élément de {@link com.humanbooster.slideshowplayer.model.Slide}
 * dont le contenu est de type Image
 */
public class ImageSlideElement extends SlideElementWithContentBase {
    public ImageSlideElement(double x, double y, double width, double height, int zindex) {
        super(x, y, width, height, zindex);
    }
}
