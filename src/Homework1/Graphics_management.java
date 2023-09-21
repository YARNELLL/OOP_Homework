package Homework1;

import java.lang.Math;

public class Graphics_management {
    public static void main(String[] args){

    }
}

class Ellipsoid implements Graphics{
    double major_semi_axis;
    double minor_semi_axis;

    public Ellipsoid(double major_semi_axis, double minor_semi_axis){
        this.major_semi_axis = major_semi_axis;
        this.minor_semi_axis = minor_semi_axis;
    }
    @Override
    public double getArea() {
        return Math.PI * major_semi_axis * minor_semi_axis;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * minor_semi_axis + 4 * (major_semi_axis - minor_semi_axis);
    }
}

class Rectangle implements Graphics{
    double length;
    double width;

    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }
    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
}

class Triangle implements Graphics{
    double edge_1;
    double edge_2;
    double edge_3;

    public Triangle(double edge_1, double edge_2, double edge_3) {
        this.edge_1 = edge_1;
        this.edge_2 = edge_2;
        this.edge_3 = edge_3;
    }

    @Override
    public double getArea() {
        double p = (edge_1 + edge_2 + edge_3) / 2;
        return Math.sqrt(p * (p - edge_1) * (p - edge_2) * (p - edge_3));
    }

    @Override
    public double getPerimeter() {
        return 0;
    }
}

interface Graphics{
    double getArea();

    double getPerimeter();
}
