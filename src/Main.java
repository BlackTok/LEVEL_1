public class Main {
    public static void main(String[] args) {
        // 1
        changeElement();

        // 2
        addElementToArray();

        // 3
        multiplyBySix();

        // 4
        oneDiagonal();

        // 5
        minAndMaxElements();

        // 6
        int[] array1 = {1,1,1,1,1,2,3};
        boolean bool = leftEqualsRight(array1);

        // 7
        int[] array2 = {1,2,3,4,5};
        int n = -1;
        displaceElement(array2, n);

    }

    // 1
    public static void changeElement() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                array[i] = 0;
            } else {
                array[i] = 1;
            }
        }
    }

    // 2
    public static void addElementToArray() {
        int[] array = new int[8];
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = count;
            count += 3;
        }
    }

    // 3
    public static void multiplyBySix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 4
    public static void oneDiagonal() {
        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j || i + j == 4) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 5
    public static void minAndMaxElements() {
        int[] array = {10,20,-5,155,0,6};
        int minE = array[0];
        int maxE = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxE) {
                maxE = array[i];
            } else if (array[i] < minE) {
                minE = array[i];
            }
        }
    }

    // 6
    public static boolean leftEqualsRight(int[] array) {
        int left = 0;
        int right = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j >= 0; j--) {
                left += array[j];
            }
            for (int j = i + 1; j < array.length; j++) {
                right += array[j];
            }

            if (left == right) {
                return true;
            }

            left = 0;
            right = 0;
        }

        return false;
    }

    // 7
    public static void displaceElement(int[] array, int n) {
        if (n == 0) {
            return;
        }

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int firstElement = array[0];
                array[0] = array[array.length - 1];

                for (int j = 1; j < array.length - 1; j++) {
                    array[array.length - j] = array[array.length - j - 1];
                }
                array[1] = firstElement;
            }

            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        } else {
            for (int i = 0; i > n; i--) {
                int firstElement = array[array.length - 1];
                array[array.length - 1] = array[0];

                for (int j = 1; j < array.length - 1; j++) {
                    array[j - 1] = array[j];
                }
                array[array.length - 2] = firstElement;
            }
        }
    }
}
