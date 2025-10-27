package zadania;

import animals.*;
import java.util.Random;

public class Zoo {
    Animal[] animals = new Animal[100];

    public Zoo() {
        Random rand = new Random();

        for (int i = 0; i < animals.length; i++) {
            int choice = rand.nextInt(3);
            switch (choice) {
                case 0:
                    animals[i] = new Parrot("Parrot " + i);
                    break;
                case 1:
                    animals[i] = new Dog("Dog " + i);
                    break;
                case 2:
                    animals[i] = new Snake("Snake " + i);
                    break;
            }
        }
    }

    public int calculateTotalLegs() {
        int totalLegs = 0;
        for (Animal animal : animals) {
            totalLegs += animal.getLegs();
        }
        return totalLegs;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        for (Animal animal : zoo.animals) {
            System.out.println(animal.getDescription());
        }

        System.out.println(zoo.calculateTotalLegs());
    }
}
