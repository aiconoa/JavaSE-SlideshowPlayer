package com.humanbooster.slideshowplayer.view;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.model.Slide;

/**
 * Created by T on 28/05/2014.
 */
public class ConsoleView implements CurrentSlideChangedListener {
    @Override
    public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
        System.out.println(newSlide);
    }
}
