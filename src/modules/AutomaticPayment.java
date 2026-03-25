package modules;

import java.time.LocalDate;

public class AutomaticPayment extends Transaction {

	// Mainīgie
    private String title;
    private AutomaticPaymentScheduleType scheduleType;
    private LocalDate nextPaymentDate;
    private boolean active;

    // Get
    public String getTitle() {
        return title;
    }

    public AutomaticPaymentScheduleType getScheduleType() {
        return scheduleType;
    }

    public LocalDate getNextPaymentDate() {
        return nextPaymentDate;
    }

    public boolean isActive() {
        return active;
    }

    // Set
    public void setTitle(String inputTitle) {
        if (inputTitle != null && !inputTitle.isEmpty()) {
            title = inputTitle;
        } 
        else {
            title = "Nezināms automātiskais maksājums";
        }
    }

    public void setScheduleType(AutomaticPaymentScheduleType inputScheduleType) {
        if (inputScheduleType != null) {
            scheduleType = inputScheduleType;
        } 
        else {
            scheduleType = AutomaticPaymentScheduleType.IKDIENAS;
        }
    }

    public void setNextPaymentDate(LocalDate inputNextPaymentDate) {
        if (inputNextPaymentDate != null) {
            nextPaymentDate = inputNextPaymentDate;
        } 
        else {
            nextPaymentDate = LocalDate.now();
        }
    }

    public void setActive(boolean inputActive) {
        active = inputActive;
    }

    // Konstruktori
    public AutomaticPayment() {
        super();
        setTitle("Nezināms");
        setScheduleType(AutomaticPaymentScheduleType.IKDIENAS);
        setNextPaymentDate(LocalDate.now().plusDays(1));
        setActive(true);
    }

    public AutomaticPayment(double amount, String description, BankAccount sourceAccount, BankAccount targetAccount, String title, AutomaticPaymentScheduleType scheduleType, LocalDate nextPaymentDate, boolean active) {
        super(amount, description, sourceAccount, targetAccount);
        setTitle(title);
        setScheduleType(scheduleType);
        setNextPaymentDate(nextPaymentDate);
        setActive(active);
    }

    // toString funkcija
    public String toString() {
        return super.toString() + " | Automātiskais: " + title + ", grafiks: " + scheduleType + ", nākamais: " + nextPaymentDate + ", aktīvs: " + active;
    }

    // funkcija nākamā datuma atjaunināšanai pēc izpildes
    public void updateNextPaymentDate() {
        if (scheduleType == AutomaticPaymentScheduleType.IKDIENAS) {
            nextPaymentDate = nextPaymentDate.plusDays(1);
        } 
        else if (scheduleType == AutomaticPaymentScheduleType.IKNEDĒĻAS) {
            nextPaymentDate = nextPaymentDate.plusWeeks(1);
        } 
        else if (scheduleType == AutomaticPaymentScheduleType.IKMĒNEŠA) {
            nextPaymentDate = nextPaymentDate.plusMonths(1);
        } 
        else if (scheduleType == AutomaticPaymentScheduleType.IKGADA) {
            nextPaymentDate = nextPaymentDate.plusYears(1);
        }
    }
}