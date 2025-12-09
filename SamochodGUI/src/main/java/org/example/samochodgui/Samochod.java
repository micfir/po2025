package org.example.samochodgui;

public class Samochod {
    private static boolean stanWlaczenia = false;
    private String nrRejest;
    private String model;
    private int predkoscMax;
    private double wagaBazowa = 0.0;
    private Silnik silnik;
    private SkrzyniaBiegow  skrzynia;
    private Pozycja aktualnaPozycja;

    private int aktualnaPredkosc = 0;

    public Samochod(String nrRejest, String model, int predkoscMax, double wagaBazowa, Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.nrRejest = nrRejest;
        this.model = model;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.aktualnaPozycja = pozycja;
        this.wagaBazowa = wagaBazowa;
    }

    public void wlacz(){
        if (silnik != null) silnik.uruchom();
    }

    public void wylacz(){
        if (silnik != null) silnik.zatrzymaj();
        if (skrzynia != null) {
            while (skrzynia.getAktBieg() > 0) {
                if (skrzynia.getSprzeglo() != null) skrzynia.getSprzeglo().wcisnij();
                skrzynia.zmniejszBieg();
            }
        }
        aktualnaPredkosc = 0;
    }

    public void jedzDo(Pozycja cel){
        if (cel == null) return;
        double dx = cel.getX() - this.aktualnaPozycja.getX();
        double dy = cel.getY() - this.aktualnaPozycja.getY();
        this.aktualnaPozycja.aktualizujPozycje(dx, dy);
        if (stanWlaczenia) this.aktualnaPredkosc = Math.min(predkoscMax, predkoscMax/2);
        else this.aktualnaPredkosc = 0;
    }

    public double getWaga(){
        double suma = this.wagaBazowa;
        if (silnik != null) suma += silnik.getWaga();
        if (skrzynia != null) suma += skrzynia.getWaga();
        if (skrzynia != null && skrzynia.getSprzeglo() != null) suma += skrzynia.getSprzeglo().getWaga();
        return suma;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public String getModel() {
        return model;
    }

    public int getAktPredkosc(){
        return aktualnaPredkosc;
    }

    public Pozycja getAktPozycja(){
        return aktualnaPozycja;
    }

    public String getNrRejest() {
        return nrRejest;
    }

    public static void setStanWlaczenia(boolean stan) {
        stanWlaczenia = stan;
    }

    public static boolean isStanWlaczenia() {
        return stanWlaczenia;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public void przeliczPredkosc() {
        if (!stanWlaczenia) {
            aktualnaPredkosc = 0;
            return;
        }


        if (skrzynia != null && skrzynia.getSprzeglo() != null) {
            if (!skrzynia.getSprzeglo().isStanSprzegla()) {

                double nowaPredkosc = silnik.getObroty() * skrzynia.getAktBieg() * 0.008;
                this.aktualnaPredkosc = (int) nowaPredkosc;

            }
        }
    }



    @Override
    public String toString() {
        return "Samochod " + model + " [" + nrRejest + "]\n" +
                "Pozycja: " + aktualnaPozycja + "\n" +
                "Prędkość: " + aktualnaPredkosc + "/" + predkoscMax + "\n" +
                "Silnik: " + (silnik != null ? silnik : "brak") + "\n" +
                "Skrzynia: " + (skrzynia != null ? skrzynia : "brak") + "\n" +
                "Waga (części): " + getWaga() + "kg";
    }

    public static void main(String[] args) {

    }
}
