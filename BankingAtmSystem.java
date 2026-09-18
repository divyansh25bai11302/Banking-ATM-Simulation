import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Account {
    private final String accountNumber;
    private final String holderName;
    private String pin;
    private double balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, String holderName, String pin, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(String.format("Account opened with deposit: $%.2f", initialDeposit));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void setPin(String newPin) {
        this.pin = newPin;
        this.transactionHistory.add("Security PIN updated");
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        transactionHistory.add(String.format("Deposited: $%.2f | Balance: $%.2f", amount, balance));
        System.out.printf("Successfully deposited $%.2f. Current balance: $%.2f%n", amount, balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return false;
        }
        balance -= amount;
        transactionHistory.add(String.format("Withdrew: $%.2f | Balance: $%.2f", amount, balance));
        System.out.printf("Successfully withdrew $%.2f. Remaining balance: $%.2f%n", amount, balance);
        return true;
    }

    public void printMiniStatement() {
        System.out.println("\n--- Transaction Statement ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (String record : transactionHistory) {
                System.out.println("- " + record);
            }
        }
        System.out.printf("Available Balance: $%.2f%n", balance);
    }
}

public class BankingAtmSystem {
    private static final Map<String, Account> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int accountCounter = 1001;

    public static void main(String[] args) {
        // Pre-populate with two sample accounts for testing
        accounts.put("1001", new Account("1001", "Alice Smith", "1234", 1500.00));
        accounts.put("1002", new Account("1002", "Bob Jones", "5678", 500.00));
        accountCounter = 1003;

        boolean running = true;
        while (running) {
            System.out.println("\n=================================");
            System.out.println("     BANK & ATM TERMINAL PORTAL  ");
            System.out.println("=================================");
            System.out.println("1. Open New Bank Account");
            System.out.println("2. Access ATM Services");
            System.out.println("3. Exit Terminal");
            System.out.print("Select an option (1-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    openNewAccount();
                    break;
                case "2":
                    atmLogin();
                    break;
                case "3":
                    System.out.println("Shutting down terminal. Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please enter 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    private static void openNewAccount() {
        System.out.println("\n--- Open New Account ---");
        System.out.print("Enter full legal name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Set a 4-digit PIN: ");
        String pin = scanner.nextLine().trim();

        if (pin.length() != 4 || !pin.matches("\\d+")) {
            System.out.println("Error: PIN must be exactly 4 numeric digits.");
            return;
        }

        System.out.print("Enter initial deposit amount ($): ");
        double deposit;
        try {
            deposit = Double.parseDouble(scanner.nextLine().trim());
            if (deposit < 0) {
                System.out.println("Initial deposit cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid currency value entered.");
            return;
        }

        String accNumber = String.valueOf(accountCounter++);
        Account newAcc = new Account(accNumber, name, pin, deposit);
        accounts.put(accNumber, newAcc);

        System.out.println("\nAccount registered successfully!");
        System.out.println("Your Account Number: " + accNumber);
        System.out.println("Please retain this number for ATM logins.");
    }

    private static void atmLogin() {
        System.out.println("\n--- ATM Authentication ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();

        Account account = accounts.get(accNum);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        int attempts = 3;
        boolean authenticated = false;
        while (attempts > 0) {
            System.out.print("Enter 4-digit PIN: ");
            String enteredPin = scanner.nextLine().trim();

            if (account.validatePin(enteredPin)) {
                authenticated = true;
                break;
            } else {
                attempts--;
                System.out.printf("Incorrect PIN. Attempts remaining: %d%n", attempts);
            }
        }

        if (authenticated) {
            runAtmSession(account);
        } else {
            System.out.println("Authentication failed. Session terminated.");
        }
    }

    private static void runAtmSession(Account account) {
        boolean sessionActive = true;
        System.out.printf("\nWelcome, %s!%n", account.getHolderName());

        while (sessionActive) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Fund Transfer");
            System.out.println("5. View Mini-Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. End ATM Session");
            System.out.print("Choose an action: ");

            String action = scanner.nextLine().trim();
            switch (action) {
                case "1":
                    System.out.printf("Current Balance: $%.2f%n", account.getBalance());
                    break;
                case "2":
                    System.out.print("Enter deposit amount: $");
                    double depAmount = readDoubleInput();
                    if (depAmount > 0) account.deposit(depAmount);
                    break;
                case "3":
                    System.out.print("Enter withdrawal amount: $");
                    double withAmount = readDoubleInput();
                    if (withAmount > 0) account.withdraw(withAmount);
                    break;
                case "4":
                    handleTransfer(account);
                    break;
                case "5":
                    account.printMiniStatement();
                    break;
                case "6":
                    changePin(account);
                    break;
                case "7":
                    System.out.println("Please collect your card/receipt. Session closed.");
                    sessionActive = false;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private static void handleTransfer(Account sender) {
        System.out.print("Enter recipient Account Number: ");
        String recipientNum = scanner.nextLine().trim();

        if (recipientNum.equals(sender.getAccountNumber())) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        Account recipient = accounts.get(recipientNum);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter amount to transfer: $");
        double transferAmount = readDoubleInput();

        if (transferAmount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        if (sender.withdraw(transferAmount)) {
            recipient.deposit(transferAmount);
            System.out.printf("Transferred $%.2f to %s (Acc: %s) successfully.%n",
                    transferAmount, recipient.getHolderName(), recipientNum);
        }
    }

    private static void changePin(Account account) {
        System.out.print("Enter new 4-digit PIN: ");
        String newPin = scanner.nextLine().trim();
        if (newPin.length() == 4 && newPin.matches("\\d+")) {
            account.setPin(newPin);
            System.out.println("PIN successfully changed.");
        } else {
            System.out.println("PIN update failed: Must be 4 numeric digits.");
        }
    }

    private static double readDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Must be a valid numerical value.");
            return -1;
        }
    }
}