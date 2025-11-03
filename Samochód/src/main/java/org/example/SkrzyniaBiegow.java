package org.example;

public class SkrzyniaBiegow extends Komponent {
    int aktualnyBieg;
    int iloscBiegow = 6;
    double aktualnePrzelozenie;

    Sprzeglo sprzeglo;

    public SkrzyniaBiegow(int aktualnyBieg, double aktualnePrzelozenie) {
        super(Sprzeglo.stanSprzegla)
        this.aktualnyBieg = aktualnyBieg;
        this.aktualnePrzelozenie = aktualnePrzelozenie;
    }

    public void zwiększBieg(){
        if (aktualnyBieg <= iloscBiegow){
            aktualnyBieg++;
        }
        else {
            System.out.println("Jest już najwyższy bieg.");;
        }
    }

    public void zmniejszBieg(){
        if (aktualnyBieg >= 1){
            aktualnyBieg--;
        }
        else {
            System.out.println("Jest już najniższy bieg.");;
        }
    }

    public int getAktBieg(){
        return aktualnyBieg;
    }

    public double getAktPrzelozenie(){
        return aktualnePrzelozenie;
    }
}
