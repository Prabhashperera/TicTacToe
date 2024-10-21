package com.assignment.tictactoe.service.game;

public class Winner {
    private Piece winningPiece;
    private int col1;
    private int row1;
    private int col2;
    private int row2;
    private int col3;
    private int row3;

    public Winner(Piece winningPiece, // Winner Piece
                  int row1, int col1, // 3 winning positions
                  int row2, int col2,
                  int row3, int col3) {
        this.winningPiece = winningPiece; // The Simbol that returns from BoardImpl (Check winner Method)
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }

}
