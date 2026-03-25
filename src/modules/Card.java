package modules;

import java.time.LocalDate;

public class Card {

	// Mainīgie
    private String cardNumber;
    private LocalDate expiryDate;
    private int cvv;
    private boolean active;
    private BankAccount account;

    // Get
    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public boolean isActive() {
        return active;
    }

    public BankAccount getAccount() {
        return account;
    }

    // Set
    public void setCardNumber(String inputCardNumber) {
        if (inputCardNumber != null && !inputCardNumber.isEmpty() && inputCardNumber.matches("\\d{4} \\d{4} \\d{4} \\d{4}")) {
            cardNumber = inputCardNumber;
        } 
        else {
            cardNumber = "0000 0000 0000 0000";
        }
    }

    public void setExpiryDate(LocalDate inputExpiryDate) {
        if (inputExpiryDate != null) {
            expiryDate = inputExpiryDate;
        } 
        else {
            expiryDate = LocalDate.now().plusYears(3);
        }
    }

    public void setCvv(int inputCvv) {
        if (inputCvv >= 100 && inputCvv <= 999) {
            cvv = inputCvv;
        } 
        else {
            cvv = 123;
        }
    }

    public void setActive(boolean inputActive) {
        active = inputActive;
    }

    public void setAccount(BankAccount inputAccount) {
        if (inputAccount != null) {
            account = inputAccount;
        } 
        else {
            account = null;
        }
    }

    // Konstruktori
    public Card() {
        setCardNumber("0000 0000 0000 0000");
        setExpiryDate(LocalDate.now().plusYears(3));
        setCvv(123);
        setActive(true);
        setAccount(null);
    }

    public Card(String cardNumber, LocalDate expiryDate, int cvv, boolean active, BankAccount account) {
        setCardNumber(cardNumber);
        setExpiryDate(expiryDate);
        setCvv(cvv);
        setActive(active);
        setAccount(account);
    }

    // toString funkcija
    public String toString() {
        return "Karte " + cardNumber + " derīga līdz " + expiryDate + ", CVV:" + cvv + ", aktīva:" + active + ", konts:" + (account != null ? account.getIban() : "nav");
    }
}