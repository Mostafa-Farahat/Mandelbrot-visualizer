package mandelbrot;

import javafx.scene.control.ColorPicker;

public class Complex {
    private double re;
    private double imag;

    Complex(double re, double imag){
        this.re = re;
        this.imag = imag;
    }

    Complex(double number){
        this.re = number;
        this.imag = number;
    }

    //addition for two complex numbers
    public Complex add(Complex other){
        double newRe = re + other.re;
        double newImag = imag + other.imag;
        Complex result = new Complex(newRe,newImag);
        return result;
    }

    public Complex multiply(Complex other){
        double newRe = (re * other.re) - (imag* other.imag);
        double newImag = (re * other.imag) + (imag * other.re);
        Complex result = new Complex(newRe,newImag);
        return result;
    }

    //calculates the squared absolute value of the
    //complex number
    public double getAbsSquared(){
        return (re * re) + (imag * imag);
    }

    private String printNumber(){
        String str = re + " + " + imag + "i";
        return str;
    }

    //some tests
    public static void main(String[] args) {
        Complex num1 = new Complex(1);
        Complex num2 = new Complex(-3,5);
        Complex num3;

        num3 = num2.multiply(num2);
        num3 = num3.add(num1);

        String str2 = num2.printNumber();
        String str3 = num3.printNumber();
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(num3.getAbsSquared());
    }




}
