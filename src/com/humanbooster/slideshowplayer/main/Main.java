package com.humanbooster.slideshowplayer.main;

import com.humanbooster.slideshowplayer.controller.SlideshowEngine;
import com.humanbooster.slideshowplayer.model.*;
import com.humanbooster.slideshowplayer.view.console.ConsoleView;

import java.util.Scanner;

public class Main {
    public static final String QUIT = "quit";
    public static final String NEXT = "next";
    public static final String SHOW = "show";
    public static final String PLAY = "play";
    public static final String PAUSE = "pause";
    public static final String STOP = "stop";

    public static void main(String[] args) {
        // créer un slideshow
        Slideshow ss = new Slideshow();
        // ajouter 10 slides
        for (int i = 0; i < 10; i++) {
            Slide s = new Slide();

            TextSlideElement title = new TextSlideElement(0.1,0.2,0.3,0.4, -1);  // TextSlideElement
            title.setContent("Slide " + i);

            ImageSlideElement image = new ImageSlideElement(0.1,0.2,0.3,0.4, -1); // ImageSlideElement
            image.setContent("url de l'image");

            s.addSlideElement(title);
            s.addSlideElement(image);

            ss.addSlide(s);
        }

        SlideshowEngine sc = new SlideshowEngine();
        sc.setSlideshow(ss);

        ConsoleView view = new ConsoleView();
        sc.addCurrentSlideChangedListener(view);

        Scanner scanner = new Scanner(System.in);

        String input = null;
        do {
            input = scanner.nextLine();
            if(NEXT.equals(input)) { // si l'utilisateur tappe "suivant" => aller au slide suivant
                // essayons d'appeller cette méthode
                try {
                    sc.nextSlide();
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

            if(PLAY.equals(input)) {
                try {
                    sc.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(PAUSE.equals(input)) {
                // mettre en pause
            }

            if(STOP.equals(input)) {
                // arreter la lecture automatique et revenir au premier slide
            }

        } while(! QUIT.equals(input)); // tant que l'utilisateur n'a pas tappé "quit"

        // si l'utilisateur tappe "précédent" => aller au slide précédent
        // si l'utilisateur tappe "affiche" => afficher le slide courant <=> toString()
    }
}
