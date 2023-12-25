package mandelbrot;

/*
* When plotting other sets that use escape time algorithm(ex:julia set)
* a set object implementing this interface should be created
* the interface will be used by the grapher class to graph the
* set regardless of the rules that determine the set
*/

public interface Set {
    //return number of iterations for escape
    int isInSet(Complex c);
    int getEscapeCount();
}
