package com.assignment.tictactoe.service.game;

public class BoardImpl implements Board {
    private Piece[][] pieces = new Piece[3][3];

    public BoardImpl() {
        initializeBoard();
        printBoard();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    @Override
    public Winner checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][0] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return new Winner(pieces[i][0], i, 0, i, 1, i, 2); // return Winner object with Winning positions
                //Horizontal Check Board 3x Times for 3 rows
            }
            if (pieces[0][i] == pieces[1][i] && pieces[0][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return new Winner(pieces[0][i], 0, i, 1, i, 2, i); // return Winner object with Winning positions
                //Check Board Vertically 3x Times for 3 Columns
            }
        }
        if (pieces[0][0] == pieces[1][1] && pieces[0][0] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
            //Check board Diagonally from Left Side
        }
        if (pieces[0][2] == pieces[1][1] && pieces[0][2] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
            //Check board Diagonally from Right Side
        }
        return null;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void resetPieces() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }
}
