package org.example;

public class Sprzeglo extends Komponent {
    static boolean stanSprzegla = false;

    public Sprzeglo(boolean stanSprzegla) {
        super(nazwa, waga, cena, producent, model);
        this.stanSprzegla = stanSprzegla;
    }

    public void wcisnij(){
        stanSprzegla = true;
    }

    public void zwolnij(){
        stanSprzegla = false;
    }
}
