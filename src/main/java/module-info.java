module com.example.mandelbrotvisualizer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens mandelbrot to javafx.fxml;
    exports mandelbrot;
}