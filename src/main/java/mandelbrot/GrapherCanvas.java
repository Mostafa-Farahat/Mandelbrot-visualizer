package mandelbrot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class GrapherCanvas extends Canvas {
    private final double WIDTH=1000;
    private final double HEIGHT=900;
    private int numberOfColorBrackets;
    private Set set;
    private static GrapherCanvas canvasInstance;
    private class Pixel{
        int x;
        int y;
        Pixel(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    ArrayList<ArrayList<Pixel>> colorBrackets = new ArrayList<ArrayList<Pixel>>();

    private GrapherCanvas(){
        super(1000,900);//horrible move
    }
    public static GrapherCanvas getInstance(){
        if(canvasInstance == null){
            canvasInstance = new GrapherCanvas();
        }
        return canvasInstance;
    }

    //must be used to specify a set for the canvas to draw
    public void setSet(Set set){
        this.set = set;
        numberOfColorBrackets = set.getEscapeCount();

        for(int i=0; i<=numberOfColorBrackets;i++){
            colorBrackets.add(new ArrayList<Pixel>());
        }
    }

    /*renders the set
    takes ranges desired for render, the value of escape (to modify accuracy)
    and the graphicsContext for the canvas*/
    public void renderSet(double xMin,  double xMax, double yMin, double yMax){
        if(set == null){
            throw new NullPointerException("you must specify the set for the canvas using setSet() method");
        }

        GraphicsContext gc = getGraphicsContext2D();

        double stepSize = Math.max((xMax-xMin) / WIDTH,(yMax-yMin) / HEIGHT );
        /*these are the actual values that are
        used for computations
        they are the numerical value assigned to each pixel
        (the numerical value of each pixel after scaling)*/
        double xPoint =xMin;
        double yPoint = yMin;

        for(int x=0; x<=WIDTH; x++){
            for(int y=0; y<=HEIGHT; y++){

                Complex c = new Complex(xPoint, yPoint);
                int colorNumber = set.isInSet(c);
                colorBrackets.get(colorNumber).add(new Pixel(x,y));

                yPoint = yPoint + stepSize;
            }
            xPoint = xPoint + stepSize;
            yPoint = yMin;
        }

        for(int i=0; i<=numberOfColorBrackets;i++){
            int color = i % 255;
            gc.setFill(Color.rgb(color,color,color));
//            if (i == 0) {
//                gc.setFill(Color.rgb(0,0,0));
//            }else{
//                gc.setFill(Color.rgb(255,255,255));
//            }
            for(Pixel pixel : colorBrackets.get(i)){
                gc.fillRect(pixel.x,pixel.y,1,1);
            }
        }
    }
}
