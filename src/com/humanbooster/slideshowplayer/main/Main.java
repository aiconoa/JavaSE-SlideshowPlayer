package com.humanbooster.slideshowplayer.main;

import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.SlideElement;
import com.humanbooster.slideshowplayer.model.Slideshow;

import java.util.Scanner;

public class Main {
    public static final String QUIT = "quit";
    public static final String NEXT = "next";
    public static final String SHOW = "show";

    public static void main(String[] args) {
        // créer un slideshow
        Slideshow ss = new Slideshow();
        // ajouter 10 slides
        for (int i = 0; i < 10; i++) {
            Slide s = new Slide();

            SlideElement title = new SlideElement();
            title.setX(0.1); // (largeur totale (1.0) - largeur élément (0.8)) / 2
            title.setY(0.4); // (hauteur totale (1.0) - hauteur élément (0.2)) / 2
            title.setWidth(0.80);
            title.setHeight(0.2);
            title.setContent("Slide " + i);

            s.addSlideElement(title);
            ss.addSlide(s);
        }

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        Scanner scanner = new Scanner(System.in);

        String input = null;
        do {
            input = scanner.nextLine();
            if(NEXT.equals(input)) { // si l'utilisateur tappe "suivant" => aller au slide suivant
                // essayons d'appeller cette méthode
                try {
                    Slide s = sc.nextSlide();
                    System.out.println(s);
                } catch(Exception e) { // si jamais il y a une erreur
                    System.out.println(e.getMessage());
                }
            }
            if(SHOW.equals(input)) {
                // récupérer le slide courant
                //Slide s = sc.getCurrentSlide();
                //System.out.println(s);
                // affiche le slide
            }

        } while(! QUIT.equals(input)); // tant que l'utilisateur n'a pas tappé "quit"

        // si l'utilisateur tappe "précédent" => aller au slide précédent
        // si l'utilisateur tappe "affiche" => afficher le slide courant <=> toString()
    }
}
