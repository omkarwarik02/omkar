import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    private JTextField[] markFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculator(int numSubjects) {
        setTitle("Grade Calculator");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, numSubjects * 50);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numSubjects + 3, 2));
        markFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            panel.add(new JLabel("Subject " + (i + 1) + ":"));
            markFields[i] = new JTextField(5);
            panel.add(markFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        panel.add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        panel.add(totalMarksLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(averagePercentageLabel);
        panel.add(gradeLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalMarks = 0;

                for (int i = 0; i < numSubjects; i++) {
                    try {
                        totalMarks += Integer.parseInt(markFields[i].getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter valid marks for all subjects.");
                        return;
                    }
                }

                double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
                totalMarksLabel.setText("Total Marks: " + totalMarks);
                averagePercentageLabel.setText("Average Percentage: " + averagePercentage + "%");

                char grade;
                if (averagePercentage >= 90) {
                    grade = 'A';
                } else if (averagePercentage >= 80) {
                    grade = 'B';
                } else if (averagePercentage >= 70) {
                    grade = 'C';
                } else if (averagePercentage >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                gradeLabel.setText("Grade: " + grade);
            }
        });

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of subjects:"));
                new GradeCalculator(numSubjects);
            }
        });
    }
}
