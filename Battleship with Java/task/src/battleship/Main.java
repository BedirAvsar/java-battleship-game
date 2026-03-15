import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static char[][] player1Board = new char[10][10];
    static char[][] player1Fog = new char[10][10];

    static char[][] player2Board = new char[10][10];
    static char[][] player2Fog = new char[10][10];

    public static void main(String[] args) {

        initBoards();

        System.out.println("Player 1, place your ships on the game field");
        printBoard(player1Board);
        placeAllShips(player1Board);

        passMove();

        System.out.println("Player 2, place your ships on the game field");
        printBoard(player2Board);
        placeAllShips(player2Board);

        passMove();

        while (true) {

            playerTurn(1, player2Board, player1Fog, player1Board);

            if (allShipsSunk(player2Board)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }

            passMove();

            playerTurn(2, player1Board, player2Fog, player2Board);

            if (allShipsSunk(player1Board)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }

            passMove();
        }
    }

    static void initBoards() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player1Board[i][j] = '~';
                player1Fog[i][j] = '~';
                player2Board[i][j] = '~';
                player2Fog[i][j] = '~';
            }
        }
    }

    static void passMove() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        scanner.nextLine();
    }

    static void playerTurn(int player, char[][] enemyBoard, char[][] fog, char[][] ownBoard) {

        printBoard(fog);
        System.out.println("---------------------");
        printBoard(ownBoard);

        System.out.println("Player " + player + ", it's your turn:");

        String shot = scanner.next();
        int r = shot.charAt(0) - 'A';
        int c = Integer.parseInt(shot.substring(1)) - 1;

        if (enemyBoard[r][c] == 'O') {

            enemyBoard[r][c] = 'X';
            fog[r][c] = 'X';

            if (shipSunk(enemyBoard, r, c)) {
                System.out.println("You sank a ship!");
            } else {
                System.out.println("You hit a ship!");
            }

        } else {

            if (enemyBoard[r][c] == '~') {
                enemyBoard[r][c] = 'M';
                fog[r][c] = 'M';
            }

            System.out.println("You missed!");
        }
    }

    static void printBoard(char[][] map) {

        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {

            System.out.print((char) ('A' + i) + " ");

            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }
    }

    static void placeAllShips(char[][] board) {

        placeShip(board, "Aircraft Carrier", 5);
        placeShip(board, "Battleship", 4);
        placeShip(board, "Submarine", 3);
        placeShip(board, "Cruiser", 3);
        placeShip(board, "Destroyer", 2);
    }

    static int[] parse(String s) {
        int r = s.charAt(0) - 'A';
        int c = Integer.parseInt(s.substring(1)) - 1;
        return new int[]{r, c};
    }

    static boolean tooClose(char[][] board, int r, int c) {

        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {

                if (i >= 0 && i < 10 && j >= 0 && j < 10) {

                    if (board[i][j] == 'O') {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static void placeShip(char[][] board, String name, int size) {

        while (true) {

            System.out.println("Enter the coordinates of the " + name + " (" + size + " cells):");

            String a = scanner.next();
            String b = scanner.next();

            int[] p1 = parse(a);
            int[] p2 = parse(b);

            int r1 = p1[0];
            int c1 = p1[1];

            int r2 = p2[0];
            int c2 = p2[1];

            if (r1 != r2 && c1 != c2) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            int length = (r1 == r2)
                    ? Math.abs(c1 - c2) + 1
                    : Math.abs(r1 - r2) + 1;

            if (length != size) {
                System.out.println("Error! Wrong length of the " + name + "! Try again:");
                continue;
            }

            boolean close = false;

            if (r1 == r2) {

                for (int i = Math.min(c1, c2); i <= Math.max(c1, c2); i++) {

                    if (tooClose(board, r1, i)) {
                        close = true;
                        break;
                    }
                }

                if (close) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    continue;
                }

                for (int i = Math.min(c1, c2); i <= Math.max(c1, c2); i++) {
                    board[r1][i] = 'O';
                }

            } else {

                for (int i = Math.min(r1, r2); i <= Math.max(r1, r2); i++) {

                    if (tooClose(board, i, c1)) {
                        close = true;
                        break;
                    }
                }

                if (close) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    continue;
                }

                for (int i = Math.min(r1, r2); i <= Math.max(r1, r2); i++) {
                    board[i][c1] = 'O';
                }
            }

            printBoard(board);
            break;
        }
    }

    static boolean shipSunk(char[][] board, int r, int c) {

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {

            int nr = r + d[0];
            int nc = c + d[1];

            while (nr >= 0 && nr < 10 && nc >= 0 && nc < 10) {

                if (board[nr][nc] == 'O') {
                    return false;
                }

                if (board[nr][nc] == '~') {
                    break;
                }

                nr += d[0];
                nc += d[1];
            }
        }

        return true;
    }

    static boolean allShipsSunk(char[][] board) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (board[i][j] == 'O') {
                    return false;
                }
            }
        }

        return true;
    }
}