import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame {
    private Random random = new Random();
    private int minNumber = 1;
    private int maxNumber = 100;
    private int maxAttempts = 10;
    private int targetNumber;
    private int attempts;
    private int totalScore;
    private int roundsWon;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel promptLabel;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    public NumberGame() {
        setTitle("Guessing Game");
        setLayout(new FlowLayout());

        promptLabel = new JLabel("Guess the number between " + minNumber + " and " + maxNumber + ": ");
        guessField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("Rounds won: " + roundsWon + " | Total Score: " + totalScore);

        add(promptLabel);
        add(guessField);
        add(submitButton);
        add(resultLabel);
        add(scoreLabel);

        targetNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        attempts = 0;

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    attempts++;

                    if (userGuess == targetNumber) {
                        resultLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        totalScore += maxAttempts - attempts + 1;
                        roundsWon++;
                        scoreLabel.setText("Rounds won: " + roundsWon + " | Total Score: " + totalScore);
                        submitButton.setEnabled(false);
                    } else if (userGuess < targetNumber) {
                        resultLabel.setText("Too low. Try again.");
                    } else {
                        resultLabel.setText("Too high. Try again.");
                    }

                    if (attempts >= maxAttempts) {
                        resultLabel.setText("Out of attempts. The correct number was " + targetNumber + ".");
                        submitButton.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a number.");
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGame();
            }
        });
    }
}
