package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {
    private Colors colors;

    public ChessPiece(Board board, Colors colors) {
        super(board);
        this.colors = colors;
    }

    public Colors getColor() {
        return colors;
    }

    public void setColor(Colors colors) {
        this.colors = colors;
    }

}
