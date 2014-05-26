package com.humanbooster.slideshowplayer.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Représente un document de type slideshow (diaporama).
 * <p>Un slideshow contient une collection de {@link Slide}.</p>
 */
public class Slideshow {

    private ArrayList<Slide> slides;

    /**
     * Largeur des slides du Slideshow en mm
     */
    private int width;

    /**
     * Hauteur des slides du Slideshow en mm
     */
    private int height;

    public Slideshow() {
        this(297, 210);
    }

    public Slideshow(int width, int height) {
        // Type inference
        slides = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Ajoute un slide dans le Slideshow à la fin
     * @param slide le slide a ajouter dans le Slideshow
     *
     * @throws java.lang.NullPointerException si slide est {@code null}
     */
    public void addSlide(Slide slide) {
        Objects.requireNonNull(slide, "Cannot add a null Slide");
        slides.add(slide);
    }

    /**
     * Retourne le nombre de Slide présents dans le Slideshow
     * @return le nombre de Slide dans le Slideshow
     */
    public int getNumberOfSlides() {
        return slides.size();
    }

    /**
     * Retourne le Slide a la position index dans le Slideshow
     * @param index l'index du Slide dans le Slideshow
     * @return le slide à l'index donné
     * @throws SlideshowIndexOutOfBoundsException if the index is out of range (index < 0 || index >= getNumberOfSlides())
     */
    public Slide getSlideAtIndex(int index) {
        if(index < 0) {
            throw new SlideshowIndexOutOfBoundsException("the index is out of range (index < 0)");
        }

        if(index >= slides.size()) {
            throw new SlideshowIndexOutOfBoundsException("the index is out of range (index >= getNumberOfSlides())");
        }

        return slides.get(index);
    }
}