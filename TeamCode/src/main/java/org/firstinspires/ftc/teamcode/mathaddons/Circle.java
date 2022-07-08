package org.firstinspires.ftc.teamcode.mathaddons;

public class Circle{
    public Vector2D position;
    public double radius;
    public Circle(Vector2D pos, double rad){
        position = pos;
        radius = rad;
    }
    public Vector2D[] intersect(Circle circle){
        Vector2D v = circle.position.clone().subtract(position);
        double d = v.norm();
        double x = (Math.pow(radius, 2) - Math.pow(circle.radius, 2) + Math.pow(d, 2)) / 2 / d;
        if (radius < x) { Vector2D[] r = {}; return r; }
        double h = Math.sqrt(Math.pow(radius, 2) - Math.pow(x, 2));
        Vector2D v1 = v.clone().multiply(x / d);
        Vector2D v2 = v.orthogonal().multiply(h / d);
        Vector2D[] r = {v1.clone().add(v2, position), v1.clone().subtract(v2).add(position)};
        return r;
    }
}
