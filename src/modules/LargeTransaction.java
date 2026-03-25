package modules;

public class LargeTransaction extends Transaction {

	// Mainīgie
    private Employee employeeWhoNeedToCheck;
    private boolean isAccepted = false;

    // Get
    public Employee getEmployeeWhoNeedToCheck() {
        return employeeWhoNeedToCheck;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    // Set
    public void setEmployeeWhoNeedToCheck(Employee inputEmployeeWhoNeedToCheck) {
        if (inputEmployeeWhoNeedToCheck != null) {
            employeeWhoNeedToCheck = inputEmployeeWhoNeedToCheck;
        } 
        else {
            employeeWhoNeedToCheck = null;
        }
    }

    public void setAccepted(boolean inputIsAccepted) {
        isAccepted = inputIsAccepted;
    }

    // Konstruktori
    public LargeTransaction() {
        super();
        setEmployeeWhoNeedToCheck(null);
    }

    public LargeTransaction(double amount, String description, BankAccount sourceAccount, BankAccount targetAccount, Employee employeeWhoNeedToCheck) {
        super(amount, description, sourceAccount, targetAccount);
        setEmployeeWhoNeedToCheck(employeeWhoNeedToCheck);
    }

    // toString
    public String toString() {
        return super.toString() + " | Lielā summa, pārbaudīs: " + (employeeWhoNeedToCheck != null ? employeeWhoNeedToCheck.getPersonCode() : "nav") + ", apstiprināts: " + isAccepted;
    }
}