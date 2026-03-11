package model.users;

import model.Post;
import java.util.ArrayList;

public class PrivateUser extends User {
    
    // Variables
    private ArrayList<Post> privatePosts;
    private ArrayList<Post> publicPosts;
    private ArrayList<User> followers;
    
    // No-argument constructor
    public PrivateUser() {
        super();
        this.privatePosts = new ArrayList<>();
        this.publicPosts = new ArrayList<>();
        this.followers = new ArrayList<>();
    }
    
    // Argument constructor
    public PrivateUser(int generatedId, String nameAndSurname, String username, String password,
                       ArrayList<Post> privatePosts, ArrayList<Post> publicPosts, ArrayList<User> followers) {
        super(generatedId, nameAndSurname, username, password);
        this.privatePosts = privatePosts;
        this.publicPosts = publicPosts;
        this.followers = followers;
    }
    
    // Get and Set methods
    public ArrayList<Post> getPrivatePosts() {
        return privatePosts;
    }
    
    public void setPrivatePosts(ArrayList<Post> privatePosts) {
        this.privatePosts = privatePosts;
    }
    
    public ArrayList<Post> getPublicPosts() {
        return publicPosts;
    }
    
    public void setPublicPosts(ArrayList<Post> publicPosts) {
        this.publicPosts = publicPosts;
    }
    
    public ArrayList<User> getFollowers() {
        return followers;
    }
    
    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }
    
    // Method to follow another private user
    public void followPrivateUser(PrivateUser privateUser) {
        if (privateUser != null && !privateUser.getFollowers().contains(this)) {
            privateUser.getFollowers().add(this);
        }
    }
    
    // Implementation of abstract method login()
    public boolean login(String username, String password) {
        String hashedInput = hashMD5(password);
        return this.getUsername().equals(username) && this.getPassword().equals(hashedInput);
    }
    
    // Implementation of abstract method followPage()
    public void followPage(model.Page page) {
        if (page != null) {
            page.getFollowers().add(this);
        }
    }
    
    // toString() method
    public String toString() {
        return "PrivateUser{" +
                "generatedId=" + getGeneratedId() +
                ", nameAndSurname='" + getNameAndSurname() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", privatePosts=" + privatePosts +
                ", publicPosts=" + publicPosts +
                ", followers=" + followers +
                '}';
    }
}