package lesson8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static GameWindow gameWindow;

    private static final int GLOBAL_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SETTINGS_HEIGHT = GLOBAL_HEIGHT / 3;
    private static final int SETTINGS_WIDTH = (int) (SETTINGS_HEIGHT * 0.9);

    private static final int FIELD_MIN_SIZE = 3;
    private static final int FIELD_MAX_SIZE = 10;
    private static final int WIN_MIN_SIZE = 3;
    private static final int WIN_MAX_SIZE = 8;

    private JRadioButton playerVsPlayer = new JRadioButton("Player VS Player",true);
    private JRadioButton playerVsAI = new JRadioButton("Player VS AI");
    private ButtonGroup gameModeButtons = new ButtonGroup();

    private JSlider fieldSliderW, fieldSliderH, winSlider;

    SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - SETTINGS_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - SETTINGS_HEIGHT / 2;

        setBounds(posX,posY,SETTINGS_WIDTH,SETTINGS_HEIGHT);
        setTitle("Game settings");
        setLayout(new GridLayout(10,1));

        addMode();
        addSlider();
        addButtons();
    }

    void addMode() {
        add(new JLabel("Choose a game mode:"));
        gameModeButtons.add(playerVsPlayer);
        gameModeButtons.add(playerVsAI);
        add(playerVsPlayer);
        add(playerVsAI);
    }

    void addSlider() {
        final JLabel fieldSizeLabelW = new JLabel("Field size horizontally: " + FIELD_MIN_SIZE);
        add(fieldSizeLabelW);
        fieldSliderW = new JSlider(FIELD_MIN_SIZE,FIELD_MAX_SIZE,FIELD_MIN_SIZE);
        add(fieldSliderW);
        fieldSliderW.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int maximum;
                if (fieldSliderW.getValue() > WIN_MAX_SIZE) {
                    winSlider.setMaximum(WIN_MAX_SIZE);
                } else {
                    maximum = (fieldSliderW.getValue() < fieldSliderH.getValue()) ? fieldSliderW.getValue() : fieldSliderH.getValue();
                    winSlider.setMaximum(maximum);
                }
                fieldSizeLabelW.setText("Field size W: " + fieldSliderW.getValue());
            }
        });

        final JLabel fieldSizeLabelH = new JLabel("Field size vertically: " + FIELD_MIN_SIZE);
        add(fieldSizeLabelH);
        fieldSliderH = new JSlider(FIELD_MIN_SIZE,FIELD_MAX_SIZE,FIELD_MIN_SIZE);
        add(fieldSliderH);
        fieldSliderH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int maximum;
                if (fieldSliderH.getValue() > WIN_MAX_SIZE) {
                    winSlider.setMaximum(WIN_MAX_SIZE);
                } else {
                    maximum = (fieldSliderW.getValue() < fieldSliderH.getValue()) ? fieldSliderW.getValue() : fieldSliderH.getValue();
                    winSlider.setMaximum(maximum);
                }
                fieldSizeLabelH.setText("Field size H: " + fieldSliderH.getValue());
            }
        });

        final JLabel winSizeLabel = new JLabel("Win size: " + WIN_MIN_SIZE);
        add(winSizeLabel);
        winSlider = new JSlider(WIN_MIN_SIZE,WIN_MIN_SIZE,WIN_MIN_SIZE);
        add(winSlider);
        winSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                winSizeLabel.setText("Win size: " + winSlider.getValue());
            }
        });
    }

    void addButtons() {
        JPanel btnPanel = new JPanel(new GridLayout(2,1));

        JButton btnStart = new JButton("Start");

        JPanel btnPanelDefAndCan = new JPanel(new GridLayout(1,2));
        JButton btnDefault = new JButton("Set default settings");
        JButton btnCancel = new JButton("Cancel");
        btnPanelDefAndCan.add(btnDefault);
        btnPanelDefAndCan.add(btnCancel);

        btnPanel.add(btnStart);
        btnPanel.add(btnPanelDefAndCan);

        add(btnPanel, BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartClick();
            }
        });

        btnDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSetDefaultSettings();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    void btnStartClick() {
        int gameMode = (playerVsPlayer.isSelected()) ? 0 : 1;
        int fieldSizeW = fieldSliderW.getValue();
        int fieldSizeH = fieldSliderH.getValue();
        int winSize = winSlider.getValue();

        setVisible(false);

        GameWindow.startNewGame(gameMode, fieldSizeW, fieldSizeH, winSize);
    }

    void btnSetDefaultSettings() {
        playerVsPlayer.setSelected(true);
        fieldSliderH.setValue(FIELD_MIN_SIZE);
        fieldSliderW.setValue(FIELD_MIN_SIZE);
    }
}
