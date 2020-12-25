package lesson8;

public abstract class GameLogic {
    private int emptyDot;
    private static int mode,sizeWin;
    private static int[][] field;

    GameLogic(int mode, int winSize, Map field) {
        this.mode = mode;
        this.sizeWin = winSize;
        this.field = field.getField();
        this.emptyDot = 0;
    }

    boolean checkMove(int y, int x) {
        if (x < 0 || x >= field[0].length || y < 0 || y >= field.length) return false;
        else if (!(field[y][x] == emptyDot)) return false;

        return true;
    }

    void setFieldCell(int y, int x, int dot) {
        field[y][x] = dot;
    }

    boolean fullField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == emptyDot) return false;
            }
        }
        System.out.println("Игра закончилась в ничью");
        return true;
    }

    static boolean checkWin(int dot) {
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field[0].length; h++) {
                if (h + sizeWin <= field[0].length) {
                    if (checkLineHorisont(v, h, dot) >= sizeWin) return true;

                    if (v - sizeWin > -2) {
                        if (checkDiaUp(v, h, dot) >= sizeWin) return true;
                    }
                    if (v + sizeWin <= field.length) {
                        if (checkDiaDown(v, h, dot) >= sizeWin) return true;
                    }
                }
                if (v + sizeWin <= field.length) {
                    if (checkLineVertical(v, h, dot) >= sizeWin) return true;
                }
            }
        }
        return false;
    }

    static int checkDiaUp(int v, int h, int dot) {
        int count = 0;
        for (int i = 0, j = 0; j < sizeWin; i--, j++) {
            if ((field[v + i][h + j] == dot)) count++;
        }
        return count;
    }

    static int checkDiaDown(int v, int h, int dot) {
        int count = 0;
        for (int i = 0; i < sizeWin; i++) {
            if ((field[i + v][i + h] == dot)) count++;
        }
        return count;
    }

    static int checkLineHorisont(int v, int h, int dot) {
        int count = 0;
        for (int j = h; j < sizeWin + h; j++) {
            if ((field[v][j] == dot)) count++;
        }
        return count;
    }

    static int checkLineVertical(int v, int h, int dot) {
        int count = 0;
        for (int i = v; i < sizeWin + v; i++) {
            if ((field[i][h] == dot)) count++;
        }
        return count;
    }

    void playerStep(int x, int y) {}

    void aiStep() {}

    int getPlayerDot1() {
        return 0;
    }
}
