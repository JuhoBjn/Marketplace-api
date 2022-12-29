package com.example.Marketplace;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ListingRepository listingRepository;
    
    // Create a new user and save into database.
    public void createUser(String aName, String aEmail, String aAddress, String aPhone) {
        System.out.println("Creating new user, " + aName);
        User newUser = new User(aName, aEmail, aAddress, aPhone);
        userRepository.save(newUser);
    }
    
    // Retrieve a user from database based on userID.
    public User getUser(long userID) {
        System.out.println("Retrieving a user by ID.");
        return this.userRepository.findByUserID(userID);
    }
    
    // Return all users from database.
    public ArrayList<User> getUsers() {
        System.out.println("Retrieving all users.");
        ArrayList<User> users = userRepository.findAll();
        return users;
    }
    
    // Update a users details based on arguments.
    @Transactional
    public void updateUser(long aUserID, String aName, String aEmail, String aAddress, String aPhone) {
        System.out.println("Updating user " + aName);
        User user = userRepository.findByUserID(aUserID);
        if(!user.getName().equals(aName) && !aName.isBlank()) {
            user.setName(aName);
        }
        if(!user.getEmail().equals(aEmail) && !aEmail.isBlank()) {
            user.setEmail(aEmail);
        }
        if(!user.getAddress().equals(aAddress) && !aAddress.isBlank()) {
            user.setAddress(aAddress);
        }
        if(!user.getPhone().equals(aPhone) && !aPhone.isBlank()) {
            user.setPhone(aPhone);
        }
        this.userRepository.save(user);
        ArrayList<Listing> userListings = this.listingRepository.findAllByUserID(aUserID);
        if(!userListings.isEmpty()) {
            for(Listing listing : userListings) {
                listing.setUserName(user.getName());
            }
        }
    }
    
    // Delete a user from database.
    @Transactional
    public void deleteUser(long aUserID) {
        System.out.println("Deleting user " + aUserID + " from database.");
        ArrayList<Listing> userListings = this.listingRepository.findAllByUserID(aUserID);
        if(!userListings.isEmpty()) {
            this.listingRepository.deleteAllByUserID(aUserID);
        }
        this.userRepository.deleteByUserID(aUserID);
    }
    
    // Return a user's userID.
    public long getUserID(String aName) {
        System.out.println("Finding userID of " + aName);
        User user = this.userRepository.findByName(aName);
        return user.getUserID();
    }
}
