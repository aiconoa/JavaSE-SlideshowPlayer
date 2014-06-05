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

    private SlideshowController slideshowController = null;

    @Override
    public void init() throws Exception {
        super.init();

        slideshowController = new SlideshowController();
        slideshowController.setTransitionTimeBetweenSlides(3000);
        //slideshowController.setSlideshow(dummySlideshowFactory());
        slideshowController.setSlideshow(defaultSlideshowFactory());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlPath = "/com/humanbooster/slideshowplayer/view/javafx/slideshow.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        SlideshowJavaFXController slideshowJavaFXController = fxmlLoader.getController();
        slideshowJavaFXController.setSlideshowController(slideshowController);
        slideshowJavaFXController.setStage(primaryStage);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    private Slideshow dummySlideshowFactory() {
        // créer un slideshow
        Slideshow slideshow = new Slideshow();

        // ajouter 10 slides
        for (int i = 0; i < 10; i++) {
            Slide s = new Slide();

            TextSlideElement title = new TextSlideElement(0.1,0.1,0.8,0.2);  // TextSlideElement
            title.setContent("Slide " + i);

            ImageSlideElement image = new ImageSlideElement(0.1,0.4,0.8,0.5); // ImageSlideElement
            image.setContent("http://placehold.it/640x480");

            s.addSlideElement(title);
            s.addSlideElement(image);

            slideshow.addSlide(s);
        }

        return slideshow;
    }
    private Slideshow defaultSlideshowFactory() {
        Slideshow slideshow = new Slideshow();
        slideshow.addSlide(this.slideFactory("Bromo", "/resources/images/bromo.jpg"));
        slideshow.addSlide(this.slideFactory("Malaysia", "/resources/images/malaysia.jpg"));
        slideshow.addSlide(this.slideFactory("Mercantour", "/resources/images/mercantour.jpg"));
        slideshow.addSlide(this.slideFactory("Seychelles", "/resources/images/seychelles.jpg"));
        slideshow.addSlide(this.slideFactory("Vietnam", "/resources/images/vietnam.jpg"));
        slideshow.addSlide(this.slideFactory("Brazil", "/resources/images/brazil.jpg"));
        return slideshow;
    }
    private Slide slideFactory(String title, String imageURL) {
        Slide s = new Slide();

        TextSlideElement textSlideElement = new TextSlideElement(0.025,0.025, 0.95, 0.05);  // TextSlideElement
        textSlideElement.setContent(title);

        ImageSlideElement imageSlideElement = new ImageSlideElement(0.025,0.1, 0.95, 0.875); // ImageSlideElement
        imageSlideElement.setContent(imageURL);

        s.addSlideElement(textSlideElement);
        s.addSlideElement(imageSlideElement);

        return s;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
