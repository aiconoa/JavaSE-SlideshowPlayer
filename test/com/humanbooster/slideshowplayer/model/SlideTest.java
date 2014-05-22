package com.humanbooster.slideshowplayer.model;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SlideTest {

    // TODO écrire un test qui garantisse qu'il n'y a pas de SlideElement dans un new Slide()

    @Test
    public void oneSlideElementTest() throws Exception {
        // given
        Slide slide = new Slide();
        SlideElement se = new SlideElement();
        // when
        slide.addSlideElement(se);
        // test
        Set<SlideElement> slideElements = slide.getSlideElements();

        assertTrue("Il doit y a voir un element dans la collection", slideElements.size() == 1);
        assertTrue("L'élément dans la collection doit être celui que l'on a ajouté", slideElements.contains(se));
    }
}