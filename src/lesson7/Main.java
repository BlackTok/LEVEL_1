package lesson7;

public class Main {
    public static void main(String[] args) {
        Plait plait = new Plait(30);
        System.out.println("В тарелке " + plait.getSize());
        Cat[] cats = {
                new Cat("Barsik",10),
                new Cat("Murzik",7),
                new Cat("Vaska",20),
                new Cat("Kuzya",2)
        };

        for (int i = 0; i < cats.length; i++) {
            cats[i].eating(plait);
        }
        System.out.println("Коты поели, стало " + plait.getSize());
        System.out.println();

        for (int i = 0; i < cats.length; i++) {
            String msg = "Cat " + cats[i].getName() + " " + (cats[i].isHungry() ? "Hungry" : "well-fed");
            System.out.println(msg);
        }
        System.out.println();

        plait.setSize(10);
        System.out.println("Добавили 10, стало " + plait.getSize());
    }
}
