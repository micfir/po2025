package Samochód.src.main.java.org.example;

public class Pozycja {
    private double  x;
    private double  y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void aktualizujPozycje(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public String getPozycja() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    @Override
    public String toString() {
        return getPozycja();
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
