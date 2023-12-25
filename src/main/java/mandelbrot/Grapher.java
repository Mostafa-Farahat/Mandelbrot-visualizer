package mandelbrot;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Grapher extends Application {
    final double HEIGHT = 900;
    final double WIDTH = 1500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(WIDTH,HEIGHT);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        renderSet(-2,1,-1.5,1.5,2000,gc);

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mandelbrot Visualizer");
        primaryStage.show();
    }

    //renders the mandelbrod set
    //takes ranges desired for render, the value of escape (to modify accuracy)
    //and the graphicsContext for the canvas
    public void renderSet(double xMin,  double xMax, double yMin, double yMax,int escapeCount, GraphicsContext gc){
        double stepSize = Math.max((xMax-xMin) / WIDTH,(yMax-yMin) / HEIGHT );
        BrotSet set = new BrotSet(escapeCount);

        //these are the actual values that are
        //used for computations
        //they are the numerical value assigned to each pixel
        //(the numerical value of each pixel after scaling)
        double xPoint =xMin;
        double yPoint = yMin;

        for(int x=0; x<=WIDTH; x++){
            for(int y=0; y<=HEIGHT; y++){
                Complex c = new Complex(xPoint, yPoint);
                int iterationNumber = set.isInSet(c);

//                System.out.println(xPoint + " " + yPoint);

                //c is part of the mandelbrot set
                if(iterationNumber == escapeCount){
                    gc.setFill(Color.BLACK);
                }else{
                    gc.setFill(Color.WHITE);
                }

                gc.fillRect(x, y, 1, 1);
                yPoint = yPoint + stepSize;
            }
            xPoint = xPoint + stepSize;
            yPoint = yMin;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
