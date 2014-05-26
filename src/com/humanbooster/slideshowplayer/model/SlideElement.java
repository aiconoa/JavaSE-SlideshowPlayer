package com.humanbooster.slideshowplayer.model;

/**
 * Les coordonnées et dimensions du SlideElement sont exprimées en pourcentage
 * des coordonnées et dimensions du Slide contenant, c.a.d. entre 0 et 1 pour exprimer une taille
 * entre 0 et 100%.
 * Il est possible d'avoir une taille supérieure à 1, cad supérieure 100% de la taille du Slide
 */
public class SlideElement {

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

    /**
     * Contenu du SlideElement
     */
    private Object content;

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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
