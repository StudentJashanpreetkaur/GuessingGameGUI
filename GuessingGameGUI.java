import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private JLabel betLabel;
    private JTextField betField;
    private JLabel guessLabel;
    private JTextField guessField;
    private JButton playButton;
    private JTextArea resultArea;
    private int randomNumber;
    private int playerBet;
    private int totalMoney; // Keeps track of total money won or lost
    private int roundCounter; // Keeps track of the number of rounds played

    public GuessingGameGUI() {
        // Set up the JFrame properties
        setTitle("Guessing Game with Betting");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        // Initialize components
        betLabel = new JLabel("Enter your bet:");
        betField = new JTextField();
        guessLabel = new JLabel("Enter your guess (1-2):");
        guessField = new JTextField();
        playButton = new JButton("Play");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the JFrame
        add(betLabel);
        add(betField);
        add(guessLabel);
        add(guessField);
        add(playButton);
        add(resultArea);

        // Add ActionListener to the playButton
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame();
            }
        });
    }

    private void playGame() {
        if (roundCounter < 10) {
            // Get the player's bet and guess from the text fields
            try {
                playerBet = Integer.parseInt(betField.getText());
                int playerGuess = Integer.parseInt(guessField.getText());

                // Generate a random number between 1 and 2
                randomNumber = new Random().nextInt(2) + 1;

                // Determine if the player won or lost the bet
                if (playerGuess == randomNumber) {
                    totalMoney += playerBet; // Add the bet amount to totalMoney
                    resultArea.setText("Congratulations! You guessed correctly!\nYou won " + playerBet + " coins.\n");
                } else {
                    totalMoney -= playerBet; // Subtract the bet amount from totalMoney
                    resultArea.setText("Sorry, the correct number was " + randomNumber + ".\nYou lost " + playerBet + " coins.\n");
                }

                roundCounter++;

                // Display remaining rounds and total money
                resultArea.append("Rounds remaining: " + (10 - roundCounter) + "\nTotal Money: " + totalMoney + " coins.");
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter valid numbers for bet and guess.");
            }
        } else {
            // If 10 rounds are completed, show the final result and option to start a new round
            resultArea.setText("Game Over!\nTotal Money: " + totalMoney + " coins.\n");
            JButton newRoundButton = new JButton("Start New Round");
            newRoundButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    startNewRound();
                }
            });
            add(newRoundButton);
            newRoundButton.setEnabled(true); // enable the new round button 
        }
    }

    private void startNewRound() {
        // Reset the game for a new round
        roundCounter = 0;
        totalMoney = 0;
        resultArea.setText("");
        betField.setText("");
        guessField.setText("");
        playButton.setEnabled(true);
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI gui = new GuessingGameGUI();
            gui.setVisible(true);
        });
    }
}