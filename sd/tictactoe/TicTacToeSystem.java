import java.util.*;

public class TicTacToeSystem {
    public static void main(String[] args) {
        new GameMain().play();
    }
}

class GameMain {

    Map<Piece, Player> players;
    Piece chance;
    Board board;
    GameState gameState;

    public GameMain() {
        this.gameState = GameState.IN_PROGRESS;
        this.chance = Piece.CROSS;
        this.board = new Board(3);
        this.players = new HashMap<>();
        players.put(Piece.CROSS, new Player(Piece.CROSS, board));
        players.put(Piece.NOUGHT, new Player(Piece.NOUGHT, board));
    }

    public void play() {
        while (this.gameState == GameState.IN_PROGRESS) {
            board.displayBoard();
            Move move = players.get(this.chance).getMove();
            boolean success = board.applyMove(move);
            if (success) {
                gameState = evaluateGameState();
                chance = chance == Piece.CROSS ? Piece.NOUGHT : Piece.CROSS;
            }
        }
        publishGameResult();
    }

    private GameState publishGameResult() {
        return this.gameState;
    }

    private GameState evaluateGameState() {
        return null;
    }

}

class Player {
    Piece piece;
    Board board;

    public Move getMove() {
        return null;
    }

    public Player(Piece piece, Board board) {
        this.piece = piece;
        this.board = board;
    }
}

class Move {
    Player player;
    Position fromPosition;
    Position toPosition;

    public Move(Player player, Position fromPosition, Position toPosition) {
        this.player = player;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

}

class Board {
    Piece[][] cells;

    public Board(int N) {
        cells = new Piece[N][N];
    }

    public void setPieceAtPosition(Piece piece, Position position) {
        cells[position.x][position.y] = piece;
    }

    public boolean applyMove(Move move) {
        if (isValidMove(move)) {
            setPieceAtPosition(move.player.piece, move.toPosition);
            return true;
        } else {
            return false;
        }
    }

    public void displayBoard() {
        System.out.println(Arrays.toString(cells));
    }

    private boolean isValidMove(Move move) {
        return false;
    }
}

class Position {
    int x;
    int y;
}

enum GameState {
    IN_PROGRESS, CROSS_WON, NOUGHT_WON, DRAW
}

enum Piece {
    CROSS, NOUGHT;

    @Override
    public String toString() {
        return super.toString();
    }
}