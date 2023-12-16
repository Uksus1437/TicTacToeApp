import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class TicTacToeApp {
    static char[][] map;
    static final int MAP_SIZE = 3;

    static char COMP_P = '0';
    static char REAL_P = 'X';

    static char first = 'p';
    static String opponent = "bot";

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
        } if ((map[0][2] == player) && (map[1][1] == player) && (map[2][0] == player)) {
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

    public static boolean check_end() {
        if ((check_win(REAL_P)) || (check_win(COMP_P)) || (check_draw())) return true;
        else return false;
    }


    public static void comp_move() {
        TicTacToeGameWindow.rootsChildrenScores = new ArrayList<>();
        minimax(0, 1);
        if (!check_end()) {
            map[getBestMove().x][getBestMove().y] = COMP_P;
            TicTacToeGameWindow.buttons[getBestMove().x][getBestMove().y].setText(String.valueOf(COMP_P));
        }
    }

    public static Point getBestMove() {
        int MAX = -100000;
        int best = -1;
        for (int i = 0; i < TicTacToeGameWindow.rootsChildrenScores.size(); ++i) {
            if (MAX < TicTacToeGameWindow.rootsChildrenScores.get(i).score) {
                MAX = TicTacToeGameWindow.rootsChildrenScores.get(i).score;
                best = i;
            }
        }
        return TicTacToeGameWindow.rootsChildrenScores.get(best).point;
    }


    public static int minimax(int depth, int turn) {
        if (check_win(COMP_P)) return +1;
        if (check_win(REAL_P)) return -1;
        if (check_draw()) return 0;

        List<Point> possible_moves = possible_moves();
        if (possible_moves.isEmpty()) return 0;
        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < possible_moves.size(); i++) {
            Point point = possible_moves.get(i);
            if (turn == 1) {
                map[point.x][point.y] = COMP_P;
                int currentScore = minimax(+1, 2);
                scores.add(currentScore);

                if (depth == 0) {
                    TicTacToeGameWindow.rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                }
            } else if (turn == 2) {
                map[point.x][point.y] = REAL_P;
                scores.add(minimax(+1, 1));
            }
            map[point.x][point.y] = '*';
        }
        return turn == 1 ? retMax(scores) : retMin((scores));
    }

    public static int retMax(List<Integer> scores) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > max) {
                max = scores.get(i);
                index = i;
            }
        }
        return scores.get(index);
    }

    public static int retMin(List<Integer> scores) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) < min) {
                min = scores.get(i);
                index = i;
            }
        }
        return scores.get(index);
    }

    public static List<Point> possible_moves() {
        ArrayList<Point> pos_m = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == '*') {
                    pos_m.add(new Point(i, j));
                }
            }
        }
        return pos_m;
    }


}
