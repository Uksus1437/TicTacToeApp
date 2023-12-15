import jdk.jfr.SettingControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MainWindow extends JFrame implements ActionListener {
    public MainWindow() {

        setTitle("Крестики-Нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton playButton = new JButton("Играть");
        JButton settingsButton = new JButton("Настройки");
        JButton exitButton = new JButton("Выход");

        playButton.addActionListener(this);
        settingsButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(playButton);
        panel.add(settingsButton);
        panel.add(exitButton);

        add(panel);


        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();


        if (command.equals("Играть")) {
            TicTacToeGame.PlayStart();
            setVisible(false);
        } else if (command.equals("Настройки")) {
            Settings.settings();
            setVisible(false);
        } else if (command.equals("Выход")) {
            System.out.println("Закрыть приложение");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
