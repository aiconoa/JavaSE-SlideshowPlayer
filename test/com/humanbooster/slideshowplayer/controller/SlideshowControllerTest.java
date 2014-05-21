package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlideshowControllerTest {

    @Test
    public void nextSlideTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        ss.addSlide(s1);
        ss.addSlide(s2);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        //when
        Slide nextSlide = sc.nextSlide();
        //test
        assertTrue("Le slide suivant est bien le slide numéro 2",
                    nextSlide.equals(s2));
    }

    @Test
    public void nextSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        //given
        //when

        //then sera fait avec Thomas
    }

    @Test
    public void nextSlideIfSlideshowIsEmptyTest() throws Exception {

    }

    @Test
    public void nextSlideIfCurrentSlideIsTheLastSlideTest() throws Exception {

    }


    //TODO tester nextSlide en positionnant le slide courant au milieu
    //TODO tester nextSlide en positionnant le slide à la fin au milieu

    //TODO tester nextSlide avec un slideshow vide
    //TODO tester nextSlide si trou dans l'ordre du slideshow
}
