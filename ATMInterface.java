import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATMGUI extends JFrame {
    private BankAccount userAccount;

    private JLabel balanceLabel = new JLabel("Balance: $1000.0");
    private JTextField amountField = new JTextField(10);

    public ATMGUI(BankAccount account) {
        userAccount = account;

        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balanceLabel.setText("Balance: $" + userAccount.getBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                userAccount.deposit(amount);
                balanceLabel.setText("Balance: $" + userAccount.getBalance());
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (userAccount.withdraw(amount)) {
                    balanceLabel.setText("Balance: $" + userAccount.getBalance());
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance or invalid withdrawal amount.");
                }
            }
        });

        panel.add(balanceLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        add(panel);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initialize with a balance of $1000
        ATMGUI atmGUI = new ATMGUI(userAccount);
        atmGUI.setVisible(true);
    }
}
