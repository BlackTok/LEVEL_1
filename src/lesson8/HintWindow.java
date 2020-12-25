package lesson8;

import javax.swing.*;
import java.awt.*;

public class HintWindow extends JFrame {
    private static GameWindow gameWindow;

    private static final int GLOBAL_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SETTINGS_HEIGHT = GLOBAL_HEIGHT / 10;
    private static final int SETTINGS_WIDTH = (int) (SETTINGS_HEIGHT * 3);
    private static final String hint = "";
    private JLabel label;

    HintWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - SETTINGS_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - SETTINGS_HEIGHT / 2;

        setBounds(posX,posY,SETTINGS_WIDTH,SETTINGS_HEIGHT);
        setTitle("End Game");

        label = new JLabel(hint);
        add(label, BorderLayout.CENTER);
    }

    void setLabelText(String text) {
        label.setText(text);
    }
}
