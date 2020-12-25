package lesson8;

import java.util.Random;

public class Ai extends GameLogic {
    static Random random = new Random();

    private int mode, sizeWin, emptyDot, playerDot1, playerDot2;
    private int[][] field;

    Ai(int mode, int playerDot, int winSize, Map map) {
        super(mode, winSize, map);
        this.playerDot1 = playerDot;
        this.playerDot2 = playerDot * -1;
        this.field = map.getField();
        this.sizeWin = winSize;
    }

    @Override
    int getPlayerDot1() {
        return playerDot1;
    }

    @Override
    void aiStep() {
        int x, y;

        //блокировка ходов человека
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field[0].length; h++) {
                //анализ наличие поля для проверки
                if (h + sizeWin <= field[0].length) {                           //по горизонтале
                    if (checkLineHorisont(v, h, playerDot2) == sizeWin - 1) {
                        if (MoveAiLineHorisont(v, h, playerDot1)) return;
                    }

                    if (v - sizeWin > -2) {
                        if (checkDiaUp(v, h, playerDot2) == sizeWin - 1) {
                            if (MoveAiDiaUp(v, h, playerDot1)) return;
                        }
                    }
                    if (v + sizeWin <= field.length) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, playerDot2) == sizeWin - 1) {
                            if (MoveAiDiaDown(v, h, playerDot1)) return;
                        }
                    }
                }
                if (v + sizeWin <= field.length) {                       //по вертикале
                    if (checkLineVertical(v, h, playerDot2) == sizeWin - 1) {
                        if (MoveAiLineVertical(v, h, playerDot1)) return;
                    }
                }
            }
        }
        //игра на победу
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field[0].length; h++) {
                //анализ наличие поля для проверки
                if (h + sizeWin <= field[0].length) {                           //по горизонтале
                    if (checkLineHorisont(v, h, playerDot1) == sizeWin - 1) {
                        if (MoveAiLineHorisont(v, h, playerDot1)) return;
                    }

                    if (v - sizeWin > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, playerDot1) == sizeWin - 1) {
                            if (MoveAiDiaUp(v, h, playerDot1)) return;
                        }
                    }
                    if (v + sizeWin <= field.length) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, playerDot1) == sizeWin - 1) {
                            if (MoveAiDiaDown(v, h, playerDot1)) return;
                        }
                    }
                }
                if (v + sizeWin <= field.length) {                       //по вертикале
                    if (checkLineVertical(v, h, playerDot1) == sizeWin - 1) {
                        if (MoveAiLineVertical(v, h, playerDot1)) return;
                    }
                }
            }
        }

        //случайный ход
        do {
            y = random.nextInt(field.length);
            x = random.nextInt(field[0].length);
        } while (!checkMove(y, x));
        setFieldCell(y, x, playerDot1);

        field[y][x] = playerDot1;
    }

    boolean MoveAiLineHorisont(int v, int h, int dot) {
        for (int j = h; j < sizeWin; j++) {
            if ((field[v][j] == emptyDot)) {
                field[v][j] = dot;
                return true;
            }
        }
        return false;
    }

    boolean MoveAiLineVertical(int v, int h, int dot) {
        for (int i = v; i < sizeWin; i++) {
            if ((field[i][h] == emptyDot)) {
                field[i][h] = dot;
                return true;
            }
        }
        return false;
    }

    boolean MoveAiDiaUp(int v, int h, int dot) {
        for (int i = 0, j = 0; j < sizeWin; i--, j++) {
            if ((field[v + i][h + j] == emptyDot)) {
                field[v + i][h + j] = dot;
                return true;
            }
        }
        return false;
    }

    boolean MoveAiDiaDown(int v, int h, int dot) {
        for (int i = 0; i < sizeWin; i++) {
            if ((field[i + v][i + h] == emptyDot)) {
                field[i + v][i + h] = dot;
                return true;
            }
        }
        return false;
    }
}
