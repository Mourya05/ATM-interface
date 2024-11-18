import javax.swing.*;
import java.awt.*;

class ATM_GUI {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;
    
    @SuppressWarnings("unused")
    public ATM_GUI(BankAccount account) {
        this.account = account;
        frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        
        balanceLabel = new JLabel("Current Balance: Rs. " + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setForeground(Color.green);
        
        amountField = new JTextField();
        amountField.setHorizontalAlignment(JTextField.CENTER);
        
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        
        withdrawButton.setBackground(Color.cyan);
        depositButton.setBackground(Color.cyan);
        checkBalanceButton.setBackground(Color.cyan);
        
        withdrawButton.setForeground(Color.black);
        depositButton.setForeground(Color.black);
        checkBalanceButton.setForeground(Color.black);
        
        withdrawButton.addActionListener(e -> withdraw());
        depositButton.addActionListener(e -> deposit());
        checkBalanceButton.addActionListener(e -> checkBalance());

        panel.add(balanceLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(checkBalanceButton);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && account.getBalance() >= amount) {
                account.withdraw(amount);
                JOptionPane.showMessageDialog(frame, "Withdrawn: Rs. " + amount, "Transaction", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance or invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateBalance();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid amount entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(frame, "Deposited: Rs. " + amount, "Transaction", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Invalid amount entered", "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateBalance();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid amount entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void checkBalance() {
        updateBalance();
        JOptionPane.showMessageDialog(frame, "Current Balance: Rs. " + account.getBalance(), "Balance", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateBalance() {
        balanceLabel.setText("Current Balance: Rs. " + account.getBalance());
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = Math.max(initialBalance, 0);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        new ATM_GUI(account);
    }
}
