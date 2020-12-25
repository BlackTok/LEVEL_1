package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    /*
    * Беру разрешение экрана, высота окна равна половине высоты экрана
    * ширина равна 90% от высоты
    * нужно для того, чтобы на любом разрешение окно сохраняло пропорции относительно границ экрана
    */
    private static final int GLOBAL_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int GLOBAL_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int WIN_HEIGHT = GLOBAL_HEIGHT / 2;
    private static final int WIN_WIDTH = (int) (WIN_HEIGHT * 0.9);
    private static final int WIN_POS_X = GLOBAL_WIDTH / 2 - WIN_WIDTH / 2;
    private static final int WIN_POS_Y = GLOBAL_HEIGHT / 2 - WIN_HEIGHT / 2;

    private static SettingsWindow settingsWindow;
    private static Map field;
    private static HintWindow hintWindow;

    GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // создаем основное окно
        setTitle("Mega Game");
        setBounds(WIN_POS_X,WIN_POS_Y,WIN_WIDTH,WIN_HEIGHT);
        setResizable(false);

        // создаем хинт
        hintWindow = new HintWindow(this);

        // создаем окно настроек
        settingsWindow = new SettingsWindow(this);

        // создаем поле
        field = new Map();
        add(field, BorderLayout.CENTER);

        // создаем кнопки
        createButtons();

        setVisible(true);
    }

    void createButtons() {
        JPanel btnPanel = new JPanel(new GridLayout(1,2));

        JButton btnSettings = new JButton("New game");
        JButton btnExit = new JButton("Exit");

        btnPanel.add(btnSettings);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.SOUTH);

        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.btnSetDefaultSettings();
                settingsWindow.setVisible(true);
                hintWindow.setLabelText("");
                hintWindow.setVisible(false);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void startNewGame(int mode, int fieldWidth, int fieldHeight, int winSize) {
        field.startNewGame(mode, fieldWidth, fieldHeight, winSize, hintWindow);
    }
}
