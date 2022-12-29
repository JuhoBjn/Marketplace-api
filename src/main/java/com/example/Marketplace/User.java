package com.example.Marketplace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Data
@Table(name = "users")
public class User extends AbstractPersistable<Long> {
    @Column(name = "userID")
    private long userID;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    
    // Keep track of the amount of users for user ID generation.
    private static long userCount = 0;
    
    public User() {
        this.userID = userCount;
        this.name = "unknown";
        this.email = "unknown";
        this.address = "unknown";
        this.phone = "unknown";
    }
    
    public User(String aName, String aEmail, String aAddress, String aPhone) {
        this.userID = userCount;
        this.name = aName;
        this.email = aEmail;
        this.address = aAddress;
        this.phone = aPhone;
    }

    public void setName(String aName) {
        this.name = aName;
    }
    
    public void setEmail(String aEmail) {
        this.email = aEmail;
    }
    
    public void setAddress(String aAddress) {
        this.address = aAddress;
    }
    
    public void setPhone(String aPhone) {
        this.phone = aPhone;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public long getUserID() {
        return userID;
    }
    
    public static void incrementUserCount() {
        userCount++;
    }
}
