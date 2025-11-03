package org.example;

public class Pozycja {
    double  x;
    double  y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void aktualizujPozycję(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    String getPozycja() {
        this(x,y);
    }
}
