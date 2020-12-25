package lesson6;

public class Main {
    public static void main(String[] args) {
        Dog mops = new Dog("Matvey",250,0.5,10);
        Dog amstaff = new Dog("Luna",500,1.7,30);

        Cat meincoon = new Cat("Barsik",200,2);

        mops.run(550);
        amstaff.jump(1.2);

        meincoon.swim(1);
    }
}
