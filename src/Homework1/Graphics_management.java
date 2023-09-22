package Homework1;

import java.lang.Math;
import java.util.Scanner;
/**
 * @author 吴禹 2023214309
 */
public class Graphics_management {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Graphics_Factory graphicsFactory = new Graphics_Factory();
        String graphicsType;
        System.out.println("选择三种图形中的一种，椭圆形、矩形和三角形：");
        graphicsType = sc.next();
        Graphics graphics = graphicsFactory.getGraphics(graphicsType);
        if(graphics != null){
            graphics.draw();
            System.out.format("%s的面积为：%.2f\n", graphicsType ,graphics.getArea());
            System.out.format("%s的周长为：%.2f\n", graphicsType, graphics.getPerimeter());
        }
        else{
            System.out.println("请输入有效的图形。");
        }
        sc.close();
    }
}


class Ellipsoid implements Graphics{
    /*
    椭圆形
     */
    private final double major_semi_axis;
    private final double minor_semi_axis;

    public Ellipsoid(double major_semi_axis, double minor_semi_axis) {
        this.major_semi_axis = major_semi_axis;
        this.minor_semi_axis = minor_semi_axis;
    }

    @Override
    public void draw() {
        System.out.println("画了一个椭圆形。");
    }

    @Override
    public double getArea() {
        return Math.PI * major_semi_axis * minor_semi_axis;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * minor_semi_axis +
                4 * (major_semi_axis - minor_semi_axis);
    }
}
class Rectangle implements Graphics{
    /*
    矩形
     */
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw() {
        System.out.println("画了一个矩形");
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
    /*
    三角形
     */
    private final double edge_1;
    private final double edge_2;
    private final double edge_3;

    public Triangle(double edge_1, double edge_2, double edge_3) {
        this.edge_1 = edge_1;
        this.edge_2 = edge_2;
        this.edge_3 = edge_3;
    }

    @Override
    public void draw() {
        System.out.println("画了一个三角形");
    }

    @Override
    public double getArea() {
        double p = (edge_1 + edge_2 + edge_3) / 2;
        return Math.sqrt(p * (p - edge_1) * (p - edge_2) * (p - edge_3));
    }

    @Override
    public double getPerimeter() {
        return edge_1 + edge_2 + edge_3;
    }
}

interface Graphics{
    /*
    图形接口，存放图形类所需的函数
     */
    void draw();
    double getArea();
    double getPerimeter();
}

class Graphics_Factory{
    public Graphics getGraphics(String graphicsType){
        Scanner sc = new Scanner(System.in);
        Graphics graphics = null;
        switch (graphicsType) {
            case "椭圆形":
                double major_semi_axis;
                double minor_semi_axis;
                System.out.println("输入椭圆形的长半轴和短半轴，中间用空格隔开：");
                major_semi_axis = sc.nextDouble();
                minor_semi_axis = sc.nextDouble();
                if(major_semi_axis >= minor_semi_axis){
                    graphics = new Ellipsoid(major_semi_axis, minor_semi_axis);
                }
                else{
                    System.out.println("长半轴应该比短半轴长。");
                }
                break;
            case "矩形":
                double length;
                double width;
                System.out.println("输入矩形的长和宽，中间用空格隔开：");
                length = sc.nextDouble();
                width = sc.nextDouble();
                graphics = new Rectangle(length, width);
                break;
            case "三角形":
                double edge_1;
                double edge_2;
                double edge_3;
                System.out.println("输入三角形的三条边，中间用空格隔开：");
                edge_1 = sc.nextDouble();
                edge_2 = sc.nextDouble();
                edge_3 = sc.nextDouble();
                graphics = new Triangle(edge_1, edge_2, edge_3);
                break;
        }
        sc.close();
        return graphics;
    }
}