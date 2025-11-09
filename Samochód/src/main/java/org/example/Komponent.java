package Samochód.src.main.java.org.example;

public abstract class Komponent {
    protected String producent;
    protected String model;
    protected String nazwa;
    protected double waga;
    protected double cena;

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

    @Override
    public String toString() {
        return nazwa + " (" + producent + " " + model + "), waga=" + waga + "kg, cena=" + cena;
    }
}
