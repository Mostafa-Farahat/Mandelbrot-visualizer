package mandelbrot;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Timer;

public class GrapherCanvas extends Canvas {
    final double WIDTH;
    final double HEIGHT;

    Set set;

    public GrapherCanvas(Set set, double width, double height){
        super(width,height);
        WIDTH = width;
        HEIGHT = height;
        this.set = set;
    }


    GraphicsContext gc = this.getGraphicsContext2D();

    //renders the set
    //takes ranges desired for render, the value of escape (to modify accuracy)
    //and the graphicsContext for the canvas
    public void renderSet(double xMin,  double xMax, double yMin, double yMax){
        double stepSize = Math.max((xMax-xMin) / WIDTH,(yMax-yMin) / HEIGHT );

        //these are the actual values that are
        //used for computations
        //they are the numerical value assigned to each pixel
        //(the numerical value of each pixel after scaling)
        double xPoint =xMin;
        double yPoint = yMin;

        for(int x=0; x<=WIDTH; x++){
            for(int y=0; y<=HEIGHT; y++){
                Complex c = new Complex(xPoint, yPoint);
                int colorNumber = set.isInSet(c);

                if(colorNumber == 0){
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

}
