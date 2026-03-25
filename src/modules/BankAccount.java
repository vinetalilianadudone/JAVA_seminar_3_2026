package modules;

public class BankAccount {

	// Mainīgie
    private long id;
    private String iban;
    private double balance;
    private boolean active;
    private static long counter = 0;

    // Get
    public long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    // Set
    public void setIban(String inputIban) {
        if (inputIban != null && !inputIban.isEmpty()) {
            iban = inputIban;
        } 
        else {
            iban = "LV00PARX0000000000000";
        }
    }

    public void setBalance(double inputBalance) {
        if (inputBalance >= 0) {
            balance = inputBalance;
        } 
        else {
            balance = 0;
        }
    }

    public void setActive(boolean inputActive) {
        active = inputActive;
    }

    // Konstruktori
    public BankAccount() {
        counter++;
        id = counter;
        setIban("LV00PARX0000000000000");
        setBalance(0);
        setActive(true);
    }

    public BankAccount(String iban, double balance, boolean active) {
        counter++;
        id = counter;
        setIban(iban);
        setBalance(balance);
        setActive(active);
    }

    // toString funkcija
    public String toString() {
        return "Konts ID:" + id + " IBAN:" + iban + " bilance:" + balance + " EUR, aktīvs:" + active;
    }
}