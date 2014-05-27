package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;

/**
 * Created by T on 27/05/2014.
 */
public interface CurrentSlideChangedListener {
    public void currentSlideChanged(SlideshowController source,
                                    Slide oldSlide,
                                    Slide newSlide);
}
