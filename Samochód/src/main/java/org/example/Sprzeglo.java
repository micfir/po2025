package Samochód.src.main.java.org.example;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, double waga, double cena, String producent, String model, boolean stanPoczatkowy) {
        super(nazwa, waga, cena, producent, model);
        this.stanSprzegla = stanSprzegla;
    }

    public void wcisnij(){
        stanSprzegla = true;
    }

    public void zwolnij(){
        stanSprzegla = false;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }

    @Override
    public String toString() {
        return super.toString() + ", stanSprzegla=" + stanSprzegla;
    }
}
