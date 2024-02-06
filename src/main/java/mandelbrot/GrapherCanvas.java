package mandelbrot;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventListener;


public class GrapherCanvas extends Canvas {
    private final double WIDTH=1000;
    private final double HEIGHT=900;
    private double topLeftX;
    private double topLeftY;
    private double zoomFactor = 350;
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
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                topLeftX += mouseEvent.getX()/zoomFactor;
                topLeftY += mouseEvent.getY()/zoomFactor;

                if(mouseEvent.getButton() == MouseButton.PRIMARY){
                    zoomFactor *= 2;
                }else if(mouseEvent.getButton() == MouseButton.SECONDARY){
                    zoomFactor /=2;
                }

                topLeftX -= (WIDTH/2)/zoomFactor;
                topLeftY -=(HEIGHT/2)/zoomFactor;

                renderSet(topLeftX,topLeftY);
            }
        });
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

    // renders the image after scaling according to the zoom factor
    public void renderSet(double xInit, double yInit){

        long start = System.currentTimeMillis();
        topLeftX = xInit;
        topLeftY = yInit;
        if(set == null){
            throw new NullPointerException("you must specify the set for the canvas using setSet() method");
        }

        GraphicsContext gc = getGraphicsContext2D();

        for(ArrayList<Pixel> bracket : colorBrackets){
            bracket.clear();
        }

        for(int x=0; x<=WIDTH; x++){
            for(int y=0; y<=HEIGHT; y++){

                Complex c = new Complex(topLeftX + x/zoomFactor, topLeftY + y/zoomFactor);
                int colorNumber = set.isInSet(c);
                colorBrackets.get(colorNumber).add(new Pixel(x,y));
            }
        }

        for(int i=0; i<=numberOfColorBrackets;i++){
            double h = (330.0/numberOfColorBrackets)*i;
            if(i==0){
                gc.setFill(Color.hsb(h,1,0)); //black as brightness is 0
            }else{
                gc.setFill(Color.hsb(h,1,1));
            }
            for(Pixel pixel : colorBrackets.get(i)){
                gc.fillRect(pixel.x,pixel.y,1,1);
            }
        }
        double finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }


}

