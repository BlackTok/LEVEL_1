package lesson6;

public class Dog extends Animal {
    Dog(String name, double run, double jump, double swim) {
        super(name, run, jump, swim);
    }

    @Override
    void run(double run) {
        System.out.println("Dog " + this.name + ", run: " + (run <= this.run));
    }

    @Override
    void jump(double jump) {
        System.out.println("Dog " + this.name + ", jump: " + (jump <= this.jump));
    }

    @Override
    void swim(double swim) {
        System.out.println("Dog " + this.name + ", swim: " + (swim <= this.swim));
    }
}
