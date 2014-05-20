package com.humanbooster.model;

import java.util.Arrays;

/**
 * Représente un document Slideshow.
 * Un slideshow contient une collection de slides
 */
public class Slideshow {

    public static final int CAPACITY = 10;

    private Slide[] slides;

    private int numberOfSlides;

    public Slideshow() {
        numberOfSlides = 0;
        slides = new Slide[CAPACITY];
    }

    /**
     * Ajoute un slide dans le Slideshow
     * @param slide a ajouter dans le Slideshow
     */
    public void addSlide(Slide slide) {
        boolean slidesWasFull = true;
        for (int i = 0; i < slides.length; i++) {
            if(slides[i] == null) {
                slides[i] = slide;
                slidesWasFull = false;
                break;
            }
        }
        // TODO heu... ca risque de ne plus marcher si un jour on doit supprimer un élément du tableau => trou introduit
        // TODO quoi qu'on fait si pas de place vide ?
        // agrandir le tableau !

        // dans le cas ou le tableau est déjà plein (on a pas rajouté notre élément dans le tableau)
        if(slidesWasFull) {
            int oldLength = slides.length;
            // agrandir le tableau de CAPACITY
            slides =  Arrays.copyOf(slides, slides.length + CAPACITY);
            // ajoute un élément à la position oldSlides.length
            slides[oldLength] = slide;
        }

        numberOfSlides++;
    }

    /**
     * Retourne le nombre de Slide présents dans le Slideshow
     * @return int nombre de Slide dans le Slideshow
     */
    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    /**
     * Retourne le Slide a la position index dans le Slideshow
     * @param index l'index du Slide dans le Slideshow
     * @return Slide le slide à l'index donné
     */
    public Slide getSlideAtIndex(int index) {
        // TODO vérifier que index est dans les bornes du tableau
        // TODO heu... ca risque de ne plus marcher si un jour
        // on doit supprimer un élément du tableau => trou introduit
        return slides[index];
    }
}
