package model.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Page;

/* Tika izmantoa informācija no:
 * https://www.geeksforgeeks.org/computer-networks/what-is-the-md5-algorithm/
 */

public abstract class User extends GuestUser {
    
    // Variables
    private String nameAndSurname;
    private String username;
    private String password; 
    
    // No-argument constructor
    public User() {
        super();
        this.nameAndSurname = "";
        this.username = "";
        this.password = "";
    }
    
    // Argument constructor
    public User(int generatedId, String nameAndSurname, String username, String password) {
        super();
        setGeneratedId(generatedId);
        this.nameAndSurname = nameAndSurname;
        this.username = username;
        setPassword(password); 
    }
    
    // MD5 Hashing method
    public static String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
    
    // Get and Set methods
    public String getNameAndSurname() {
        return nameAndSurname;
    }
    
    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = hashMD5(password);
    }
    
    public abstract boolean login(String username, String password);
    public abstract void followPage(Page page);
    
    // toString() method
    public String toString() {
        return "User{" +
                "generatedId=" + getGeneratedId() +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", username='" + username + '\'' +
                ", password='[HASHED]'" +
                '}';
    }
}