package Lab1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> liczby = new HashSet<>();

        while (liczby.size() < 6) {
            int liczba = random.nextInt(49) + 1;
            liczby.add(liczba);
        }

        Scanner scanner = new Scanner(System.in);
        HashSet<Integer> liczbyUzytkownika = new HashSet<>();
        Integer wspolne = 0;

        System.out.print("Podaj 6 liczb Lotto od 1 do 49: ");
        while (liczbyUzytkownika.size() < 6) {
            Integer input = scanner.nextInt();
            if (input >= 1 && input <= 49) {
                liczbyUzytkownika.add(input);
                if (liczby.contains(input)) {
                    wspolne++;
                }
            }else {
                System.out.println("To nie jest poprawna liczba lotto podaj inna!");
            }
        }

        System.out.println("Wylosowane liczby Lotto:");
        for (int liczba : liczby) {
            System.out.print(liczba + " ");
        }
        System.out.println("\n");
        System.out.println("Ilosc trafien: " + wspolne);

    }
}
