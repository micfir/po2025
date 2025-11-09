package Samochód.src.main.java.org.example;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow = 6;
    private double aktualnePrzelozenie;

    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, String producent, String model, int iloscBiegow, Sprzeglo sprzeglo) {
        super(nazwa, waga, cena, producent, model);
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = sprzeglo;
        this.aktualnyBieg = 0;
        this.aktualnePrzelozenie = 1.0;
    }

    public void zwiekszBieg(){
        if (sprzeglo != null && sprzeglo.isStanSprzegla()) {
            if (aktualnyBieg < iloscBiegow) {
                aktualnyBieg++;
                aktualnePrzelozenie = obliczPrzelozenie(aktualnyBieg);
            } else {
                System.out.println("Jest już najwyższy bieg.");
            }
        } else {
            System.out.println("Nie można zmienić biegu — wciśnij sprzęgło.");
        }
    }

    public void zmniejszBieg(){
        if (sprzeglo != null && sprzeglo.isStanSprzegla()) {
            if (aktualnyBieg > 0) {
                aktualnyBieg--;
                aktualnePrzelozenie = obliczPrzelozenie(aktualnyBieg);
            } else {
                System.out.println("Jest już najniższy bieg.");
            }
        } else {
            System.out.println("Nie można zmienić biegu — wciśnij sprzęgło.");
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
