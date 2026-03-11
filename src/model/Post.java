package model;

import java.util.Date;

public class Post {
    
    // Variables
    private String msg;
    private Date date;
    private int countOfLikes;
    
    // No-argument constructor
    public Post() {
        this.msg = "";
        this.date = new Date(); 
        this.countOfLikes = 0;
    }
    
    // Argument constructor
    public Post(String msg, Date date, int countOfLikes) {
        this.msg = msg;
        this.date = date;
        this.countOfLikes = countOfLikes;
    }
    
    // Get and set methods
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getCountOfLikes() {
        return countOfLikes;
    }
    
    public void setCountOfLikes(int countOfLikes) {
        this.countOfLikes = countOfLikes;
    }
    
    // toString() method
    public String toString() {
        return "Post{" +
                "msg='" + msg + '\'' +
                ", date=" + date +
                ", countOfLikes=" + countOfLikes +
                '}';
    }
}