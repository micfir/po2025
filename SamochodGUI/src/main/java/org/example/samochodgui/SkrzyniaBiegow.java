package org.example.samochodgui;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow = 6;
    private double aktualnePrzelozenie;

    private Sprzeglo sprzeglo;
    private Silnik silnik;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, String producent, String model, int iloscBiegow, Sprzeglo sprzeglo) {
        super(nazwa, waga, cena, producent, model);
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = sprzeglo;
        this.aktualnyBieg = 0;
        this.aktualnePrzelozenie = 1.0;
    }

    public void setSilnik(Silnik silnik) {
        this.silnik = silnik;
    }

    public void zwiekszBieg(){
        if (sprzeglo != null && sprzeglo.isStanSprzegla()) {
            if (aktualnyBieg < iloscBiegow) {

                int stareObroty = silnik.getObroty();

                aktualnyBieg++;
                aktualnePrzelozenie = obliczPrzelozenie(aktualnyBieg);
                if (aktualnyBieg > 1) {
                    int noweObroty = (int) (stareObroty * 0.7);
                    silnik.setObroty(noweObroty);
                }
            } else {
                System.out.println("Jest już najwyższy bieg.");
            }
        } else {
            System.out.println("Nie można zmienić biegu - wciśnij sprzęgło.");
        }
    }

    public void zmniejszBieg(){
        if (sprzeglo != null && sprzeglo.isStanSprzegla()) {
            if (aktualnyBieg > 0) {

                int stareObroty = silnik.getObroty();

                aktualnyBieg--;
                aktualnePrzelozenie = obliczPrzelozenie(aktualnyBieg);
                if (aktualnyBieg >= 0) {
                    int noweObroty = (int) (stareObroty * 1.5);
                    silnik.setObroty(noweObroty);
                }
            } else {
                System.out.println("Jest już najniższy bieg.");
            }
        } else {
            System.out.println("Nie można zmienić biegu - wciśnij sprzęgło.");
        }
    }

    private double obliczPrzelozenie(int bieg){
        if (bieg == 0) return 1.0;
        return 1.0 / bieg;
    }

    public int getAktBieg(){
        return aktualnyBieg;
    }

    public double getAktPrzelozenie(){
        return aktualnePrzelozenie;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }


    @Override
    public String toString() {
        return super.toString() + ", bieg=" + aktualnyBieg + "/" + iloscBiegow + ", przelozenie=" + aktualnePrzelozenie;
    }
}
