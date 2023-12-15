import javax.swing.*;
import java.awt.*;

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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adding padding to the main panel

        // Player Settings Panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Игроки"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Adding padding to player panel
        playersToggle = new JToggleButton("1 игрок");
        playersToggle.setPreferredSize(new Dimension(500, 100));
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
                BorderFactory.createTitledBorder("Опции игры"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Adding padding to game options panel

        choice1CheckBox = new JCheckBox("Крестики");
        choice2CheckBox = new JCheckBox("Нолики");

        choiceGroup = new ButtonGroup();
        choiceGroup.add(choice1CheckBox);
        choiceGroup.add(choice2CheckBox);

        gameOptionsPanel.add(choice1CheckBox);
        gameOptionsPanel.add(choice2CheckBox);
        mainPanel.add(gameOptionsPanel);

        // Turn Order Panel
        JPanel turnOrderPanel = new JPanel(new GridLayout(2, 1));
        turnOrderPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Порядок хода"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Adding padding to turn order panel

        choice3CheckBox = new JCheckBox("Первым");
        choice4CheckBox = new JCheckBox("Вторым");
        choiceGroup2 = new ButtonGroup();
        choiceGroup2.add(choice3CheckBox);
        choiceGroup2.add(choice4CheckBox);

        turnOrderPanel.add(choice3CheckBox);
        turnOrderPanel.add(choice4CheckBox);
        mainPanel.add(turnOrderPanel);
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> {
            new MainWindow();
            setVisible(false);
        });
        backButtonPanel.add(backButton);
        mainPanel.add(backButtonPanel);

        // Adding vertical gaps between panels
        mainPanel.add(Box.createVerticalStrut(10));
        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public static void settings() {
        SwingUtilities.invokeLater(() -> {
            new Settings();
        });
    }
}
