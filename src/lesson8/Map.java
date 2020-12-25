package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    private int mode, fieldWidth, fieldHeight, winSize, cellWidth, cellHeight;
    private int[][] field;
    private boolean isInitialized = false;
    private static GameLogic player1, player2;
    private int step = 1;
    private int win = 0;
    private boolean fieldFull = false;
    private HintWindow hintWindow;

    Map() {
        setBackground(Color.GRAY);

        mouseListener();
    }

    void startNewGame(int mode, int fieldWidth, int fieldHeight, int winSize, HintWindow hintWindow) {
        this.mode = mode;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.winSize = winSize;
        this.cellWidth = getWidth() / fieldWidth;
        this.cellHeight = getHeight() / fieldHeight;
        this.field = new int[fieldHeight][fieldWidth];
        this.isInitialized = true;
        this.hintWindow = hintWindow;

        step = 1;
        win = 0;
        fieldFull = false;

        player1 = new Player(mode, 1, winSize, this);
        if (mode == 0) {
            player2 = new Player(mode, -1, winSize, this);
        } else {
            player2 = new Ai(mode, -1, winSize, this);
        }

        repaint();
    }

    void mouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    void update(MouseEvent e) {
        if (!isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        step(cellX, cellY);

        repaint();
    }

    void step(int x, int y) {
        if (win != 0 || fieldFull) return;

        if (player1.fullField()) {
            fieldFull = true;
            return;
        };

        if (step > 0 && player1.checkMove(y, x)) {
            stepPlayer(player1, x, y);
            checkWin(player1, "Player 1");

            if (mode == 1) {
                step *= -1;
                player2.aiStep();

                repaint();

                checkWin(player2, "AI");
            }
        } else if (step < 0 && player2.checkMove(y, x)) {
            stepPlayer(player2, x, y);
            checkWin(player2, "Player 2");
        }
    }

    void stepPlayer(GameLogic player, int x, int y) {
        player.playerStep(x, y);
        step *= -1;
        field[y][x] = player.getPlayerDot1();
    }

    void checkWin(GameLogic player, String playerText) {
        if (player.checkWin(player.getPlayerDot1())) {
            win = player.getPlayerDot1();
            hintWindow.setLabelText(playerText + " WIN!!!");
            hintWindow.setVisible(true);
        }
    }

    void paintX(int x, int y, Graphics g) {
        int posX1 = x * cellWidth;
        int posX2 = posX1 + cellWidth;
        int posY1 = y * cellHeight;
        int posY2 = posY1 + cellHeight;

        g.setColor(Color.BLUE);
        g.drawLine(posX1, posY1, posX2, posY2);
        g.drawLine(posX2, posY1, posX1, posY2);
    }

    void paintO(int x, int y, Graphics g) {
        int posX1 = x * cellWidth;
        int posX2 = posX1 + cellWidth;
        int posY1 = y * cellHeight;
        int posY2 = posY1 + cellHeight;

        g.setColor(Color.RED);
        g.drawOval(posX1, posY1, cellWidth, cellHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void render(Graphics g) {
        if (!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        for (int i = 0; i < fieldHeight; i++) {
            int y = i * cellHeight;
            g.drawLine(0,y,panelWidth,y);
        }

        for (int i = 0; i < fieldWidth; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0,x,panelHeight);
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] > 0) paintX(j, i, g);
                if (field[i][j] < 0) paintO(j, i, g);
            }
        }
    }

    public int[][] getField() {
        return field;
    }
}
