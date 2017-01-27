package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import objectClasses.AnimatedImage;


public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
        theStage.setTitle("Timeline Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(512,512);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image earth = new Image("resources/earth.png");
        Image sun = new Image("resources/sun.png");
        Image space = new Image("resources/space.png");
        //UFO object
        AnimatedImage ufo = new AnimatedImage();
        Image[] imageArray = new Image[6];
        for (int i = 0; i < 6; i++) {
            imageArray[i] = new Image("resources/ufo_" + i + ".png");
        }
        ufo.frames = imageArray;
        ufo.duration = 0.100;
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                //background image clears canvas
                gc.drawImage(space,0,0);
                gc.drawImage(earth,x,y);
                gc.drawImage(sun, 196,196);
                // draw UFO
                gc.drawImage(ufo.getFrame(t), 250, 25);

            }
        }.start();


        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
