package model.users;

import model.Page;
import java.util.ArrayList;

public class BusinessUser extends User {
    
    // Variables
    private ArrayList<Page> listOfPages;
    
    // No-argument constructor
    public BusinessUser() {
        super();
        this.listOfPages = new ArrayList<>();
    }
    
    // Argument constructor
    public BusinessUser(int generatedId, String nameAndSurname, String username, String password,
                        ArrayList<Page> listOfPages) {
        super(generatedId, nameAndSurname, username, password);
        this.listOfPages = listOfPages;
    }
    
    // Get and Set methods
    public ArrayList<Page> getListOfPages() {
        return listOfPages;
    }
    
    public void setListOfPages(ArrayList<Page> listOfPages) {
        this.listOfPages = listOfPages;
    }
    
    // Method to create a new page
    public void createPage(String title, String description) {
        Page newPage = new Page();
        newPage.setTitle(title);
        newPage.setDescription(description);
        newPage.setFollowers(new ArrayList<>());
        newPage.setPostsInPage(new ArrayList<>());
        
        listOfPages.add(newPage);
    }
    
    // Implementation of abstract method login()
    public boolean login(String username, String password) {
        String hashedInput = hashMD5(password);
        return this.getUsername().equals(username) && this.getPassword().equals(hashedInput);
    }
    
    // Implementation of abstract method followPage()
    public void followPage(Page page) {
        if (page != null && !page.getFollowers().contains(this)) {
            page.getFollowers().add(this);
        }
    }
    
    // toString() method
    public String toString() {
        return "BusinessUser{" +
                "generatedId=" + getGeneratedId() +
                ", nameAndSurname='" + getNameAndSurname() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", listOfPages=" + listOfPages +
                '}';
    }
}