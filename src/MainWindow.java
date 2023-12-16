import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    public MainWindow() {
        setTitle("Крестики-Нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton playButton = new JButton("Играть");
        JButton settingsButton = new JButton("Настройки");
        JButton exitButton = new JButton("Выход");

        // Установка стилей для кнопок
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        playButton.setFont(buttonFont);
        settingsButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        playButton.setBackground(Color.LIGHT_GRAY);
        settingsButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setBackground(Color.LIGHT_GRAY);

        playButton.setForeground(Color.BLACK);
        settingsButton.setForeground(Color.BLACK);
        exitButton.setForeground(Color.BLACK);

        playButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);

        playButton.addActionListener(this);
        settingsButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

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
            TicTacToeGameWindow.PlayStart();
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
