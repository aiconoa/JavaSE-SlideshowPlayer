package com.humanbooster.slideshowplayer.model;

import java.util.*;

/**
 * Un slide d'un {@link Slideshow}.
 * <p>Un Slide contient une collection de SlideElement.
 * Il ne peut pas y avoir de double de SlideElement</p>
 */
public class Slide {

    private Set<SlideElement> slideElements = new HashSet<>();

    public Set<SlideElement> getSlideElements() {
        return Collections.unmodifiableSet(slideElements);
    }

    /**
     * Ajoute un {@link SlideElement} à notre Slide.
     *
     * //TODO Test unitaire jette une exception si le slide element existe déjà dans le slide. PAS DE DOUBLON
     *
     * @param se
     * @throws java.lang.NullPointerException si le {@link SlideElement} est {@code null}
     */
    public void addSlideElement(SlideElement se) {
        Objects.requireNonNull(se); //TODO test unitaire de la NPE
        slideElements.add(se);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getCanonicalName())
          .append("{")
          .append(System.lineSeparator());

        for (SlideElement slideElement : slideElements) {
            sb.append("\t")
              .append(slideElement)
              .append(System.lineSeparator());
        }

        sb.append("}");
        return sb.toString();
    }
}
