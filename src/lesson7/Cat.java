package lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean hungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void eating(Plait plait) {
        plait.decreaseSize(this);
    }
}
