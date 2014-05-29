package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Test(expected = Exception.class)
    public void nextSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        //given
        SlideshowController sc = new SlideshowController();
        //when
        sc.nextSlide();
    }

    @Test(expected = Exception.class)
    public void nextSlideIfSlideshowIsEmptyTest() throws Exception {
        //given
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(new Slideshow());
        //when
        sc.nextSlide();
    }

    @Test(expected = Exception.class)
    public void nextSlideIfCurrentSlideIsTheLastSlideAndOnlyOneSlideTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        ss.addSlide(s1);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);
        //when
        sc.nextSlide();
    }

    @Test
    public void getCurrentSlideTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        ss.addSlide(s1);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);
        //when
        Slide currentSlide = sc.getCurrentSlide();
        //test
        assertTrue("Le slide courant est bien le slide numéro 1",
                currentSlide.equals(s1));
    }

    @Test(expected = Exception.class)
    public void getCurrentSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        //given
        SlideshowController sc = new SlideshowController();
        //when
        sc.getCurrentSlide();
    }

    @Test(expected = Exception.class)
    public void getCurrentSlideIfSlideshowIsEmptyTest() throws Exception {
        //given
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(new Slideshow());
        //when
        sc.getCurrentSlide();
    }

    @Test
    public void getCurrentSlideAfterNextSlideTest() throws Exception {
        //given
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        for (int i = 0; i < 10; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        for (int i = 1; i < slides.size(); i++) {
            //when
            sc.nextSlide();
            Slide currentSlide = sc.getCurrentSlide();
            //then
            assertTrue("Slide at index " + i + " must be " + slides.get(i), currentSlide ==  slides.get(i));
        }
    }

    @Test
    public void play() throws Exception {
        //given
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        int numberOfSlides = 10;
        for (int i = 0; i < numberOfSlides; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);
        AtomicInteger totalSlideChanged = new AtomicInteger(0);

        CurrentSlideChangedListener listener = new CurrentSlideChangedListener() {
            @Override
            public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
                totalSlideChanged.incrementAndGet();
                // Then
                // vérifier que le nouveau slide est bien le slide suivant
                assertEquals("Le slide suivant attendu est " + slides.get(totalSlideChanged.get())
                            , slides.get(totalSlideChanged.get()), newSlide);

                // si on veut vérifier l'appel toutes les X secondes, conserver un timestamp de l'appel
                // précédent et vérifier si il s'est passé moins de X secondes + DeltaTemps entre l'ancien
                // appel et le nouvel appel.

            }
        };

        sc.addCurrentSlideChangedListener(listener);

        //when
        sc.play();

        // on estime que le diaporama doit avoir défilé après un temps egal à
        // nb_slides * frequence de changement du slide * marge d'erreur
        int deltaInMS = 1500;
        Thread.sleep(numberOfSlides * sc.getTransitionTimeBetweenSlides() + deltaInMS);

        // quand le diaporama est censé avoir fini de défiler, on vérifie que l'on a bien parcouru toutes
        // les slides
        assertEquals("on doit avoir traversé " + numberOfSlides + " slides", numberOfSlides, totalSlideChanged.get() + 1);
    }



    //TODO tester nextSlide en positionnant le slide courant au milieu
    //TODO tester nextSlide en positionnant le slide à la fin au milieu

    //TODO tester nextSlide avec un slideshow vide
    //TODO tester nextSlide si trou dans l'ordre du slideshow
}
