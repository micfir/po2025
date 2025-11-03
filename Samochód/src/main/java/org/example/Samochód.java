package org.example;

public class Samochód {
    public static boolean stanWłączenia = false;
    String nrRejest;
    String model;
    int prędkośćMax;

    Silnik silnik;
    SkrzyniaBiegow skrzynia;
    Pozycja aktualnePozycja;

    public void włącz(){
        silnik.uruchom();
    }

    public void wyłącz(){
        silnik.zatrzymaj();
        skrzynia.aktualnyBieg = 0;
    }

    void jedźDo(Pozycja cel){

    }

    public double getWaga(){
        return 0;
    }

    public int getAktPredkosc(){
        return 0;
    }

    public double getAktPozycja(){
        return aktualnePozycja;
    }

    public Samochód()

    public static void main(String[] args){
        Samochód samochod = new Samochod();
    }
}
