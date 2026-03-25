package service;

import modules.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/* Lai izpildītu uzdevumus, tika izmantota informācija par JAVA no:
 * https://www.w3schools.com/java
 * moodle.venta.lv, kursa "Objektorientētā programmēšana" (JAVA), teorija un uzdevumi
 * https://www.geeksforgeeks.org/java
 * https://docs.oracle.com/javase/8/docs/api/
 * https://www.javatips.net/api/iban4j-master/src/main/java/org/iban4j/Iban.java
 * ChatGPT priekš generateClientDataAccountsAndCardsRandomly() un findAllAutomaticPaymentsThatNeedToTransferTodayAndDoTransfers()
 */

public class MainService {
	
	// Saraksti
    private static ArrayList<Client> allClients = new ArrayList<Client>();
    private static ArrayList<Employee> allEmployees = new ArrayList<Employee>();
    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private static ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();

    public static void main(String[] args) {
    	
        // Ģenerē testa datus
        generateClientDataAccountsAndCardsRandomly();

        // Pievieno darbiniekus
        addNewEmployee("Anna", "Kalniņa", "010101-12345", EmployeeStatus.AKTIVS, 50000.0);
        addNewEmployee("Pēteris", "Ozoliņš", "020202-23456", EmployeeStatus.AKTIVS, 2000.0);

        // CRUD darbiniekiem
        System.out.println("\nVisi darbinieki pirms izmaiņām:");
        allEmployees.forEach(System.out::println);

        Employee foundEmp = getEmployeeByPersonCode("010101-12345");
        System.out.println("Atrastais darbinieks: " + foundEmp);

        changeApprovalLimitForEmployeeByPersonCode("010101-12345", 60000.0);
        changeEmployeeStatusByPersonCode("020202-23456", EmployeeStatus.ATVALINAJUMA);
        removeEmployeeByPersonCode("020202-23456");

        System.out.println("Darbinieki pēc CRUD izmaiņām:");
        allEmployees.forEach(System.out::println);

        // Datu filtrācija un pievienošana
        if (!allClients.isEmpty()) {
            Client testClient = allClients.get(0);
            System.out.println("\nKonta meklēšana klientam " + testClient.getClientCode() + ": " + findAllBankAccountsForClientByClientCode(testClient.getClientCode()));

            System.out.println("Klienti Rīgā: " + findAllClientsInTheCity(City.RIGA).size() + " gab.");

            // Izveido jaunu kontu
            createNewBankAccountForClientByClientCode(testClient.getClientCode(), "LV88PARX12345678901234", 250.0, true);

            // Pārskaitījumi
            if (!testClient.getAccounts().isEmpty()) {
                BankAccount src = testClient.getAccounts().get(0);
                BankAccount tgt = testClient.getAccounts().get(0);
                createNewSimpleTransaction(10.0, "Testa maksājums", src, tgt);
            }

            // Lielā summa
            if (!allEmployees.isEmpty()) {
                createNewLargeTransaction(30000.0, "Liels pirkums", testClient.getAccounts().get(0), testClient.getAccounts().get(0), allEmployees.get(0));
            }

            // Automātiskais
            createNewAutomaticPayment(15.0, "Interneta rēķins", testClient.getAccounts().get(0), testClient.getAccounts().get(0), "Internets", AutomaticPaymentScheduleType.IKMĒNEŠA, LocalDate.now(), true);

            System.out.println("Visi pārskaitījumi kontam: " + findAllTransactionsForBankAccount(testClient.getAccounts().get(0).getIban()).size() + " gab.");
            System.out.println("Visi pārskaitījumi klientam: " + findAllTransactionsForClientByClientCode(testClient.getClientCode()).size() + " gab.");
        }

        System.out.println("Kartes, kurām beidzas derīgums nedēļas laikā: " + findAllCardsExpiringWithInNextWeek().size() + " gab.");
        System.out.println("Kontu skaits, kas piesaistīti kartēm: " + findAllAccountsLinkedToCards().size() + " gab.");

        // Automātisko maksājumu izpilde šodien
        findAllAutomaticPaymentsThatNeedToTransferTodayAndDoTransfers();

        System.out.println("Darbinieku skaits pirms kārtošanas: " + allEmployees.size());
        sortEmployeesByApprovalLimit();
        
        sortEmployeesByApprovalLimit();
        System.out.println("Darbinieki pēc kārtošanas (pēc limita):");
        allEmployees.forEach(System.out::println);
    }

