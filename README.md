# Mandelbrot-visualizer
- This is a program to visualize the [Mandelbrot set](https://en.wikipedia.org/wiki/Mandelbrot_set) using java and java FX.

- The program uses the escape time algorithm to render the image on a JavaFX canvas.

## How To Use:
- The image can be moved around using the **arrow keys** on the keyboard.
- **Zoom** in on a specific point on the image by **right-clicking** the desired point.
- **Zoom** out by **left-clicking** the point form which it is required to zoom out. 


- *To improve the precision of the image change the `escapeCount` argument passed to the constructor of the `brotSet`
  object in the `Application.java` file. (a pull request implementing the feature of change the iteration count from within the program is more than welcomed :) )*

## Some Images from the program:

|![seaHorseValley](images/seaHorseValley.png)|
|:--:|
|*Sea Horse Valley*|


| ![seaHorseValleyBW](images/seaHorseBW.png) |
|:------------------------------------------:|
|  *A black render of the Sea Horse Valley*  |


| ![Full picture](images/brotSet.png) |
|:-----------------------------------:|
|     *A render of the full view*     |


**Improvement and pull requests are always welcomed :)**