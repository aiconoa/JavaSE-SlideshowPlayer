package com.humanbooster.slideshowplayer.main;

import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.model.ImageSlideElement;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import com.humanbooster.slideshowplayer.model.TextSlideElement;
import com.humanbooster.slideshowplayer.view.javafx.SlideshowJavaFXController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by T on 04/06/2014.
 */
public class MainJavaFX extends Application {

    private Slideshow currentSlideshow = null;
    private SlideshowController slideshowController = null;

    @Override
    public void init() throws Exception {
        super.init();

        // créer un slideshow
        currentSlideshow = new Slideshow();
        // ajouter 10 slides
        for (int i = 0; i < 10; i++) {
            Slide s = new Slide();

            TextSlideElement title = new TextSlideElement(0.1,0.2,0.3,0.4);  // TextSlideElement
            title.setContent("Slide " + i);

            ImageSlideElement image = new ImageSlideElement(0.1,0.2,0.3,0.4); // ImageSlideElement
            image.setContent("url de l'image");

            s.addSlideElement(title);
            s.addSlideElement(image);

            currentSlideshow.addSlide(s);
        }

        slideshowController = new SlideshowController();
        slideshowController.setSlideshow(currentSlideshow);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlPath = "/com/humanbooster/slideshowplayer/view/javafx/slideshow.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        SlideshowJavaFXController slideshowJavaFXController = fxmlLoader.getController();
        slideshowJavaFXController.setSlideshowController(slideshowController);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
