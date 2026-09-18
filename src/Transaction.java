import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final double amount;
    private final String description;
    private final LocalDateTime timestamp;
    private final double balanceAfter;

    public Transaction(String type, double amount, String description, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("%-12s | ₹%10.2f | %-28s | Balance: ₹%.2f | %s",
                type, amount, description, balanceAfter, timestamp.format(formatter));
    }
}
