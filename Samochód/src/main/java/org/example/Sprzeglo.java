package org.example;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, double waga, double cena, String producent, String model, boolean stanPoczatkowy) {
        super(nazwa, waga, cena, producent, model);
        this.stanSprzegla = stanPoczatkowy;
    }

    public void wcisnij() throws SamochodException {
        if (stanSprzegla) {
            throw new SamochodException("Sprzęgło jest już wciśnięte.");
        }
        stanSprzegla = true;
    }

    public void zwolnij() throws SamochodException {
        if (!stanSprzegla) {
            throw new SamochodException("Sprzęgło jest już zwolnione.");
        }
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
