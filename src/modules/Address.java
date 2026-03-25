package modules;

public class Address {
    
	// Mainīgie
    private City city;
    private String streetOrHouseTitle;
    private int houseNo;

    // Get
    public City getCity() {
        return city;
    }

    public String getStreetOrHouseTitle() {
        return streetOrHouseTitle;
    }

    public int getHouseNo() {
        return houseNo;
    }

    // Set
    public void setCity(City inputCity) {
        if (inputCity != null) {
            city = inputCity;
        } 
        else {
            city = City.RIGA;
        }
    }

    public void setStreetOrHouseTitle(String inputStreetOrHouseTitle) {
        if (inputStreetOrHouseTitle != null && !inputStreetOrHouseTitle.isEmpty() && inputStreetOrHouseTitle.matches("[A-ZĀČĒĢĪĶĻŅŠŪŽ][a-zāčēģīķļņšūž0-9 .,/-]{2,80}")) {
            streetOrHouseTitle = inputStreetOrHouseTitle;
        } 
        else {
            streetOrHouseTitle = "Nezināma iela";
        }
    }

    public void setHouseNo(int inputHouseNo) {
        if (inputHouseNo > 0 && inputHouseNo < 10000) {
            houseNo = inputHouseNo;
        } 
        else {
            houseNo = 1;
        }
    }

    // Konstruktori
    public Address() {
        setCity(City.RIGA);
        setStreetOrHouseTitle("Nezināma iela");
        setHouseNo(1);
    }

    public Address(City city, String streetOrHouseTitle, int houseNo) {
        setCity(city);
        setStreetOrHouseTitle(streetOrHouseTitle);
        setHouseNo(houseNo);
    }

    // toString funkcija
    public String toString() {
        return city + ", " + streetOrHouseTitle + " " + houseNo;
    }
}