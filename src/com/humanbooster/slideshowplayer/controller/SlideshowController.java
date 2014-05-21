package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;

/**
 * Gère la logique de l'application, c'est à dire les actions en
 * réaction aux solicitations de l'utilisateur.
 * Interagit avec le modèle de données représenté par un
 * Slideshow, ses Slides , etc...
 * Lors du chargement d'un slideshow le slide courant est le premier slide du slideshow.
 */
public class SlideshowController {
    private int currentSlideIndex = -1;
    private Slideshow slideshow = null;

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
        currentSlideIndex = 0;
    }

    /**
     * Retourne le {@link com.humanbooster.slideshowplayer.model.Slide} suivant.
     * <p>La position du slide courant dans le slideshow courant est incrémentée.</p>
     *
     * @return le slide suivant
     *
     * @throws java.lang.Exception si nextSlide est appelée alors qu'il n'y a pas de slideshow de chargé.
     * @throws java.lang.Exception si nextSlide est appelée alors que le slideshow est vide.
     */
    public Slide nextSlide() throws Exception {
        if(slideshow == null) {
            //TODO jetter une exception plus spécifique
            throw new Exception("Cannot call nextSlide if no slideshow has been loaded");
        }

        if(slideshow.getNumberOfSlides() == 0) {
            throw new Exception("Cannot call nextSlide if slideshow is empty");
        }

        if(currentSlideIndex == (slideshow.getNumberOfSlides() - 1)) {
            throw new Exception("Cannot call nextSlide if the current slide is the last one");
        }

        // incrémente l'indice du slide courant
        currentSlideIndex++;
        // retourne le slide à la position correspondante à cet index dans le slideshow
        return slideshow.getSlideAtIndex(currentSlideIndex);
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

}
