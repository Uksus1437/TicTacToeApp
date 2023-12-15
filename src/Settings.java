import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {
    private JCheckBox choice1CheckBox, choice2CheckBox, choice3CheckBox, choice4CheckBox;
    private JToggleButton playersToggle;
    private JLabel choiceLabel;
    private ButtonGroup choiceGroup, choiceGroup2;

    public Settings() {
        setTitle("Игра");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Player Settings Panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Игроки"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        playersToggle = new JToggleButton("1 игрок");
        playersToggle.addItemListener(e -> {
            if (playersToggle.isSelected()) {
                playersToggle.setText("2 игрока");
            } else {
                playersToggle.setText("1 игрок");
            }
        });
        playerPanel.add(playersToggle);
        mainPanel.add(playerPanel);

        // Game Options Panel
        JPanel gameOptionsPanel = new JPanel(new GridLayout(2, 1));
        gameOptionsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Играть за"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        choice1CheckBox = new JCheckBox("Крестики");
        choice2CheckBox = new JCheckBox("Нолики");

        choiceGroup = new ButtonGroup();
        choiceGroup.add(choice1CheckBox);
        choiceGroup.add(choice2CheckBox);

        gameOptionsPanel.add(choice1CheckBox);
        gameOptionsPanel.add(choice2CheckBox);
        mainPanel.add(gameOptionsPanel);

        choice1CheckBox.setSelected(true);

        // Turn Order Panel
        JPanel turnOrderPanel = new JPanel(new GridLayout(2, 1));
        turnOrderPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Порядок хода"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        choice3CheckBox = new JCheckBox("Первым");
        choice4CheckBox = new JCheckBox("Вторым");
        choiceGroup2 = new ButtonGroup();
        choiceGroup2.add(choice3CheckBox);
        choiceGroup2.add(choice4CheckBox);

        turnOrderPanel.add(choice3CheckBox);
        turnOrderPanel.add(choice4CheckBox);
        mainPanel.add(turnOrderPanel);

        choice3CheckBox.setSelected(true);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> {
            new MainWindow();
            setVisible(false);
        });
        backButtonPanel.add(backButton);
        mainPanel.add(backButtonPanel);

        mainPanel.add(Box.createVerticalStrut(10));
        getContentPane().add(mainPanel);

        setVisible(true);

        choice1CheckBox.addActionListener(e -> {
            if (choice1CheckBox.isSelected()) {
                TicTacToeApp.REAL_P = 'X';
                TicTacToeApp.COMP_P = '0';
            }
        });

        choice2CheckBox.addActionListener(e -> {
            if (choice2CheckBox.isSelected()) {
                TicTacToeApp.REAL_P = '0';
                TicTacToeApp.COMP_P = 'X';
            }
        });

        choice3CheckBox.addActionListener(e -> {
            if (choice3CheckBox.isSelected()) {
                TicTacToeApp.first = 'p';
            }
        });

        choice4CheckBox.addActionListener(e -> {
            if (choice4CheckBox.isSelected()) {
                TicTacToeApp.first = 'c';
            }
        });

        playersToggle.addActionListener(e -> {
            if (playersToggle.isSelected()) {
                TicTacToeApp.opponent = "person";
            }
        });
    }

    public static void settings() {
        SwingUtilities.invokeLater(() -> {
            new Settings();
        });
    }
}
