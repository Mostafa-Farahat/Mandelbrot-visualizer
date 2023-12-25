package mandelbrot;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    final double HEIGHT = 900;
    final double WIDTH = 1000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Set brotSet = new BrotSet(2000);
        GrapherCanvas canvas = new GrapherCanvas(brotSet,WIDTH,HEIGHT);
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mandelbrot Visualizer");


        long start = System.currentTimeMillis();
        canvas.renderSet(-2,1,-1.5,1.5);

        primaryStage.show();
        double finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
