package animals;

public abstract class Animal {
    String name;
    int legs;
    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }
    public Animal() {

    }
    public abstract String getDescription();

    public int getLegs() {
        return legs;
    }

    public void makeSound() {

    }
}
