package com.assignment.tictactoe.service.controller;

import com.assignment.tictactoe.service.game.*;
import com.assignment.tictactoe.service.game.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUI {
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;

    BoardImpl board;
    HumanPlayer human;
    AIPlayer aiPlayer;

    boolean isGameOver = false;

    public BoardController() {
        board = new BoardImpl();
        human = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
    }

    public void boardBtnClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);

        // Human move
        if (!isGameOver) {
            if (board.isLegalMove(row, col)) {
                human.move(row, col);
                updateBoard(row, col, true); // Update UI for human move

                // Check for a winner after human's move
                if (board.checkWinner() != null) {
                    isGameOver = true;
                    NotifyWinner(Piece.X);
                    return;  // End the game if there's a winner
                }

                // AI move
                aiPlayer.findBestMove();
                Piece[][] pieces = board.getPieces(); // Get updated board after AI move

                // Find where AI placed its move and update UI
                for (int i = 0; i < pieces.length; i++) {
                    for (int j = 0; j < pieces[i].length; j++) {
                        if (pieces[i][j] == Piece.O && !getButtonByPosition(i, j).getText().equals("O")) {
                            updateBoard(i, j, false);  // Update UI for AI move
                            break;
                        }
                    }
                }
                // Check for a winner after AI's move
                if (board.checkWinner() != null) {
                    NotifyWinner(Piece.O);
                    isGameOver = true;
                    return;  // End the game if there's a winner
                }
            }
        }else {
            System.out.println("Game Over");
        }
    }

    @Override
    public void updateBoard(int row, int col, boolean isHuman) {
        String symbol = isHuman ? "X" : "O";
        Button button = getButtonByPosition(row, col);
        button.setText(symbol); // set the button text to Simbol
    }

    private Button getButtonByPosition(int row, int col) {
        if (row == 0 && col == 0) return one;
        if (row == 0 && col == 1) return two;
        if (row == 0 && col == 2) return three;
        if (row == 1 && col == 0) return four;
        if (row == 1 && col == 1) return five;
        if (row == 1 && col == 2) return six;
        if (row == 2 && col == 0) return seven;
        if (row == 2 && col == 1) return eight;
        if (row == 2 && col == 2) return nine;
        return null;
    }

    @Override
    public void NotifyWinner(Piece winner) {
        new Alert(Alert.AlertType.INFORMATION , (winner + " Won The Game!!")).show();
        // Implement notification logic for the winner
    }

    public void resetButtons() {
        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");
    }

    public void playAgainBtnClicked(ActionEvent event) {
        resetButtons();
        board.resetPieces();
        isGameOver = false;
    }
}
