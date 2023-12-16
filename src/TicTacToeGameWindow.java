import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class TicTacToeGameWindow extends JFrame {

    public static JButton[][] buttons;
    public static boolean player1Turn;

    private boolean gameEnded;
    public static List<PointsAndScores> rootsChildrenScores;

    public TicTacToeGameWindow() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3, 5, 5));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        buttons = new JButton[3][3];
        player1Turn = true;
        gameEnded = false;
        JMenuItem backButton = new JMenuItem("Назад");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainWindow();
                setVisible(false);
            }
        });
        menuBar.add(backButton);

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setBorder(new EmptyBorder(10, 10, 10, 10));
                buttons[row][col] = button;
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                button.addActionListener(new ButtonClickListener(row, col));
                add(button);
            }
        }
        if (TicTacToeApp.first == 'c') {
            TicTacToeApp.comp_move();
        }
    }

    private void checkForWinner_1() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                announceWinner(buttons[i][0].getText());
                return;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                announceWinner(buttons[0][i].getText());
                return;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            announceWinner(buttons[0][0].getText());
            return;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            announceWinner(buttons[0][2].getText());
            return;
        }

        // Check for a tie
        if (!gameEnded) {
            boolean tie = true;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().isEmpty()) {
                        tie = false;
                        break;
                    }
                }
            }
            if (tie) {
                announceWinner("Ничья");
            }
        }
    }

    public void announceWinner(String winner) {
        gameEnded = true;
        if (winner == "Ничья") {
            JOptionPane.showMessageDialog(this, winner);
            resetGame();
        } else {
            JOptionPane.showMessageDialog(this, "Победитель: " + winner);
            resetGame();
        }
    }

    private void resetGame() {
        TicTacToeApp.init_map();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        if (TicTacToeApp.first == 'c') {
            TicTacToeApp.comp_move();
        }
        gameEnded = false;
    }

    public class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (TicTacToeApp.opponent == "bot") {
                if (buttons[row][col].getText().isEmpty() && !gameEnded) {
                    buttons[row][col].setText(String.valueOf(TicTacToeApp.REAL_P));
                    TicTacToeApp.map[row][col] = TicTacToeApp.REAL_P;
                    TicTacToeApp.comp_move();
                    checkForWinner_1();
                }
            }else{
                if (buttons[row][col].getText().isEmpty() && !gameEnded) {
                    if(player1Turn == true) {
                        buttons[row][col].setText(String.valueOf(TicTacToeApp.REAL_P));
                        TicTacToeApp.map[row][col] = TicTacToeApp.REAL_P;
                    }else {
                        buttons[row][col].setText(String.valueOf(TicTacToeApp.COMP_P));
                        TicTacToeApp.map[row][col] = TicTacToeApp.COMP_P;
                    }
                    player1Turn = !player1Turn;
                    checkForWinner_1();
                }
            }
        }
    }


    public static void PlayStart() {
        TicTacToeApp.init_map();
        SwingUtilities.invokeLater(() -> {
            TicTacToeGameWindow game = new TicTacToeGameWindow();
            game.setVisible(true);
        });
    }
}
