public class StartGame {
    public static void main(String[] args) {
        TicTacToeApp.init_map();
        TicTacToeApp.print_map();
        while (!TicTacToeApp.check_end()) {
            TicTacToeApp.play_game();
            TicTacToeApp.print_map();
        }
        if (TicTacToeApp.check_win('X')) System.out.println("Игрок победил");
        else if (TicTacToeApp.check_win('0')) System.out.println("Компьютер победил");
        else System.out.println("Ничья");
    }
}
