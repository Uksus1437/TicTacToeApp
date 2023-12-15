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

    public static void print_settings() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------Настройки------");
        System.out.println("1. Изменить фигуру");
        System.out.println("2. Изменить первого игрока");
        System.out.println("3. Назад");
        int sw = scanner.nextInt();
        switch (sw) {
            case 1:
                System.out.println("1. Играть за нолики");
                System.out.println("2. Играть за крестики");
                System.out.println("3. Рандом");
                System.out.println("4. Назад");
                int SwF = scanner.nextInt();
                switch (SwF) {
                    case 1:
                        REAL_P = '0';
                        break;
                    case 2:
                        REAL_P = 'X';
                        break;
                    case 3:
                        rand_player();
                        break;
                    case 4:
                        print_settings();
                        break;
                }
                break;
            case 2:
                System.out.println("1. Ходить первым");
                System.out.println("2. Ходить вторым");
                System.out.println("3. Рандом");
                System.out.println("4. Назад");
                int SwT = scanner.nextInt();
                switch (SwT) {
                    case 1:
                        first = 'p';
                        break;
                    case 2:
                        first = 'c';
                        break;
                    case 3:
                        rand_turn();
                        break;
                    case 4:
                        print_settings();
                        break;
                }
        }
    }

    public static void rand_player() {
        Random p = new Random();
        if (p.nextInt(2) == 1) {
            COMP_P = '0';
            REAL_P = 'X';
        } else {
            COMP_P = 'X';
            REAL_P = '0';
        }
    }

    public static void rand_turn() {
        Random p = new Random();
        if (p.nextInt(2) == 1) first = 'p';
        else first = 'c';

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

    public static void play_game() {
        if (first == 'p') {
            boolean true_turn = false;

            Point point = new Point(0, 0);
            while ((!true_turn) && (!check_end())) {
                System.out.print("Введите координаты x, y:");
                Scanner n = new Scanner((System.in));
                point.x = n.nextInt();
                point.y = n.nextInt();
                if (((point.x < MAP_SIZE) && (point.x >= 0)) && ((point.y < MAP_SIZE) && (point.y >= 0))) {
                    if (map[point.x][point.y] == '*') {
                        true_turn = true;
                    }
                }
            }
            map[point.x][point.y] = REAL_P;
            comp_move();
        } else {
            comp_move();
            print_map();
            boolean true_turn = false;

            Point point = new Point(0, 0);
            while ((!true_turn) && (!check_end())) {
                System.out.print("Введите координаты x, y:");
                Scanner n = new Scanner((System.in));
                point.x = n.nextInt();
                point.y = n.nextInt();
                if ((map[point.x][point.y] == '*') && ((point.x < MAP_SIZE) && (point.x >= 0)) && ((point.y < MAP_SIZE) && (point.y >= 0)))
                    true_turn = true;
            }
            map[point.x][point.y] = REAL_P;
        }
    }

    public static boolean check_end() {
        if ((check_win(REAL_P)) || (check_win(COMP_P)) || (check_draw())) return true;
        else return false;
    }

    static List<PointsAndScores> rootsChildrenScores;


    public static void comp_move() {
        rootsChildrenScores = new ArrayList<>();
        minimax(0, 1);
        if (!check_end()) {
            map[getBestMove().x][getBestMove().y] = COMP_P;
            TicTacToeGame.buttons[TicTacToeApp.getBestMove().x][TicTacToeApp.getBestMove().y].setText("0");
        }
    }

    public static Point getBestMove() {
        int MAX = -100000;
        int best = -1;
        for (int i = 0; i < rootsChildrenScores.size(); ++i) {
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }
        return rootsChildrenScores.get(best).point;
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
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
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
