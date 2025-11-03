package org.example;

public abstract class Komponent {
    String producent;
    String model;
    String nazwa;
    double waga;
    double cena;

    public Komponent(String nazwa, double waga, double cena, String producent, String model) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
        this.producent = producent;
        this.model = model;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getWaga() {
        return waga;
    }

    public double getCena() {
        return cena;
    }

    public String getProducent() {
        return producent;
    }

    public String getModel() {
        return model;
    }
}