    // CRUD darbiniekiem 
    public static void addNewEmployee(String name, String surname, String personCode, EmployeeStatus status, double approvalLimit) {
        Employee e = new Employee(name, surname, personCode, status, approvalLimit);
        if (!allEmployees.contains(e)) {
            allEmployees.add(e);
        }
    }

    public static Employee getEmployeeByPersonCode(String personCode) {
        for (Employee e : allEmployees) {
            if (e.getPersonCode().equals(personCode)) {
                return e;
            }
        }
        return null;
    }

    public static void changeApprovalLimitForEmployeeByPersonCode(String personCode, double newApprovalLimit) {
        Employee e = getEmployeeByPersonCode(personCode);
        if (e != null) {
            e.setApprovalLimit(newApprovalLimit);
        }
    }

    public static void changeEmployeeStatusByPersonCode(String personCode, EmployeeStatus status) {
        Employee e = getEmployeeByPersonCode(personCode);
        if (e != null) {
            e.setStatus(status);
        }
    }

    public static void removeEmployeeByPersonCode(String personCode) {
        Employee e = getEmployeeByPersonCode(personCode);
        if (e != null) {
            allEmployees.remove(e);
        }
    }

    // Datu filtrācija un pievienošana
    public static ArrayList<BankAccount> findAllBankAccountsForClientByClientCode(String clientCode) {
        for (Client c : allClients) {
            if (c.getClientCode().equals(clientCode)) {
                return c.getAccounts();
            }
        }
        return new ArrayList<>();
    }

