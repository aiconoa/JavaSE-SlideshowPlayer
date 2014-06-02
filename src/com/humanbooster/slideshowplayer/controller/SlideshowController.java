package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;

import java.util.*;

/**
 * Gère la logique de l'application, c'est à dire les actions en
 * réaction aux solicitations de l'utilisateur.
 * Interagit avec le modèle de données représenté par un
 * Slideshow, ses Slides , etc...
 * Lors du chargement d'un slideshow le slide courant est le premier slide du slideshow.
 */
public class SlideshowController {

    public static enum STATUS { PLAYING, PAUSED, STOPPED };

    private STATUS status = STATUS.STOPPED;

    private int transitionTimeBetweenSlides = 1000;
    private int currentSlideIndex = -1;
    private Slideshow slideshow = null;
    private List<CurrentSlideChangedListener> currentSlideChangedListeners = new ArrayList<>();

    private TimerTask currentTask;

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
        currentSlideIndex = 0;
    }

    public STATUS getStatus() {
        return status;
    }

    public int getTransitionTimeBetweenSlides() {
        return transitionTimeBetweenSlides;
    }

    public void setTransitionTimeBetweenSlides(int transitionTimeBetweenSlides) {
        //TODO what happends if we call this method while playing ?
        // should it reset the timer ?
        this.transitionTimeBetweenSlides = transitionTimeBetweenSlides;
    }

    /**
     * Retourne le {@link com.humanbooster.slideshowplayer.model.Slide} suivant.
     * <p>La position du slide courant dans le slideshow courant est incrémentée.</p>
     *
     * Notifie les {@link CurrentSlideChangedListener} du changement de slide.
     *
     * @return le slide suivant
     *
     * @throws java.lang.NullPointerException si nextSlide est appelée alors qu'il n'y a pas de slideshow de chargé.
     * @throws java.lang.Exception si nextSlide est appelée alors que le slideshow est vide.
     * @throws java.lang.Exception si nextSlide est appelée alors que le slide courant est le dernier slide
     */
    public Slide nextSlide() throws Exception {
        Objects.requireNonNull(slideshow, "Cannot call nextSlide if no slideshow has been loaded");
        requireSlideshowNotEmpty("Cannot call nextSlide if slideshow is empty");

        if(currentSlideIndex == (slideshow.getNumberOfSlides() - 1)) {
            throw new Exception("Cannot call nextSlide if the current slide is the last one");
        }

        Slide oldSlide = slideshow.getSlideAtIndex(currentSlideIndex);

        currentSlideIndex++; // incrémente l'indice du slide courant
        Slide newSlide = slideshow.getSlideAtIndex(currentSlideIndex);

        for (CurrentSlideChangedListener currentSlideChangedListener : currentSlideChangedListeners) { // prévenir les listeners que le slide courant a changé
            currentSlideChangedListener.currentSlideChanged(this, oldSlide, newSlide);
        }

        return newSlide; // retourne le slide à la position correspondante à cet index dans le slideshow
    }

    /**
     * Retourne le slide courant.
     *
     * Si getCurrentSlide est appelée alors qu'il n'y a pas de slideshow de chargé alors
     * une exception est jetée.
     *
     * Si getCurrentSlide est appelée alors qu'il n'y a pas de slideshow de chargé alors
     * une exception est jetée.
     *
     * @return le slide courant.
     */
    public Slide getCurrentSlide() throws Exception {
        if(slideshow == null) {
            //TODO jetter une exception plus spécifique
            throw new Exception("Cannot call getCurrentSlide if no slideshow has been loaded");
        }

        if(slideshow.getNumberOfSlides() == 0) {
            throw new Exception("Cannot call getCurrentSlide if slideshow is empty");
        }

        return slideshow.getSlideAtIndex(currentSlideIndex);
    }

    public void addCurrentSlideChangedListener(CurrentSlideChangedListener listener) {
        currentSlideChangedListeners.add(listener);
    }

    public void removeCurrentSlideChangedListener(CurrentSlideChangedListener listener) {
        currentSlideChangedListeners.remove(listener);
    }

    private void requireSlideshowNotEmpty(String message) throws Exception {
        if(slideshow.getNumberOfSlides() == 0) {
            throw new Exception(message);
        }
    }

    /**
     * Change le slide courant toutes les X secondes
     * Notifie les {@link CurrentSlideChangedListener} à chaque changement de slide.
     *
     * @throws java.lang.NullPointerException si play est appelée alors qu'il n'y a pas de slideshow de chargé.
     * @throws java.lang.Exception si play est appelée alors que le slideshow est vide.
     */
    public void play() throws Exception {
        Objects.requireNonNull(slideshow, "Cannot call play if no slideshow has been loaded");
        requireSlideshowNotEmpty("Cannot call play if slideshow is empty");

        Timer t = new Timer();
        currentTask = new TimerTask() {
            @Override
            public void run() {
                if(SlideshowController.this.slideshow.getNumberOfSlides() - 1
                    == SlideshowController.this.currentSlideIndex) {
                    t.cancel();
                    return;
                }

                // we should use uncaughtExceptionHandler here
                // http://stackoverflow.com/a/11584273
                // http://make-aitee-work.blogspot.de/2013/12/a-executor-is-not-thread-or-correct.html
                 try {
                     SlideshowController.this.nextSlide();
                 } catch (Exception e) {
                     t.cancel();
                     e.printStackTrace(); //TODO logger l'exception
                 }
            }
        };

        this.status = STATUS.PLAYING;
        t.schedule(currentTask, 0, transitionTimeBetweenSlides);
    }


    /**
     * Stoppe le défilement automatique du diaporama.
     *
     * @throws com.humanbooster.slideshowplayer.controller.SlideshowControllerStatusException si le SlideshowController n'est pas en mode play
     */
    public void pause() throws SlideshowControllerStatusException {
        if(! STATUS.PLAYING.equals(this.status)) {
            throw new SlideshowControllerStatusException("Cannot call pause if SlideshowController is now playing");
        }
        currentTask.cancel(); //TODO si pas play => pas currentTask => NPE
        this.status = STATUS.PAUSED;
    }

    /**
     * Arrete le défilement. Si le défilement n'est pas démarré, ne fait rien.
     *
     * @throws java.lang.NullPointerException si play est appelée alors qu'il n'y a pas de slideshow de chargé.
     * @throws java.lang.Exception si play est appelée alors que le slideshow est vide.
     */
    public void stop() throws Exception {
        Objects.requireNonNull(slideshow, "Cannot call play if no slideshow has been loaded");
        requireSlideshowNotEmpty("Cannot call play if slideshow is empty");


        /// <BUG>
        if(currentTask != null) { // cas ou on a jamais fait PLAY ou que le status est PAUSED.
            currentTask.cancel();
        }
        this.status = STATUS.STOPPED;
        this.currentSlideIndex = 0;
        /// </BUG>
    }

}
