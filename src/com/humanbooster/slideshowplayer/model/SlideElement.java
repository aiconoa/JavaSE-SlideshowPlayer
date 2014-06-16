package com.humanbooster.slideshowplayer.model;

/**
 * Les coordonnées et dimensions du SlideElement sont exprimées en pourcentage
 * des coordonnées et dimensions du Slide contenant, c.a.d. entre 0 et 1 pour exprimer une taille
 * entre 0 et 100%.
 * Il est possible d'avoir une taille supérieure à 1, cad supérieure 100% de la taille du Slide
 */
public interface SlideElement {

    public double getX();

    public void setX(double x);

    public double getY();

    public void setY(double y);

    public double getWidth();

    public void setWidth(double width);

    public double getHeight();

    public void setHeight(double height);

    public int getZIndex();

    public void setZIndex(int zindex);
}
