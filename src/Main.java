public class Main {
    // 1
    public static void main(String[] args) {

        //2
        byte b1 = 10;
        short s1 = 1000;
        int i1 = 1000000;
        long l1 = 1000000000L;

        float f1 = 1000.5f;
        double d1 = 1000.5;

        char c1 = 'b';

        boolean bool = false;

        // 3
        float res1 = calcNumber(2,4,3.3f,8);

        // 4
        boolean res2 = checkNumber(11,10);

        // 5
        printCheckPositive(5);

        // 6
        boolean res3 = checkNegative(10);

        // 7
        printHello("Алексей");

        // 8
        printYear(204);

    }

    public static float calcNumber(float a, float b, float c, float d) {

        float res = a * (b + (c / d));

        return res;

    }

    public static boolean checkNumber(float a, float b) {

        float res = a + b;
        if (res >= 10 && res <= 20) {
            return true;
        }

        return  false;

    }

    public static void printCheckPositive(int a) {

        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }

    }

    public static boolean checkNegative(int a) {

        if (a < 0) {
            return true;
        }

        return false;

    }

    public static void printHello(String name) {

        String msg = "Привет, ";
        System.out.println(msg + name);

    }

    public static void printYear(int year) {

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    System.out.println("Год високосный!");
                } else {
                    System.out.println("Год не високосный!");
                }
            } else {
                System.out.println("Год високосный!");
            }
        }

    }

}
