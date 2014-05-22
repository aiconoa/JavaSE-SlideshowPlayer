package com.humanbooster.slideshowplayer.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlideshowTest {

    @Test
    public void oneSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        // when
        Slide s = new Slide();
        ss.addSlide(s);
        // test
        assertTrue("Il doit y avoir un slide dans le slideshow", ss.getNumberOfSlides() == 1);
        assertTrue("L'élément présent doit être celui que l'on a inséré",
                ss.getSlideAtIndex(0).equals(s));
    }

    @Test
    public void twoSlidesTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        // when
        Slide s0 = new Slide();
        ss.addSlide(s0);
        Slide s1 = new Slide();
        ss.addSlide(s1);
        // then
        assertTrue("Il doit y avoir deux slides dans le slideshow", ss.getNumberOfSlides() == 2);
        assertTrue("L'élément présent doit être celui que l'on a inséré",
                ss.getSlideAtIndex(0).equals(s0));
        assertTrue("L'élément présent doit être celui que l'on a inséré",
                ss.getSlideAtIndex(1).equals(s1));
    }

    @Test(expected = SlideshowIndexOutOfBoundsException.class)
    public void getSlideAtIndexWithNegativeIndexTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        //when
        ss.getSlideAtIndex(-1);
    }

    @Test(expected = SlideshowIndexOutOfBoundsException.class)
    public void getSlideAtIndexWithTooBigIndexTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        //when
        ss.getSlideAtIndex(ss.getNumberOfSlides());
    }

    @Test(expected = NullPointerException.class)
    public void addSlideWithNullSlideTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        //when
        ss.addSlide(null);
    }
}