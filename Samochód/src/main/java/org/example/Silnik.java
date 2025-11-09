package Samochód.src.main.java.org.example;

public class Silnik extends Komponent {
    private int obroty;
    private int maxObroty;

    public Silnik(String nazwa, double waga, double cena, String producent, String model, int maxObroty) {
        super(nazwa, waga, cena, producent, model);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom(){
        this.obroty = Math.min(2000, maxObroty);
        Samochod.setStanWlaczenia(true);
    }

    public void zatrzymaj(){
        this.obroty = 0;
        Samochod.setStanWlaczenia(false);
    }

    public void zwiekszObroty(){
        if (obroty < maxObroty) obroty++;
    }

    public void zmniejszObroty(){
        if (obroty > 0) obroty--;
    }

    public int getObroty() {
        return obroty;
    }

    public int getMaxObroty() {
        return maxObroty;
    }

    @Override
    public String toString() {
        return super.toString() + ", obroty=" + obroty + "/" + maxObroty;
    }
}
