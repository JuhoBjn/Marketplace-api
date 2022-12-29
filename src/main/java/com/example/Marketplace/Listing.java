package com.example.Marketplace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@Table(name = "listings")
public class Listing extends AbstractPersistable<Long> {
    @Column(name = "listingID")
    private long listingID;
    @Column(name = "userID")
    private long userID;
    @Column(name = "userName")
    private String userName;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "price")
    private double price;
    
    // Keep track of the amount of listings for listing ID generation.
    private static long listingCount = 0;
    
    public Listing() {
        this.listingID = listingCount;
        this.userID = 0;
        this.userName = "empty";
        this.title = "empty";
        this.content = "empty";
        this.price = 0.0;
        listingCount++;
    }
    
    public Listing(Long aUserID) {
        this.listingID = listingCount;
        this.userID = aUserID;
        this.userName = "empty";
        this.title = "empty";
        this.content = "empty";
        this.price = 0.0;
        listingCount++;
    }
    
    public Listing(Long aUserID, String aUserName, String aTitle, String aContent, double aPrice) {
        this.listingID = listingCount;
        this.userID = aUserID;
        this.userName = aUserName;
        this.title = aTitle;
        this.content = aContent;
        this.price = aPrice;
        listingCount++;
    }
    
    public void setUserName(String aUserName) {
        this.userName = aUserName;
    }
    
    public void setTitle(String aTitle) {
        this.title = aTitle;
    }
    
    public void setContent(String aContent) {
        this.content = aContent;
    }
    
    public void setPrice(double aPrice) {
        this.price = aPrice;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public double getPrice() {
        return this.price;
    }
}
