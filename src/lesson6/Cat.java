package lesson6;

public class Cat extends Animal {
    Cat(String name, double run, double jump) {
        super(name, run, jump, 0);
    }

    @Override
    void run(double run) {
        System.out.println("Cat " + this.name + ", run: " + (run <= this.run));
    }

    @Override
    void jump(double jump) {
        System.out.println("Cat " + this.name + ", jump: " + (jump <= this.jump));
    }

    @Override
    void swim(double swim) {
        System.out.println("Cat " + this.name + ", swim: " + (swim <= this.swim));
    }
}
