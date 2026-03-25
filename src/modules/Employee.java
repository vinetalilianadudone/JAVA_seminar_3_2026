package modules;

public class Employee extends Person {
    
	// Mainigie
    private EmployeeStatus status;
    private double approvalLimit;

    //2 Get
    public EmployeeStatus getStatus() {
        return status;
    }

    public double getApprovalLimit() {
        return approvalLimit;
    }

    // Set
    public void setStatus(EmployeeStatus inputStatus) {
        if (inputStatus != null) {
            status = inputStatus;
        } 
        else {
            status = EmployeeStatus.AKTIVS;
        }
    }

    public void setApprovalLimit(double inputApprovalLimit) {
        if (inputApprovalLimit >= 0 && inputApprovalLimit <= 1000000) {
            approvalLimit = inputApprovalLimit;
        } 
        else {
            approvalLimit = 0;
        }
    }

    // Konstruktori
    public Employee() {
        super();
        setStatus(EmployeeStatus.AKTIVS);
        setApprovalLimit(1000);
    }

    public Employee(String name, String surname, String personCode, EmployeeStatus status, double approvalLimit) {
        super(name, surname, personCode);
        setStatus(status);
        setApprovalLimit(approvalLimit);
    }

    // toString funkcija
    public String toString() {
        return "status: " + status + ", apstiprinājuma limits: " + approvalLimit + " EUR";
    }
}