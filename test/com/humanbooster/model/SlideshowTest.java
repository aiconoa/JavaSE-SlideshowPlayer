package com.humanbooster.model;

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
        ss.addSlide(new Slide());
        ss.addSlide(new Slide());
        // test
        assertTrue("Il doit y avoir deux slides dans le slideshow", ss.getNumberOfSlides() == 2);
    }

    @Test
    public void elevenSlidesTest()  throws Exception {
        // given
        Slideshow ss = new Slideshow();
        // when
        for (int i = 0; i < 11; i++) {
            ss.addSlide(new Slide());
        }
        // test
        assertTrue("Il doit y avoir 11 slides dans le slideshow", ss.getNumberOfSlides() == 11);
    }


    // TODO ajouter un test ou le slideshow a deja des slides au départ
    // TODO ajouter un test qui dépasse la capacité initiale du tableau
}