package lesson6;

public abstract class Animal {
    String name;
    double run, jump, swim;

    Animal(String name, double run, double jump, double swim) {
        this.name = name;
        this.run = run;
        this.jump = jump;
        this.swim = swim;
    }

    abstract void run (double run);
    abstract void jump (double jump);
    abstract void swim (double swim);
}
