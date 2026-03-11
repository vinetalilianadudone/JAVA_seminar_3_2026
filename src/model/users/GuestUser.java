package model.users;

public class GuestUser {
    
    // Variable
    private int generatedId;
    
    // No-argument constructor
    public GuestUser() {
        this.generatedId = 0;
    }
    
    // Get and Set methods
    public int getGeneratedId() {
        return generatedId;
    }
    
    public void setGeneratedId(int generatedId) {
        this.generatedId = generatedId;
    }
    
    // toString() method
    public String toString() {
        return "GuestUser{" +
                "generatedId=" + generatedId +
                '}';
    }
}