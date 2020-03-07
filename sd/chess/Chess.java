import java.util.*;

public class Chess {

    Map<Color, Player> players = new HashMap<>();
    Board board = new Board();

    public static void main(String[] args) {
        new Chess().play();
    }

    public void play() {

        Color chance = Color.WHITE;
        while (board.getBoardState() == BoardState.IN_PROGRESS) {
            Move move = players.get(chance).getMove();
            boolean successfulMove = false;
            try {
                board.tryMove(move);
                successfulMove = true;
            } catch (RuntimeException e) {

            }

            if (successfulMove) {
                chance = (chance == Color.BLACK) ? Color.WHITE : Color.BLACK;
            }

        }

    }
}

enum Color {
    BLACK, WHITE
}

class Player {

    Color color;

    public Move getMove() {
        return null;
    }

}

class Board {

    Piece[][] cell;
    BoardState state;
    MoveHistory moveHistory;
    PlayRule playRule;

    public Board() {
        state = BoardState.IN_PROGRESS;
        // set pieces
    }

    public BoardState getBoardState() {
        return state;
    }

    public void tryMove(Move move) {
        playRule.apply(this, move);
    }

    public Piece getPieceAt(Position position) {
        return cell[position.x][position.y];
    }

    public void playMove(Move move) {
        cell[move.from.x][move.from.y] = null;
        if (move.to != null) {
            cell[move.to.x][move.to.y] = move.piece;
        }

        moveHistory.addMove(move);
    }

    public void updateState() {
        // logic to get the state
    }

}

abstract class PlayRule {
    Validation validation;

    public void apply(Board board, Move move) {
        validation.validate(board, move);
        play(board, move);
    }

    public abstract void play(Board board, Move move);
}

class SimpleChessRule extends PlayRule {

    @Override
    public void play(Board board, Move move) {
        // castle

        // en passant

        // killing
        if (board.getPieceAt(move.to) != null) {
            board.playMove(new Move(move.piece, move.from, null));
            return;
        }
        board.playMove(move);
        board.updateState();
    }

}

interface Validation {

    public void validate(Board board, Move move);

}

class SimpleValidationRule implements Validation {

    Map<Type, Validation> pieceTypeValidation = new HashMap<>();

    @Override
    public void validate(Board board, Move move) {
        Validation validation = pieceTypeValidation.get(move.piece.type);
        validation.validate(board, move);
    }

    public void addValidationRule(Type type, Validation validation) {
        pieceTypeValidation.putIfAbsent(type, validation);
    }

}

class MoveHistory {
    private List<Move> moves = new ArrayList<>();

    public void addMove(Move move) {
        moves.add(move);
    }
}

enum BoardState {

    IN_PROGRESS, WHITE_WIN, BLACK_WIN, DRAW

}

class Move {
    Piece piece;
    Position from;
    Position to;

    public Move(Piece piece, Position from, Position to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }
}

class Position {
    int x;
    int y;
}

class Piece {
    Color color;
    Type type;
}

enum Type {
    PAWN, ROOK, KNIGHT, BISHOP, KING, QUEEN
}