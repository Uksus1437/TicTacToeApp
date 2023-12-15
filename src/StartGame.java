import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.Scanner;

public class StartGame {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int sw;
        do {
            System.out.println("1. Играть");
            System.out.println("2. Настройки");
            System.out.println("3. Выход");
            sw = scan.nextInt();
            System.out.print("\033[H\033[2J");
            switch (sw) {
                case 1:
                    SwingUtilities.invokeLater(() -> {
                        TicTacToeGame game = new TicTacToeGame();
                        game.setVisible(true);
                    });
                    System.out.println("Вы играете за " + TicTacToeApp.REAL_P);
                    TicTacToeApp.init_map();
                    if (TicTacToeApp.first == 'p') TicTacToeApp.print_map();
                    while (!TicTacToeApp.check_end()) {
                        if (TicTacToeApp.first == 'p') {
                            TicTacToeApp.play_game();
                            TicTacToeApp.print_map();
                        } else TicTacToeApp.play_game();
                    }
                    if (TicTacToeApp.check_win(TicTacToeApp.REAL_P)) System.out.println("Игрок победил");
                    else if (TicTacToeApp.check_win(TicTacToeApp.COMP_P)) System.out.println("Компьютер победил");
                    else System.out.println("Ничья");
                    break;
                case 2:
                    TicTacToeApp.print_settings();
                    break;
                case 3:
                    break;
            }
        } while (sw != 3);
    }
}
