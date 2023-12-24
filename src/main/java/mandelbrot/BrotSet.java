package mandelbrot;

public class BrotSet {
    private int escapeCount;

    BrotSet(int escapeCount){
        this.escapeCount = escapeCount;
    }

    //uses the function Zn+1 = Zn^2 + C
    //Z0 = 0 + 0i
    public int isInSet(Complex c){
        int iteration=0;
        Complex z = new Complex(0,0);

        while(iteration != escapeCount){
            iteration++;
            z = z.multiply(z).add(c);
            if(z.getAbsSquared() > 4){
                return iteration;
            }
        }
        return escapeCount;
    }
}