    public static ArrayList<Transaction> findAllTransactionsForBankAccount(String iban) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : allTransactions) {
            if ((t.getSourceAccount() != null && t.getSourceAccount().getIban().equals(iban)) ||
                (t.getTargetAccount() != null && t.getTargetAccount().getIban().equals(iban))) {
                result.add(t);
            }
        }
        return result;
    }

    public static ArrayList<Client> findAllClientsInTheCity(City city) {
        ArrayList<Client> result = new ArrayList<>();
        for (Client c : allClients) {
            if (c.getAddress() != null && c.getAddress().getCity() == city) {
                result.add(c);
            }
        }
        return result;
    }

    public static ArrayList<Transaction> findAllTransactionsForClientByClientCode(String clientCode) {
        ArrayList<Transaction> result = new ArrayList<>();
        Client client = null;
        for (Client c : allClients) {
            if (c.getClientCode().equals(clientCode)) {
                client = c;
                break;
            }
        }
        if (client == null) 
        	return result;
        for (Transaction t : allTransactions) {
            if ((t.getSourceAccount() != null && client.getAccounts().contains(t.getSourceAccount())) ||
                (t.getTargetAccount() != null && client.getAccounts().contains(t.getTargetAccount()))) {
                result.add(t);
            }
        }
        return result;
    }

    public static void createNewBankAccountForClientByClientCode(String clientCode, String iban, double balance, boolean active) {
        for (Client c : allClients) {
            if (c.getClientCode().equals(clientCode)) {
                BankAccount ba = new BankAccount(iban, balance, active);
                c.addBankAccount(ba);
                return;
            }
        }
    }

    public static ArrayList<Card> findAllCardsExpiringWithInNextWeek() {
        ArrayList<Card> result = new ArrayList<>();
        LocalDate weekLater = LocalDate.now().plusWeeks(1);
        for (Card c : allCards) {
            if (c.getExpiryDate() != null && c.getExpiryDate().isAfter(LocalDate.now()) && c.getExpiryDate().isBefore(weekLater)) {
                result.add(c);
            }
        }
        return result;
    }

    public static ArrayList<BankAccount> findAllAccountsLinkedToCards() {
        ArrayList<BankAccount> result = new ArrayList<>();
        for (Card c : allCards) {
            if (c.getAccount() != null && !result.contains(c.getAccount())) {
                result.add(c.getAccount());
            }
        }
        return result;
    }

    public static ArrayList<Employee> findAllEmployeesWhoCanCheckThatLargeTransaction(LargeTransaction largeTransaction) {
        ArrayList<Employee> result = new ArrayList<>();
        if (largeTransaction == null) 
        	return result;
        double amount = largeTransaction.getAmount();
        for (Employee e : allEmployees) {
            if (e.getStatus() == EmployeeStatus.AKTIVS && e.getApprovalLimit() >= amount) {
                result.add(e);
            }
        }
        return result;
    }

    public static void createNewSimpleTransaction(double amount, String description, BankAccount sourceAccount, BankAccount targetAccount) {
        if (sourceAccount == null || targetAccount == null || amount <= 0) 
        	return;
        Transaction t = new Transaction(amount, description, sourceAccount, targetAccount);
        if (t.checkIsBalanceEnough()) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            allTransactions.add(t);
        } else {
            System.out.println("Brīdinājums: nepietiek naudas parastajam pārskaitījumam!");
            allTransactions.add(t); 
        }
    }

    public static void createNewAutomaticPayment(double amount, String description, BankAccount sourceAccount, BankAccount targetAccount, 
    		String title, AutomaticPaymentScheduleType scheduleType, LocalDate nextPaymentDate, boolean active) {
        if (sourceAccount == null || targetAccount == null) 
        	return;
        AutomaticPayment ap = new AutomaticPayment(amount, description, sourceAccount, targetAccount, title, scheduleType, nextPaymentDate, active);
        allTransactions.add(ap);
    }

    public static void createNewLargeTransaction(double amount, String description, BankAccount sourceAccount, 
    		BankAccount targetAccount, Employee employeeWhoNeedToCheck) {
        if (sourceAccount == null || targetAccount == null || employeeWhoNeedToCheck == null) 
        	return;
        if (employeeWhoNeedToCheck.getStatus() == EmployeeStatus.AKTIVS && employeeWhoNeedToCheck.getApprovalLimit() >= amount) {
            LargeTransaction lt = new LargeTransaction(amount, description, sourceAccount, targetAccount, employeeWhoNeedToCheck);
            allTransactions.add(lt);
        } 
        else {
            System.out.println("Brīdinājums: darbinieks nevar apstiprināt lielās summas pārskaitījumu!");
        }
    }

    public static void findAllAutomaticPaymentsThatNeedToTransferTodayAndDoTransfers() {
        LocalDate today = LocalDate.now();
        for (Transaction t : new ArrayList<>(allTransactions)) {
            if (t instanceof AutomaticPayment) {
                AutomaticPayment ap = (AutomaticPayment) t;
                if (ap.isActive() && ap.getNextPaymentDate().equals(today)) {
                    if (ap.checkIsBalanceEnough()) {
                        ap.getSourceAccount().setBalance(ap.getSourceAccount().getBalance() - ap.getAmount());
                        ap.getTargetAccount().setBalance(ap.getTargetAccount().getBalance() + ap.getAmount());
                        ap.updateNextPaymentDate();
                        System.out.println("Tika izpildīts automātiskais maksājums: " + ap.getTitle());
                    }
                }
            }
        }
    }

    public static void sortEmployeesByApprovalLimit() {
        Collections.sort(allEmployees, new Comparator<Employee>() {
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getApprovalLimit(), e2.getApprovalLimit());
            }
        });
    }

    public static void generateClientDataAccountsAndCardsRandomly() {
        Random rand = new Random();
        String[] names = {"Jānis", "Anna", "Vineta", "Marta", "Andris", "Laura"};
        String[] surnames = {"Bērziņš", "Kalniņa", "Ozoliņš", "Liepiņa", "Krastiņš", "Zvaigzne"};
        City[] cities = City.values();

        for (int i = 0; i < 6; i++) {
            String name = names[rand.nextInt(names.length)];
            String surname = surnames[rand.nextInt(surnames.length)];
            String personCode = String.format("%06d-%05d", 100000 + rand.nextInt(899999), rand.nextInt(99999));
            String email = name.toLowerCase() + "." + surname.toLowerCase() + "@ibanka.lv";
            Address addr = new Address(cities[rand.nextInt(cities.length)], "Iela " + (rand.nextInt(50) + 1), rand.nextInt(200) + 1);

            Client cl = new Client(name, surname, personCode, email, addr);
            allClients.add(cl);

            // 1 - 3 konti katram klientam
            int accountsCount = 1 + rand.nextInt(3);
            for (int j = 0; j < accountsCount; j++) {
                String iban = "LV" + String.format("%02d", 10 + i) + "PARX" + String.format("%016d", rand.nextInt(99999999) + j);
                BankAccount ba = new BankAccount(iban, rand.nextDouble() * 5000, true);
                cl.addBankAccount(ba);

                // 1/2 gadījumu pievieno karti
                if (rand.nextBoolean()) {
                    String cardNum = "3700 " + String.format("%04d", rand.nextInt(10000)) + " " + String.format("%04d", rand.nextInt(10000)) + " " + String.format("%04d", rand.nextInt(10000));
                    Card card = new Card(cardNum, LocalDate.now().plusYears(2 + rand.nextInt(3)), 100 + rand.nextInt(900), true, ba);
                    allCards.add(card);
                }
            }
        }
        System.out.println("Ģenerēti " + allClients.size() + " nejauši klienti, konti un kartes.");
    }
}