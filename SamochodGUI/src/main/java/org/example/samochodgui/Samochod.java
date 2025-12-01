package org.example.samochodgui;

public class Samochod {
    private static boolean stanWlaczenia = false;
    private String nrRejest;
    private String model;
    private int predkoscMax;

    private Silnik silnik;
    private SkrzyniaBiegow  skrzynia;
    private Pozycja aktualnaPozycja;

    private int aktualnaPredkosc = 0;

    public Samochod(String nrRejest, String model, int predkoscMax, Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.nrRejest = nrRejest;
        this.model = model;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.aktualnaPozycja = pozycja;
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
        double suma = 0;
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
        /*
        Silnik silnik = new Silnik("Silnik V4", 100.0, 5000.0, "A", "V4-200", 6000);
        Sprzeglo sprzeglo = new Sprzeglo("Standardowe sprzęgło", 10.0, 200.0, "A", "B100", true);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia 6 biegów", 50.0, 1500.0, "A", "GB", 6, sprzeglo);
        Pozycja start = new Pozycja(0, 0);

        Samochod samochod = new Samochod("KR12345", "ModelX", 200, silnik, skrzynia, start);

        System.out.println(samochod);
        samochod.wlacz();
        System.out.println("Stan włączenia: " + Samochod.isStanWlaczenia());
        System.out.println("Obroty silnika: " + silnik.getObroty());
        sprzeglo.wcisnij();
        skrzynia.zwiekszBieg();
        System.out.println("Aktualny bieg: " + skrzynia.getAktBieg());
        samochod.jedzDo(new Pozycja(100,50));
        System.out.println("Pozycja: " + samochod.getAktPozycja());
        System.out.println("Prędkość: " + samochod.getAktPredkosc());
        System.out.println(samochod);
         */
    }
}
