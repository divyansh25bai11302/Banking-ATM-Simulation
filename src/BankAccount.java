import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private final long accountNumber;
    private final Customer customer;
    private double balance;
    private final List<Transaction> transactions;

    public BankAccount(long accountNumber, Customer customer, double initialDeposit)
            throws BankingException {
        if (initialDeposit < 0) {
            throw new BankingException("Initial deposit cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();

        if (initialDeposit > 0) {
            addTransaction("DEPOSIT", initialDeposit, "Initial deposit");
        }
    }

    public long getAccountNumber() { return accountNumber; }
    public Customer getCustomer() { return customer; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(double amount) throws BankingException {
        validatePositiveAmount(amount);
        balance += amount;
        addTransaction("DEPOSIT", amount, "Cash deposit");
    }

    public void withdraw(double amount) throws BankingException {
        validatePositiveAmount(amount);
        if (amount > balance) {
            throw new BankingException("Insufficient balance.");
        }
        balance -= amount;
        addTransaction("WITHDRAW", amount, "Cash withdrawal");
    }

    public void transferTo(BankAccount receiver, double amount) throws BankingException {
        if (receiver == null) {
            throw new BankingException("Receiver account not found.");
        }
        if (receiver.accountNumber == this.accountNumber) {
            throw new BankingException("Cannot transfer money to the same account.");
        }
        validatePositiveAmount(amount);
        if (amount > balance) {
            throw new BankingException("Insufficient balance.");
        }

        balance -= amount;
        receiver.balance += amount;
        addTransaction("TRANSFER OUT", amount,
                "Transfer to A/C " + receiver.accountNumber);
        receiver.addTransaction("TRANSFER IN", amount,
                "Transfer from A/C " + this.accountNumber);
    }

    private void validatePositiveAmount(double amount) throws BankingException {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new BankingException("Amount must be a valid positive number.");
        }
    }

    private void addTransaction(String type, double amount, String description) {
        transactions.add(new Transaction(type, amount, description, balance));
    }

    public void printAccountSummary() {
        System.out.println("\n---------- ACCOUNT SUMMARY ----------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer ID    : " + customer.getCustomerId());
        System.out.println("Customer Name  : " + customer.getName());
        System.out.println("Phone          : " + customer.getPhone());
        System.out.printf("Balance        : ₹%.2f%n", balance);
        System.out.println("-------------------------------------");
    }
}
