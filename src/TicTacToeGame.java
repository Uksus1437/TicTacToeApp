import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {

    public static JButton[][] buttons;
    private boolean player1Turn;

    private boolean gameEnded;

    public TicTacToeGame() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        player1Turn = true;
        gameEnded = false;

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                buttons[row][col] = button;
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
                button.addActionListener(new ButtonClickListener(row, col));
                add(button);
            }
        }
    }

    private void checkForWinner_1() {
        // Check rows, columns, and diagonals for a winner
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

    private void announceWinner(String winner) {
        gameEnded = true;
        if (winner == "Ничья") {
            JOptionPane.showMessageDialog(this, winner);
        } else {
            JOptionPane.showMessageDialog(this, "Победитель: " + winner);
            resetGame();
        }
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        gameEnded = false;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty() && !gameEnded) {
                buttons[row][col].setText("X");
                TicTacToeApp.map[row][col] = 'X';
                buttons[TicTacToeApp.getBestMove().x][TicTacToeApp.getBestMove().y].setText(TicTacToeApp.COMP_P);
                checkForWinner_1();
            }
        }


        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                TicTacToeGame game = new TicTacToeGame();
                game.setVisible(true);
            });
        }
    }
}