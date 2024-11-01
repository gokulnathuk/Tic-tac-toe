import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 1400;
    int boardHeight = 1100; 

    JFrame frame = new JFrame("G-S Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    public TicTacToe() {
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textPanel.setLayout(new BorderLayout());
        textLabel.setText("Gokulnath");
        textPanel.add(textLabel, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(3, 3));
        initializeBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new JButton();
                board[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                board[row][col].setFocusPainted(false);
                board[row][col].setBackground(Color.LIGHT_GRAY);
                board[row][col].setActionCommand(row + " " + col);
                board[row][col].addActionListener(new ButtonClickListener());
                boardPanel.add(board[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (!clickedButton.getText().equals("")) {
                return; 
            }
            clickedButton.setText(currentPlayer);
            if (checkForWin()) {
                textLabel.setText("Player " + currentPlayer + " Wins!");
                disableButtons();
            } else if (isBoardFull()) {
                textLabel.setText("Over");
            } else {
                currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
                textLabel.setText("Player " + currentPlayer + "'s Turn");
            }
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(currentPlayer) && 
                board[i][1].getText().equals(currentPlayer) && 
                board[i][2].getText().equals(currentPlayer)) {
                return true;
            }
            if (board[0][i].getText().equals(currentPlayer) && 
                board[1][i].getText().equals(currentPlayer) && 
                board[2][i].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return (board[0][0].getText().equals(currentPlayer) && 
                board[1][1].getText().equals(currentPlayer) && 
                board[2][2].getText().equals(currentPlayer)) ||
               (board[0][2].getText().equals(currentPlayer) && 
                board[1][1].getText().equals(currentPlayer) && 
                board[2][0].getText().equals(currentPlayer));
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
