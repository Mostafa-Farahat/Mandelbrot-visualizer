package mandelbrot;

public class BrotSet implements Set{

    int escapeCount;
    BrotSet(int escapeCount){
        this.escapeCount = escapeCount;
    }

    //uses the function Zn+1 = Zn^2 + C
    //Z0 = 0 + 0i
    //returns a number corresponding to the color to be filled
    public int isInSet(Complex c){
        int iteration=0;
        Complex z = new Complex(0,0);

        while(iteration != escapeCount){
            z = z.multiply(z).add(c);
            iteration++;
            if(z.getAbsSquared() > 4){
                return iteration;
            }
        }
        //if it is part of the set
        //return 0 to paint it black
        return 0;
    }

    public int getEscapeCount(){
        return escapeCount;
    }
}
