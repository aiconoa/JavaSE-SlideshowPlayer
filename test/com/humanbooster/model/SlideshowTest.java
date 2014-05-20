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
        Slide s0 = new Slide();
        ss.addSlide(s0);
        Slide s1 = new Slide();
        ss.addSlide(s1);
        // test
        assertTrue("Il doit y avoir deux slides dans le slideshow", ss.getNumberOfSlides() == 2);
        assertTrue("L'élément présent doit être celui que l'on a inséré",
                ss.getSlideAtIndex(0).equals(s0));
        assertTrue("L'élément présent doit être celui que l'on a inséré",
                ss.getSlideAtIndex(1).equals(s1));
    }

    @Test
    public void elevenSlidesTest()  throws Exception {
        // given
        Slideshow ss = new Slideshow();
        // when
        Slide[] slides = new Slide[Slideshow.CAPACITY * 5];
        for (int i = 0; i < Slideshow.CAPACITY * 5; i++) {
            slides[i] = new Slide();
            ss.addSlide(slides[i]);
        }
        // test
        assertTrue("Il doit y avoir 11 slides dans le slideshow",
                ss.getNumberOfSlides() == Slideshow.CAPACITY * 5 );
        assertTrue("L'élément à la position 0 doit être celui que l'on a inséré",
                ss.getSlideAtIndex(0).equals(slides[0]));
        assertTrue("L'élément à la position 5 doit être celui que l'on a inséré",
                ss.getSlideAtIndex(5).equals(slides[5]));
        assertTrue("L'élément à la position Slideshow.CAPACITY doit être celui que l'on a inséré",
                ss.getSlideAtIndex(Slideshow.CAPACITY).equals(slides[Slideshow.CAPACITY]));
        assertTrue("L'élément à la position (Slideshow.CAPACITY * 5 - 1) doit être celui que l'on a inséré",
                ss.getSlideAtIndex(Slideshow.CAPACITY * 5 - 1).equals(slides[Slideshow.CAPACITY * 5 - 1]));
    }


    // TODO ajouter un test ou le slideshow a deja des slides au départ
    // TODO ajouter un test qui dépasse la capacité initiale du tableau
}