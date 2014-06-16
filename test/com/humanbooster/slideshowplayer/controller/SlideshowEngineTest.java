package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class SlideshowEngineTest {

    //TODO tester nextSlide en positionnant le slide courant au milieu
    //TODO tester nextSlide en positionnant le slide à la fin au milieu

    //TODO tester nextSlide avec un slideshow vide
    //TODO tester nextSlide si trou dans l'ordre du slideshow
    //TODO tester "resume", cad pause puis play


    @Test
    public void nextSlideTest() throws Exception {
        //given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        ss.addSlide(s1);
        ss.addSlide(s2);

        SlideshowEngine sc = new SlideshowEngine();
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
        SlideshowEngine sc = new SlideshowEngine();
        //when
        sc.nextSlide();
    }

    @Test(expected = Exception.class)
    public void nextSlideIfSlideshowIsEmptyTest() throws Exception {
        //given
        SlideshowEngine sc = new SlideshowEngine();
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

        SlideshowEngine sc = new SlideshowEngine();
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

        SlideshowEngine sc = new SlideshowEngine();
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
        SlideshowEngine sc = new SlideshowEngine();
        //when
        sc.getCurrentSlide();
    }

    @Test(expected = Exception.class)
    public void getCurrentSlideIfSlideshowIsEmptyTest() throws Exception {
        //given
        SlideshowEngine sc = new SlideshowEngine();
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

        SlideshowEngine sc = new SlideshowEngine();
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

        SlideshowEngine sc = new SlideshowEngine();

        int transitionTimeBetweenSlides = 100; //ms
        sc.setTransitionTimeBetweenSlides(transitionTimeBetweenSlides);
        sc.setSlideshow(ss);

        AtomicInteger totalSlideChanged = new AtomicInteger(0);

        CurrentSlideChangedListener listener = new CurrentSlideChangedListener() {
            @Override
            public void currentSlideChanged(SlideshowEngine source, Slide oldSlide, Slide newSlide) {
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

        assertEquals("Le slideshow controller doit être en status PLAYING", SlideshowEngine.STATUS.PLAYING, sc.getStatus());

        // on estime que le diaporama doit avoir défilé après un temps egal à
        // nb_slides * frequence de changement du slide + marge d'erreur
        int deltaInMS = 1500;
        Thread.sleep(numberOfSlides * sc.getTransitionTimeBetweenSlides() + deltaInMS);

        // quand le diaporama est censé avoir fini de défiler, on vérifie que l'on a bien parcouru toutes
        // les slides
        assertEquals("on doit avoir traversé " + numberOfSlides + " slides", numberOfSlides, totalSlideChanged.get() + 1);
    }

    @Test
    public void pause() throws Exception {
        //given
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        int numberOfSlides = 10;
        for (int i = 0; i < numberOfSlides; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowEngine sc = new SlideshowEngine();

        int transitionTimeBetweenSlides = 100; //ms
        sc.setTransitionTimeBetweenSlides(transitionTimeBetweenSlides);
        sc.setSlideshow(ss);

        sc.play();

        Thread.sleep(transitionTimeBetweenSlides * 5); // Optionnel: laisser le test se dérouler un peu
        sc.pause();
        Slide slideAfterPause = sc.getCurrentSlide();
        Thread.sleep(1000);
        Slide slideAfterPauseAndSleep = sc.getCurrentSlide();

        assertEquals("Le slideshow controller doit être en status PAUSED",
                SlideshowEngine.STATUS.PAUSED,
                sc.getStatus());
        assertEquals("Le slideshow ne doit plus defiler apres la pause", slideAfterPause, slideAfterPauseAndSleep);
    }

    @Test(expected = SlideshowControllerStatusException.class)
    public void pauseWithoutPlay() throws Exception {
        //given
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        int numberOfSlides = 10;
        for (int i = 0; i < numberOfSlides; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowEngine sc = new SlideshowEngine();

        int transitionTimeBetweenSlides = 100; //ms
        sc.setTransitionTimeBetweenSlides(transitionTimeBetweenSlides);
        sc.setSlideshow(ss);

        sc.pause();
    }

    @Test
    public void stop() throws Exception {
        //given
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        int numberOfSlides = 10;
        for (int i = 0; i < numberOfSlides; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowEngine sc = new SlideshowEngine();

        int transitionTimeBetweenSlides = 100; //ms
        sc.setTransitionTimeBetweenSlides(transitionTimeBetweenSlides);
        sc.setSlideshow(ss);

        sc.play();

        Thread.sleep(transitionTimeBetweenSlides * 5); // Optionnel: laisser le test se dérouler un peu
        sc.stop();
        Slide slideAfterStop = sc.getCurrentSlide();
        Thread.sleep(1000);
        Slide slideAfterStopAndSleep = sc.getCurrentSlide();

        assertEquals("Le slideshow controller doit être en status STOPPED",
                SlideshowEngine.STATUS.STOPPED,
                sc.getStatus());
        assertEquals("Le slideshow ne doit plus defiler apres stop", slideAfterStop, slideAfterStopAndSleep);
        assertEquals("Apres stop on doit revenir au premier slide", slides.get(0), sc.getCurrentSlide());
    }


    @Test
    public void isCurrentSlideLastSlide() throws Exception {
        ArrayList<Slide> slides = new ArrayList<>();
        Slideshow ss = new Slideshow();
        int numberOfSlides = 10;
        for (int i = 0; i < numberOfSlides; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }

        SlideshowEngine sc = new SlideshowEngine();
        sc.setSlideshow(ss);

        for (int i = 1; i < numberOfSlides; i++) {
            assertFalse("Slide " + i +  " out of 10 should not be the last slide", sc.isCurrentSlideLastSlide());
            sc.nextSlide();
        }

        assertTrue("Slide " + numberOfSlides + "out of 10 is the last slide", sc.isCurrentSlideLastSlide());

    }
}