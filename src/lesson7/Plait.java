package lesson7;

public class Plait {
    public static final int FULL_SIZE = 50;
    private int size;

    public Plait(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size += size;
    }

    public void decreaseSize(Cat cat) {
        int appetite = cat.getAppetite();
        boolean isHungry = true;
        if (appetite <= size) {
            size -= appetite;
            isHungry = false;
        }

        cat.setHungry(isHungry);
    }
}
