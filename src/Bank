import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Long, BankAccount> accounts = new HashMap<>();
    private long nextAccountNumber = 1001;
    private int nextCustomerId = 1;

    public BankAccount createAccount(String name, String phone, double initialDeposit)
            throws BankingException {
        if (name == null || name.trim().isEmpty()) {
            throw new BankingException("Customer name cannot be empty.");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new BankingException("Phone number cannot be empty.");
        }

        Customer customer = new Customer(nextCustomerId++, name.trim(), phone.trim());
        BankAccount account = new BankAccount(nextAccountNumber++, customer, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public BankAccount findAccount(long accountNumber) throws BankingException {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankingException("Account not found.");
        }
        return account;
    }

    public void transfer(long senderNumber, long receiverNumber, double amount)
            throws BankingException {
        BankAccount sender = findAccount(senderNumber);
        BankAccount receiver = findAccount(receiverNumber);
        sender.transferTo(receiver, amount);
    }

    public boolean hasAccounts() {
        return !accounts.isEmpty();
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n---------------- ALL ACCOUNTS ----------------");
        System.out.printf("%-12s %-20s %-15s %s%n", "Account No.", "Name", "Phone", "Balance");
        for (BankAccount account : accounts.values()) {
            System.out.printf("%-12d %-20s %-15s ₹%.2f%n",
                    account.getAccountNumber(),
                    account.getCustomer().getName(),
                    account.getCustomer().getPhone(),
                    account.getBalance());
        }
        System.out.println("-----------------------------------------------");
    }
}
