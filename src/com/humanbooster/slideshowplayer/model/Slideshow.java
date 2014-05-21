package com.humanbooster.slideshowplayer.model;

import java.util.Arrays;

/**
 * Représente un document de type slideshow (diaporama).
 * <p>Un slideshow contient une collection de {@link Slide}.</p>
 */
public class Slideshow {

    /**
     * Capacité par défaut du Slideshow
     */
    public static final int CAPACITY = 10;

    private Slide[] slides;

    private int numberOfSlides;

    public Slideshow() {
        numberOfSlides = 0;
        slides = new Slide[CAPACITY];
    }

    /**
     * Ajoute un slide dans le Slideshow à la première place libre
     * @param slide le slide a ajouter dans le Slideshow
     */
    public void addSlide(Slide slide) {
        // TODO attention trous dans le tableau (par exemple via suppression) alors
        // on risque d'introduire un élément ailleurs qu'à la fin

        for (int i = 0; i < slides.length; i++) {
            if(slides[i] == null) {
                slides[i] = slide;
                numberOfSlides++;
                return;
            }
        }

        // dans le cas ou le tableau est déjà plein (on a pas rajouté notre élément dans le tableau)
        int oldLength = slides.length;
        // agrandir le tableau de CAPACITY
        slides =  Arrays.copyOf(slides, slides.length + CAPACITY);
        // ajoute un élément à la position oldSlides.length
        slides[oldLength] = slide;
        numberOfSlides++;
    }

    /**
     * Retourne le nombre de Slide présents dans le Slideshow
     * @return le nombre de Slide dans le Slideshow
     */
    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    /**
     * Retourne le Slide a la position index dans le Slideshow
     * @param index l'index du Slide dans le Slideshow
     * @return le slide à l'index donné
     */
    public Slide getSlideAtIndex(int index) {
        // TODO vérifier que index est dans les bornes du tableau
        // TODO heu... ca risque de ne plus marcher si un jour
        // on doit supprimer un élément du tableau => trou introduit
        return slides[index];
    }
}
