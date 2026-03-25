package modules;

public class Person {
    
	// Mainigie
    private String name;
    private String surname;
    private String personCode;

    // Get
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonCode() {
        return personCode;
    }

    // Set
    public void setName(String inputName) {
        if (inputName != null && !inputName.isEmpty() && inputName.matches("[A-ZĀČĒĢĪĶĻŅŠŪŽ][a-zāčēģīķļņšūž]+")) {
            name = inputName;
        } 
        else {
            name = "Nezināms";
        }
    }

    public void setSurname(String inputSurname) {
        if (inputSurname != null && !inputSurname.isEmpty() && inputSurname.matches("[A-ZĀČĒĢĪĶĻŅŠŪŽ][a-zāčēģīķļņšūž]+")) {
            surname = inputSurname;
        } 
        else {
            surname = "Nezināms";
        }
    }

    public void setPersonCode(String inputPersonCode) {
        if (inputPersonCode != null && !inputPersonCode.isEmpty() && inputPersonCode.matches("\\d{6}-\\d{5}")) {
            personCode = inputPersonCode;
        } 
        else {
            personCode = "000000-00000";
        }
    }

    // Konstruktori
    public Person() {
        setName("Nezināms");
        setSurname("Nezināms");
        setPersonCode("000000-00000");
    }

    public Person(String name, String surname, String personCode) {
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }

    // toString funkcija
    public String toString() {
        return name + " " + surname + " (" + personCode + ")";
    }
}