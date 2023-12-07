import java.util.Scanner;


public class TicTacToeApp {
    static char[][] map;
    static final int MAP_SIZE = 3;

    public static void main(String[] args) {
        init_map();
        print_map();
        while (!check_end()) {
            play_game();
            print_map();
        }
        if (check_win('X')) System.out.println("Игрок победил");
        else if (check_win('0')) System.out.println("Компьютер победил");
        else System.out.println("Ничья");
    }

    public static void init_map() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = '*';
            }
        }
    }

    public static void print_map() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean check_win(char player) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if ((map[i][0] == player) && (map[i][1] == player) && (map[i][2] == player)) {
                return true;
            }
        }

        for (int i = 0; i < MAP_SIZE; i++) {
            if ((map[0][i] == player) && (map[1][i] == player) && (map[2][i] == player)) {
                return true;
            }
        }

        if ((map[0][0] == player) && (map[1][1] == player) && (map[2][2] == player)) {
            return true;
        } else if ((map[0][2] == player) && (map[1][1] == player) && (map[2][0] == player)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean check_draw() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == '*') return false;
            }
        }
        return true;
    }

    public static void play_game() {
        boolean true_turn = false;

        int x = 0, y = 0;
        while ((!true_turn) && (!check_end())) {
            System.out.print("Введите координаты x, y:");
            Scanner n = new Scanner((System.in));
            x = n.nextInt();
            y = n.nextInt();
            if (map[x][y] == '*') true_turn = true;
        }
        map[x][y] = 'X';
        comp_move();
    }

    public static boolean check_end() {
        if ((check_win('X')) || (check_win('0')) || (check_draw())) return true;
        else return false;
    }

    public static void comp_move() {
        int[] move = getBestMove();
        map[move[0]][move[1]] = '0';
    }


    public static int minimax(int depth, boolean isMax) {
        if (check_win('X')) {
            return -1;
        } else if (check_win('O')) {
            return 1;
        } else if (check_draw()) return 0;

        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    if (map[i][j] == '*') {
                        map[i][j] = '0';
                        int score = minimax(depth + 1, false);
                        map[i][j] = '*';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    if (map[i][j] == '*') {
                        map[i][j] = 'X';
                        int score = minimax(depth + 1, true);
                        map[i][j] = '*';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public static int[] getBestMove() {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == '*') {
                    map[i][j] = '0';
                    int score = minimax(0, false);
                    map[i][j] = '*';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    public static int[][] possible_moves() {
        int[][] pm = new int[9][2];
        int c = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == '*') ;
                pm[c][0] = i;
                pm[c][1] = j;
            }
        }
        return pm;
    }


    void r() {
            return 1;
    }
}