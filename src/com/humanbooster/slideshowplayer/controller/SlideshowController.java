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

    private Slideshow slideshow;

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
        currentSlideIndex = 0;
    }

    /**
     * Retourne le slide suivant.
     * La position du slide courant dans le slideshow courant est incrémentée.
     * @return
     */
    public Slide nextSlide() {
        // incrémente l'indice du slide courant
        currentSlideIndex++;
        // retourne le slide à la position correspondante à cet index dans le slideshow
        return slideshow.getSlideAtIndex(currentSlideIndex);
    }

}
