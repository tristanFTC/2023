package org.firstinspires.ftc.teamcode.mathaddons;

public class Cartesienne2D {
    public double a;
    public double b;
    public double c;
    public Cartesienne2D(Vector2D A, Vector2D B) {
        this(A.y-B.y, B.x-A.x, (B.y-A.y)*A.x+(A.x-B.x)*A.y);
    }
    public Cartesienne2D(double x, double y, double z) {
        a = x; b = y; c = z;
    }
    public Vector2D[] intersect(Cartesienne2D line) {
        double t = a*line.b-line.a*b;
        if (t == 0){ Vector2D[] ret = {}; return ret; }
        Vector2D[] ret = {new Vector2D(b * line.c - line.b * c, a * line.c - line.a * c).multiply(1 / t)};
        return ret;
    }
    public double distance(Vector2D point) {
        return Math.abs(a * point.x + b * point.y + c) / Math.sqrt(Math.pow(a,2) + Math.pow(b, 2));

    }
    public Vector2D closest(Vector2D point) {
        double a2 = Math.pow(a,2), b2 = Math.pow(b,2), ab = a * b;
        return new Vector2D(
                (b2 * point.x - ab * point.y - a * c),
                (a2 * point.x - ab * point.y - b * c)
        ).multiply(1/(a2+b2));
    }
}
