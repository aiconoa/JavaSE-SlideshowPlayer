package com.humanbooster.slideshowplayer.model;

/**
 * Created by T on 26/05/2014.
 */
public class SlideElementBase implements SlideElement{

    /**
     * Coordonnée x du coin haut gauche du SlideElement
     */
    private double x;

    /**
     * Coordonnée y du coin haut gauche du SlideElement
     */
    private double y;

    /**
     * Largeur exprimée en pourcentage de la largeur du slide parent (supérieure à 0)
     */
    private double width;

    /**
     * Hauteur exprimée en pourcentage de la hauteur du slide parent (supérieure à 0)
     */
    private double height;

    public SlideElementBase(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + " {" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
