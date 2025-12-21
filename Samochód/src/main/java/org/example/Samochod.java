package org.example;

import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread {
    private volatile boolean stanWlaczenia = false;
    private String nrRejest;
    private String model;
    private int predkoscMax;
    private double wagaBazowa = 0.0;
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Pozycja aktualnaPozycja;
    private volatile int aktualnaPredkosc = 0;
    private Pozycja celPodrozy;

    private List<Listener> listeners = new ArrayList<>();

    public Samochod(String nrRejest, String model, int predkoscMax, double wagaBazowa, Silnik silnik, SkrzyniaBiegow skrzynia, Pozycja pozycja) {
        this.nrRejest = nrRejest;
        this.model = model;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.aktualnaPozycja = pozycja;
        this.wagaBazowa = wagaBazowa;
        if (this.skrzynia != null) {
            this.skrzynia.setSilnik(this.silnik);
        }
    }

    public void wlacz() throws SamochodException {
        if (silnik != null) silnik.uruchom();
        this.stanWlaczenia = true;
        notifyListeners();
    }

    public void wylacz() throws SamochodException {
        if (silnik != null) {
            silnik.zatrzymaj();
            this.stanWlaczenia = false;
        }
        if (skrzynia != null) {
            while (skrzynia.getAktBieg() > 0) {
                if (skrzynia.getSprzeglo() != null) {
                    if (!skrzynia.getSprzeglo().isStanSprzegla()) {
                        skrzynia.getSprzeglo().wcisnij();
                    }
                }
                skrzynia.zmniejszBieg();
            }
        }
        aktualnaPredkosc = 0;
        notifyListeners();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            przeliczPredkosc();

            if (celPodrozy != null) {
                double dx = celPodrozy.getX() - aktualnaPozycja.getX();
                double dy = celPodrozy.getY() - aktualnaPozycja.getY();
                double odleglosc = Math.sqrt(dx * dx + dy * dy);

                if (odleglosc < 5) {
                    aktualnaPozycja.aktualizujPozycje(dx, dy);
                    celPodrozy = null;
                } else {
                    double krok = this.aktualnaPredkosc / 200.0;
                    if (krok > 0) {
                        double ruchX = (dx / odleglosc) * krok;
                        double ruchY = (dy / odleglosc) * krok;

                        aktualnaPozycja.aktualizujPozycje(ruchX, ruchY);
                    }
                }
                notifyListeners();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void przeliczPredkosc() {
        if (!this.stanWlaczenia) {
            aktualnaPredkosc = 0;
            return;
        }

        if (skrzynia != null && skrzynia.getSprzeglo() != null) {
            if (!skrzynia.getSprzeglo().isStanSprzegla()) {
                double wspolczynnik = skrzynia.getAktualnyWspolczynnik();
                double obrotyTysiace = silnik.getObroty() / 1000.0;
                double nowaPredkosc = obrotyTysiace * wspolczynnik;
                this.aktualnaPredkosc = (int) Math.min(predkoscMax, nowaPredkosc);

            }
        }
    }

    public void jedzDo(Pozycja cel) {
        if (cel == null) return;
        this.celPodrozy = cel;
        System.out.println("Ustawiono cel na: " + cel);
    }

    public double getWaga() {
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

    public int getAktPredkosc() {
        return aktualnaPredkosc;
    }

    public Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public String getNrRejest() {
        return nrRejest;
    }

    public void setStanWlaczenia(boolean stan) {
        this.stanWlaczenia = stan;
    }

    public boolean isStanWlaczenia() {
        return this.stanWlaczenia;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
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

    //Same odwołania z notifyListeners()
    public void zwiekszBieg() throws SamochodException {
        if (skrzynia != null) {
            skrzynia.zwiekszBieg();
            notifyListeners();
        }
    }

    public void zmniejszBieg() throws SamochodException {
        if (skrzynia != null) {
            skrzynia.zmniejszBieg();
            notifyListeners();
        }
    }

    public void zwiekszObroty() throws SamochodException {
        if (!isStanWlaczenia()) {
            throw new SamochodException("Uruchom silnik!");
        }
        if (this.silnik != null) {
            this.silnik.zwiekszObroty();
            notifyListeners();
        }
    }

    public void zmniejszObroty() {
        if (this.silnik != null) {
            this.silnik.zmniejszObroty();
            notifyListeners();
        }
    }

    public void wcisnijSprzeglo() throws SamochodException {
        if (skrzynia != null && skrzynia.getSprzeglo() != null) {
            skrzynia.getSprzeglo().wcisnij();
            notifyListeners();
        }
    }

    public void zwolnijSprzeglo() throws SamochodException {
        if (skrzynia != null && skrzynia.getSprzeglo() != null) {
            skrzynia.getSprzeglo().zwolnij();
            notifyListeners();
        }
    }
}
