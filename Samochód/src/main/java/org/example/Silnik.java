package org.example;

public class Silnik extends Komponent {
    int obroty;
    int maxObroty;

    public Silnik(int Obroty, int maxObroty) {
        super(nazwa, waga, cena, producent, model);
        this.obroty = Obroty;
        this.maxObroty = maxObroty;
    }

    public void uruchom(){
        obroty = 2000;
        Samochód.stanWłączenia = true;
    }

    public void zatrzymaj(){
        obroty = 0;
        Samochód.stanWłączenia = false;
    }

    public void zwiększObroty(){
        obroty++;
    }

    public void zmniejszObroty(){
        obroty--;
    }

}
