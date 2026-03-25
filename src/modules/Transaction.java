package modules;

import java.time.LocalDateTime;

public class Transaction {

	// Mainīgie
    private long id;
    private LocalDateTime dateTime = LocalDateTime.now();
    private double amount;
    private String description;
    private BankAccount sourceAccount;
    private BankAccount targetAccount;
    private static long counter = 0;

    // Get
    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public BankAccount getTargetAccount() {
        return targetAccount;
    }

    // Set
    public void setAmount(double inputAmount) {
        if (inputAmount > 0) {
            amount = inputAmount;
        } 
        else {
            amount = 0;
        }
    }

    public void setDescription(String inputDescription) {
        if (inputDescription != null && !inputDescription.isEmpty()) {
            description = inputDescription;
        } 
        else {
            description = "Nezināms mērķis";
        }
    }

    public void setSourceAccount(BankAccount inputSourceAccount) {
        if (inputSourceAccount != null) {
            sourceAccount = inputSourceAccount;
        } 
        else {
            sourceAccount = null;
        }
    }

    public void setTargetAccount(BankAccount inputTargetAccount) {
        if (inputTargetAccount != null) {
            targetAccount = inputTargetAccount;
        } 
        else {
            targetAccount = null;
        }
    }

    public void setDateTime() {
        dateTime = LocalDateTime.now();
    }

    // Konstruktori
    public Transaction() {
        counter++;
        id = counter;
        setAmount(0);
        setDescription("Nezināms");
        setSourceAccount(null);
        setTargetAccount(null);
    }

    public Transaction(double amount, String description, BankAccount sourceAccount, BankAccount targetAccount) {
        counter++;
        id = counter;
        setAmount(amount);
        setDescription(description);
        setSourceAccount(sourceAccount);
        setTargetAccount(targetAccount);
        setDateTime();
    }

    // toString funkcija
    public String toString() {
        return "Pārskaitījums ID:" + id + " " + dateTime + " summa:" + amount + " EUR no " + (sourceAccount != null ? sourceAccount.getIban() : "nav") + " uz " + (targetAccount != null ? targetAccount.getIban() : "nav") + " (" + description + ")";
    }

    public boolean checkIsBalanceEnough() {
        return sourceAccount != null && sourceAccount.getBalance() >= amount;
    }
}