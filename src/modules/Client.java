package modules;

import java.util.ArrayList;

public class Client extends Person {

	// Mainīgie
    private String email;
    private Address address;
    private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    private String clientCode;

    // Get
    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public String getClientCode() {
        return clientCode;
    }

    // Set
    public void setEmail(String inputEmail) {
        if (inputEmail != null && !inputEmail.isEmpty() && inputEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            email = inputEmail;
        } 
        else {
            email = "nezinams@ibanka.lv";
        }
    }

    public void setAddress(Address inputAddress) {
        if (inputAddress != null) {
            address = inputAddress;
        } 
        else {
            address = new Address();
        }
    }

    public void setClientCode() {
        if (getSurname() != null && getName() != null && getPersonCode() != null && getSurname().length() > 0 && getName().length() > 0) {
            String firstSurname = getSurname().substring(0, 1).toUpperCase();
            String firstName = getName().substring(0, 1).toUpperCase();
            clientCode = firstSurname + "_" + firstName + "_" + getPersonCode();
        } 
        else {
            clientCode = "X_X_000000-00000";
        }
    }

    // Konstruktori
    public Client() {
        super();
        setEmail("nezinams@ibanka.lv");
        setAddress(new Address());
        setClientCode();
    }

    public Client(String name, String surname, String personCode, String email, Address address) {
        super(name, surname, personCode);
        setEmail(email);
        setAddress(address);
        setClientCode();
    }

    // toString funkcija
    public String toString() {
        return super.toString() + ", e-pasts: " + email + ", adrese: " + address + ", klienta kods: " + clientCode + ", konti: " + accounts.size() + " gab.";
    }

    public void addBankAccount(BankAccount account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public void removeBankAccount(BankAccount account) {
        if (account != null && accounts.contains(account)) {
            accounts.remove(account);
        }
    }
}