package com.humanbooster.slideshowplayer.view.console;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowEngine;
import com.humanbooster.slideshowplayer.model.Slide;

/**
 * Created by T on 28/05/2014.
 */
public class ConsoleView implements CurrentSlideChangedListener {
    @Override
    public void currentSlideChanged(SlideshowEngine source, Slide oldSlide, Slide newSlide) {
        System.out.println(newSlide);
    }
}
