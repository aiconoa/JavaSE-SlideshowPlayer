package com.humanbooster.slideshowplayer.model;

/**
 * Un élément de {@link com.humanbooster.slideshowplayer.model.Slide}
 * ayant un contenu de type String
 */
public class SlideElementWithContentBase extends SlideElementBase {
    private String content;

    public SlideElementWithContentBase(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
