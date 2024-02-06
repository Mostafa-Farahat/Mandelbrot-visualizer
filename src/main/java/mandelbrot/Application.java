package mandelbrot;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    final double HEIGHT = 900;
    final double WIDTH = 1000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Set brotSet = new BrotSet(200);
        GrapherCanvas canvas = GrapherCanvas.getInstance();
        canvas.setSet(brotSet);

        Group root = new Group(); //the root of the scene graph
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mandelbrot Visualizer");

        canvas.renderSet(-2,-1.5);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
