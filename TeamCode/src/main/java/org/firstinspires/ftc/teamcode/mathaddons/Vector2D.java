package org.firstinspires.ftc.teamcode.mathaddons;

public class Vector2D {
    public double x;
    public double y;
    public Vector2D(double dx, double dy) { x = dx; y = dy; }
    public Vector2D add(Vector2D ...vs){
        for (Vector2D v : vs) { x += v.x; y += v.y; }
        return this;
    }
    public Vector2D subtract(Vector2D ...vs){
        for (Vector2D v : vs) { x -= v.x; y -= v.y; }
        return this;
    }
    public Vector2D multiply(double n){
        x *= n; y += n; return this;
    }
    public Vector2D rotate(Vector2D v){
        return setValue(x*v.x -y*v.y,x*v.y + y*v.x);
    }
    public Vector2D rotate(double a){
        return rotate(new Vector2D(Math.cos(a), Math.sin(a)));
    }
    public Vector2D clone(){
        return new Vector2D(x,y);
    }
    public Vector2D orthogonal(){
        return new Vector2D(-y,x);
    }
    public double norm(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    public Vector2D setNorm(double n){
        double N = norm();
        if (N == 0) return this;
        return multiply(n/N);
    }
    public double scalar(Vector2D v){
        return x*v.x + y*v.y;
    }
    public double Det(Vector2D v){
        return x*v.y - y*v.x;
    }
    public Vector2D setValue(double dx, double dy){
        x = dx; y = dy; return this;
    }
}
