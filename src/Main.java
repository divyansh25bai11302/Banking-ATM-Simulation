import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       BANKING & ATM SIMULATION");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> depositMoney();
                    case 3 -> withdrawMoney();
                    case 4 -> checkBalance();
                    case 5 -> transferMoney();
                    case 6 -> showTransactionHistory();
                    case 7 -> bank.listAccounts();
                    case 8 -> {
                        running = false;
                        System.out.println("Thank you for using Banking & ATM Simulation!");
                    }
                    default -> System.out.println("Invalid choice. Please select 1-8.");
                }
            } catch (BankingException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n============== MAIN MENU ==============");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Transfer Money");
        System.out.println("6. Transaction History");
        System.out.println("7. List All Accounts");
        System.out.println("8. Exit");
        System.out.println("========================================");
    }

    private static void createAccount() throws BankingException {
        System.out.println("\n---------- CREATE ACCOUNT ----------");
        String name = readText("Enter customer name: ");
        String phone = readText("Enter phone number: ");
        double initialDeposit = readDouble("Enter initial deposit: ₹");

        BankAccount account = bank.createAccount(name, phone, initialDeposit);
        System.out.println("Account created successfully!");
        System.out.println("Your account number is: " + account.getAccountNumber());
    }

    private static void depositMoney() throws BankingException {
        BankAccount account = getAccountFromInput();
        double amount = readDouble("Enter deposit amount: ₹");
        account.deposit(amount);
        System.out.printf("Deposit successful! Current balance: ₹%.2f%n", account.getBalance());
    }

    private static void withdrawMoney() throws BankingException {
        BankAccount account = getAccountFromInput();
        double amount = readDouble("Enter withdrawal amount: ₹");
        account.withdraw(amount);
        System.out.printf("Withdrawal successful! Current balance: ₹%.2f%n", account.getBalance());
    }

    private static void checkBalance() throws BankingException {
        BankAccount account = getAccountFromInput();
        account.printAccountSummary();
    }

    private static void transferMoney() throws BankingException {
        long sender = readLong("Enter sender account number: ");
        long receiver = readLong("Enter receiver account number: ");
        double amount = readDouble("Enter transfer amount: ₹");

        bank.transfer(sender, receiver, amount);
        System.out.println("Transfer successful!");
    }

    private static void showTransactionHistory() throws BankingException {
        BankAccount account = getAccountFromInput();
        System.out.println("\n---------- TRANSACTION HISTORY ----------");
        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }
        System.out.println("-----------------------------------------");
    }

    private static BankAccount getAccountFromInput() throws BankingException {
        long accountNumber = readLong("Enter account number: ");
        return bank.findAccount(accountNumber);
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid account number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric amount.");
            }
        }
    }
}
