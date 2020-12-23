package additionally_HW_1;

import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //randomArray();

        staticArray();
    }

    public static void randomArray() {
        int arrLength = 15;
        int maxRandom = 20;

        int arr1[] = new int[arrLength];
        int arr2[] = new int[maxRandom + 1];

        Random num = new Random();

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = num.nextInt(maxRandom) + 1;
        }

        for (int i = 0; i < arr1.length; i++) {
            ++arr2[arr1[i]];
        }

        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] > 0)
                System.out.println("Число " + i + " встретилось " + arr2[i] + " раз(а)");
        }
    }

    public static void staticArray() {
        int[] arr1,arr2;
        arr1 = new int[] {5, 1, 5, 2, 2, 4, 1, 4, 5, 1, 5, 3, 2, 4, 4, 4, 5, 1, 3, 4, 2, 2, 1, 2, 4,
                4, 4, 5, 4, 3, 5, 4, 4, 5, 5, 1, 4, 1, 5, 3, 1, 4, 5, 3, 3, 4, 2, 2, 4, 4, 5, 5,
                1, 1, 1, 4, 5, 5, 4, 4, 2, 4, 3, 1, 3, 3, 1, 1, 3, 1, 3, 4, 4, 3, 2, 2, 1, 3, 4,
                4, 2, 3, 4, 2, 4, 4, 1, 4, 4, 4, 2, 1, 2, 4, 1, 5, 2, 2, 5, 4, 2, 2, 3, 1, 5, 5,
                3, 5, 3, 1, 4, 5, 4, 2, 1, 3, 1, 2, 1, 4, 1, 3, 4, 2, 2, 5, 2, 3, 1, 1, 2, 3, 3,
                4, 4, 2, 4, 1, 2, 2, 2, 5, 1, 5, 1, 2, 2, 1, 3, 3, 4, 3, 5, 3, 5, 1, 2, 1, 3, 3,
                2, 4, 1, 4, 3, 5, 1, 2, 1, 2, 3, 2, 1, 3, 2, 2, 4, 3, 2, 1, 5, 1, 4, 5, 4, 4, 5,
                5, 4, 2, 3, 5, 1, 3, 4, 3, 2, 4, 5, 2, 5, 2, 4, 1, 4, 5, 2, 3, 3, 4, 4, 3, 5, 2,
                2, 3, 5, 1, 2, 4, 3, 4, 4, 3, 2, 2, 1, 4, 5, 5, 1, 5, 2, 4, 5, 5, 4, 2, 2, 1, 5,
                1, 3, 4, 2, 4, 2, 2, 4, 3, 5, 2, 2, 4, 4, 4, 5, 5, 2, 5, 5, 2, 5, 1, 1, 5, 5, 4,
                1, 2, 4, 1, 2, 2, 5, 4, 5, 1, 5, 4, 4, 5, 5, 5, 3, 3, 4, 3, 3, 5, 3, 2, 2, 2, 2,
                2, 1, 2, 5, 2, 3, 4, 3, 5, 5, 2, 4, 5, 3, 4, 3, 1, 3, 2, 1, 1, 5, 4, 4, 2, 3, 1,
                3, 4, 2, 4, 1, 3, 5, 1, 5, 3, 5, 2, 3, 4, 4, 1, 3, 1, 5, 5, 1, 2, 2, 1, 3, 1, 5,
                1, 2, 2, 1, 5, 1, 3, 3, 2, 1, 3, 2, 5, 1, 1, 2, 3, 5, 5, 4, 3, 1, 3, 3, 1, 5, 4,
                2, 3, 4
        };
        int maxNum = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            maxNum = Math.max(maxNum, arr1[i]);
        }

        arr2 = new int[maxNum];
        for (int i = 0; i < arr1.length; i++) {
            ++arr2[arr1[i] - 1];
        }

        for (int i = 0; i < arr2.length; i++) {
            System.out.println("Число " + (i + 1) + " встречается " + arr2[i] + " раз(а)");
        }
    }
}
