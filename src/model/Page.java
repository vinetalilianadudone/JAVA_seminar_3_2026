package model;

import java.util.ArrayList;

public class Page {
    
    // Variables
    private String title;
    private String description;
    private ArrayList<User> followers;
    private ArrayList<Post> postsInPage;
    
    // No-argument constructor
    public Page() {
        this.title = "";
        this.description = "";
        this.followers = new ArrayList<>();
        this.postsInPage = new ArrayList<>();
    }
    
    // Argument constructor
    public Page(String title, String description, ArrayList<User> followers, ArrayList<Post> postsInPage) {
        this.title = title;
        this.description = description;
        this.followers = followers;
        this.postsInPage = postsInPage;
    }
    
    // Get and Set methods
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public ArrayList<User> getFollowers() {
        return followers;
    }
    
    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }
    
    public ArrayList<Post> getPostsInPage() {
        return postsInPage;
    }
    
    public void setPostsInPage(ArrayList<Post> postsInPage) {
        this.postsInPage = postsInPage;
    }
    
    // toString() method
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", followers=" + followers +
                ", postsInPage=" + postsInPage +
                '}';
    }
}