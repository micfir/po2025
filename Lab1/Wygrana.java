package Lab1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Wygrana {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        Set<Integer> liczby = new HashSet<>();

        while (liczby.size() < 6) {
            int liczba = random.nextInt(49) + 1;
            liczby.add(liczba);
        }

        System.out.println("Wylosowane wygrywajace liczby Lotto:");
        for (int liczba : liczby) {
            System.out.print(liczba + " ");
        }

        Set<Integer> wygrana = new HashSet<>();

        while (liczby.containsAll(wygrana)) {
            while (wygrana.size() < 6) {
                int liczba = random.nextInt(49) + 1;
                wygrana.add(liczba);
            }
            System.out.println("Wylosowane liczby Lotto: " + wygrana);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("\nWygrana po: " + duration + " ms");

        }

    }
