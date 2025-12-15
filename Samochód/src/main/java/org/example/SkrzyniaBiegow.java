package org.example;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow = 6;
    private double aktualnePrzelozenie;

    private Sprzeglo sprzeglo;
    private Silnik silnik;

    private final double[] wspolczynnikiPredkosci = {0.0, 8.0, 16.0, 24.0, 32.0, 40.0, 48.0};

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
                double staryWsp = getAktualnyWspolczynnik();
                int stareObroty = silnik.getObroty();

                aktualnyBieg++;
                double nowyWsp = getAktualnyWspolczynnik();
                if (nowyWsp > 0) {
                    int noweObroty = (int) (stareObroty * (staryWsp / nowyWsp));
                    silnik.setObroty(noweObroty);
                }
            } else {
                throw new SamochodException("Nie można zwiększyć biegu. Skrzynia posiada maksymalnie " + iloscBiegow + " biegów.");
            }
        } else {
            throw new SamochodException("Nie można zmienić biegu - wciśnij sprzęgło.");
        }
    }

    public void zmniejszBieg(){
        if (sprzeglo != null && sprzeglo.isStanSprzegla()) {
            if (aktualnyBieg > 0) {
                double staryWsp = getAktualnyWspolczynnik();
                int stareObroty = silnik.getObroty();

                aktualnyBieg--;
                double nowyWsp = getAktualnyWspolczynnik();
                if (nowyWsp > 0) {
                    int noweObroty = (int) (stareObroty * (staryWsp / nowyWsp));
                    silnik.setObroty(noweObroty);
                }
            } else {
                throw new SamochodException("Nie można zmniejszyć biegu. Jest już najniższy bieg.");
            }
        } else {
            throw new SamochodException("Nie można zmienić biegu - wciśnij sprzęgło.");
        }
    }

    public double getAktualnyWspolczynnik() {
        if (aktualnyBieg >= 0 && aktualnyBieg < wspolczynnikiPredkosci.length) {
            return wspolczynnikiPredkosci[aktualnyBieg];
        }
        return 0.0;
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
