package lesson8;

import java.util.Random;

public class Player extends GameLogic {
    static Random random = new Random();

    private int mode, sizeWin, emptyDot, playerDot1, playerDot2;
    private static int[][] field;

    Player(int mode, int playerDot, int winSize, Map field) {
        super(mode, winSize, field);
        this.playerDot1 = playerDot;
        this.playerDot2 = playerDot * -1;
    }

    @Override
    void playerStep(int x, int y) {
        if (!checkMove(y, x)) return;

        setFieldCell(y, x, playerDot1);
    }

    @Override
    int getPlayerDot1() {
        return playerDot1;
    }
}
