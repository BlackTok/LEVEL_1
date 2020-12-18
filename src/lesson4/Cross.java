package lesson4;

import java.util.Random;
import java.util.Scanner;

public class Cross {
    static char PLAYER_DOT = 'X';
    static char AI_DOT = '0';
    static char EMPTY_DOT = '.';

    static int SIZE_Y = 5;
    static int SIZE_X = 5;
    static int DOT_IN_ROW = 4;
    static char field[][] = new char[SIZE_Y][SIZE_X];

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }

            System.out.println("AI step");
            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("AI WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }

    public static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    public static void printField() {
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }

    public static void setFieldCell(int y, int x, char dot) {
        field[y][x] = dot;
    }

    public static boolean isFieldCell(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE_Y || x >= SIZE_X) {
            return false;
        }

        return field[y][x] == EMPTY_DOT;
    }

    public static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void playerStep() {
        int x,y;

        do {
            System.out.println("Введите координаты X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isFieldCell(y,x));

        setFieldCell(y,x,PLAYER_DOT);
    }

    public static void aiStep() {
        int x,y;
        int yx[] = {-1,-1};

        yx = checkPlayerDOT();

        if (yx[0] < 0) {
            do {
                System.out.println(32);
                x = random.nextInt(SIZE_X);
                y = random.nextInt(SIZE_Y);
            } while (!isFieldCell(y, x));
        } else {
            y = yx[0];
            x = yx[1];
        }
        setFieldCell(y,x,AI_DOT);
    }

    public static int[] checkPlayerDOT() {
        int countWinCombination = 1 + (SIZE_X / DOT_IN_ROW) * 2;
        int countPLAYERDotRow = 0;
        int countEMPTYDotRow = 0;
        int countPLAYERDotCol = 0;
        int countEMPTYDotCol = 0;
        int countPLAYERDotDiagLeft = 0;
        int countEMPTYDotDiagLeft = 0;
        int countPLAYERDotDiagRight = 0;
        int countEMPTYDotDiagRight = 0;

        int yx[] = {-1,-1};

        // строки
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    ++countEMPTYDotRow;
                } else if (field[i][j] == PLAYER_DOT) {
                    ++countPLAYERDotRow;
                }
                if (field[i][j] == AI_DOT) {
                    countPLAYERDotRow = 0;
                    countEMPTYDotRow = 0;
                }
                if (countPLAYERDotRow >= DOT_IN_ROW - 2 && (countEMPTYDotRow + countPLAYERDotRow >= DOT_IN_ROW)) {
                    for (int k = 0; k < SIZE_X; k++) {
                        if (field[i][k] == EMPTY_DOT) {
                            if (k > 0 && k < SIZE_X - 1) {
                                if (field[i][k - 1] == PLAYER_DOT || field[i][k + 1] == PLAYER_DOT){
                                    yx[0] = i;
                                    yx[1] = k;

                                    return yx;
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotRow = 0;
            countEMPTYDotRow = 0;
        }

        // столбцы
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[j][i] == EMPTY_DOT) {
                    ++countEMPTYDotCol;
                }
                if (field[j][i] == PLAYER_DOT) {
                    ++countPLAYERDotCol;
                }
                if (field[j][i] == AI_DOT) {
                    countPLAYERDotCol = 0;
                }
                if (countPLAYERDotCol >= DOT_IN_ROW - 2 && (countPLAYERDotCol + countEMPTYDotCol >= DOT_IN_ROW)) {
                    for (int k = 0; k < SIZE_Y; k++) {
                        if (field[k][i] == EMPTY_DOT) {
                            if (k > 0 && k < SIZE_Y - 1) {
                                if (field[k - 1][i] == PLAYER_DOT || field[k + 1][i] == PLAYER_DOT) {
                                    yx[0] = k;
                                    yx[1] = i;

                                    return yx;
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotCol = 0;
            countEMPTYDotCol = 0;
        }

        // диагональ левая
        for (int c = 1; c <= countWinCombination; c++) {
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i == j) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagLeft;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagLeft;
                        }
                        if (countPLAYERDotDiagLeft >= DOT_IN_ROW - 2 && (countPLAYERDotDiagLeft + countEMPTYDotDiagLeft == DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k > 0 && k < SIZE_Y - 1 && l > 0 && l < SIZE_X - 1) {
                                        if (k == l && field[k][l] == EMPTY_DOT && (field[k - 1][l - 1] == PLAYER_DOT || field[k + 1][l + 1] == PLAYER_DOT)) {
                                            yx[0] = k;
                                            yx[1] = l;

                                            return yx;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotDiagLeft = 0;
            countEMPTYDotDiagLeft = 0;
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i - j == c) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagLeft;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagLeft;
                        }
                        if (countPLAYERDotDiagLeft >= DOT_IN_ROW - 2 && (countPLAYERDotDiagLeft + countEMPTYDotDiagLeft == DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k > 0 && k < SIZE_Y - 1 && l > 0 && l < SIZE_X - 1) {
                                        if (k - l == c && field[k][l] == EMPTY_DOT && (field[k - 1][l - 1] == PLAYER_DOT || field[k + 1][l + 1] == PLAYER_DOT)) {
                                            yx[0] = k;
                                            yx[1] = l;

                                            return yx;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotDiagLeft = 0;
            countEMPTYDotDiagLeft = 0;
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i - j == -c) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagLeft;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagLeft;
                        }
                        if (countPLAYERDotDiagLeft >= DOT_IN_ROW - 2 && (countPLAYERDotDiagLeft + countEMPTYDotDiagLeft == DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k > 0 && k < SIZE_Y - 1 && l > 0 && l < SIZE_X - 1) {
                                        if (k - l == -c && field[k][l] == EMPTY_DOT && (field[k - 1][l - 1] == PLAYER_DOT || field[k + 1][l + 1] == PLAYER_DOT)) {
                                            yx[0] = k;
                                            yx[1] = l;

                                            return yx;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // диагональ правая
        for (int c = 1; c <= countWinCombination; c++) {
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i + j == SIZE_X - 1) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagRight;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagRight;
                        }
                        if (countPLAYERDotDiagRight >= DOT_IN_ROW - 2 && (countPLAYERDotDiagRight + countEMPTYDotDiagRight >= DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k > 0 && k < SIZE_Y - 1 && l > 0 && l < SIZE_X - 1) {
                                        if (k + l == SIZE_X - 1 && field[k][l] == EMPTY_DOT && (field[k + 1][l - 1] == PLAYER_DOT || field[k - 1][l + 1] == PLAYER_DOT)) {
                                            yx[0] = k;
                                            yx[1] = l;

                                            return yx;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotDiagRight = 0;
            countEMPTYDotDiagRight = 0;
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i + j == SIZE_X - c) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagRight;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagRight;
                        }
                        if (countPLAYERDotDiagRight >= DOT_IN_ROW - 2 && (countPLAYERDotDiagRight + countEMPTYDotDiagRight >= DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k + l == SIZE_X - c && field[k][l] == EMPTY_DOT) {
                                        yx[0] = k;
                                        yx[1] = l;

                                        return yx;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            countPLAYERDotDiagRight = 0;
            countEMPTYDotDiagRight = 0;
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
                    if (i + j == SIZE_X) {
                        if (field[i][j] == EMPTY_DOT) {
                            ++countEMPTYDotDiagRight;
                        }
                        if (field[i][j] == PLAYER_DOT) {
                            ++countPLAYERDotDiagRight;
                        }
                        if (countPLAYERDotDiagRight >= DOT_IN_ROW - 2 && (countPLAYERDotDiagRight + countEMPTYDotDiagRight >= DOT_IN_ROW)) {
                            for (int k = 0; k < SIZE_Y; k++) {
                                for (int l = 0; l < SIZE_X; l++) {
                                    if (k + l == SIZE_X && field[k][l] == EMPTY_DOT) {
                                        yx[0] = k;
                                        yx[1] = l;

                                        return yx;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return yx;
    }

    public static boolean checkWin(char dot) {
        int countDotRow = 0;
        int countDotCol = 0;
        int countDotDiagLeft = 0;
        int countDotDiagRight = 0;

        // строки
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == dot) {
                    ++countDotRow;
                    if (countDotRow == DOT_IN_ROW) {
                        return true;
                    }
                } else {
                    countDotRow = 0;
                }
            }
            countDotRow = 0;
        }

        // столбцы
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[j][i] == dot) {
                    ++countDotCol;
                    if (countDotCol == DOT_IN_ROW) {
                        return true;
                    }
                } else {
                    countDotCol = 0;
                }
            }
            countDotCol = 0;
        }

        // диагональ левая
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i == j) {
                    if (field[i][j] == dot) {
                        ++countDotDiagLeft;
                        if (countDotDiagLeft == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagLeft = 0;
                    }
                }
            }
        }
        countDotDiagLeft = 0;
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i - j == 1) {
                    if (field[i][j] == dot) {
                        ++countDotDiagLeft;
                        if (countDotDiagLeft == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagLeft = 0;
                    }
                }
            }
        }
        countDotDiagLeft = 0;
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i - j == -1) {
                    if (field[i][j] == dot) {
                        ++countDotDiagLeft;
                        if (countDotDiagLeft == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagLeft = 0;
                    }
                }
            }
        }

        // диагональ правая
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i + j == SIZE_X - 1) {
                    if (field[i][j] == dot) {
                        ++countDotDiagRight;
                        if (countDotDiagRight == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagRight = 0;
                    }
                }
            }
        }
        countDotDiagRight = 0;
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i + j == SIZE_X - 2) {
                    if (field[i][j] == dot) {
                        ++countDotDiagRight;
                        if (countDotDiagRight == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagRight = 0;
                    }
                }
            }
        }
        countDotDiagRight = 0;
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (i + j == SIZE_X) {
                    if (field[i][j] == dot) {
                        ++countDotDiagRight;
                        if (countDotDiagRight == DOT_IN_ROW) {
                            return true;
                        }
                    } else {
                        countDotDiagRight = 0;
                    }
                }
            }
        }

        return false;
    }
}
