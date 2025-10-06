package Lab1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lotto {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> liczby = new HashSet<>();

        while (liczby.size() < 6) {
            int liczba = random.nextInt(49) + 1;
            liczby.add(liczba);
        }

        System.out.println("Wylosowane liczby Lotto:");
        for (int liczba : liczby) {
            System.out.print(liczba + " ");
        }
    }
}

