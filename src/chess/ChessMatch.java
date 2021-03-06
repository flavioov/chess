package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSteup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows() ; i++) {
            for (int j = 0; j < board.getColumns() ; j++) {
                //Down  casting
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return mat;
    }


    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source positon.");
        }

        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece.");
        }

    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSteup() {

        placeNewPiece('c', 1, new Rook(board, Colors.WHITE));
        placeNewPiece('c', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('d', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('e', 2, new Rook(board, Colors.WHITE));
        placeNewPiece('e', 1, new Rook(board, Colors.WHITE));
        placeNewPiece('d', 1, new King(board, Colors.WHITE));

        placeNewPiece('c', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('c', 8, new Rook(board, Colors.BLACK));
        placeNewPiece('d', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('e', 7, new Rook(board, Colors.BLACK));
        placeNewPiece('e', 8, new Rook(board, Colors.BLACK));
        placeNewPiece('d', 8, new King(board, Colors.BLACK));

    }
}
