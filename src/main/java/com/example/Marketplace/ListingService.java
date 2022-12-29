package com.example.Marketplace;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;
    
    @Autowired
    private UserService userService;
    
    // Return all listings in database.
    public ArrayList<Listing> getListings() {
        System.out.println("Retrieving all listings from database.");
        return this.listingRepository.findAll();
    }
    
    // Retrieve a listing from database based on listingID.
    public Listing getListing(long aListingID) {
        System.out.println("Retrieving listing " + aListingID + " from database.");
        return this.listingRepository.findByListingID(aListingID);
    }
    
    // Submit a new listing into the database.
    public void submitListing(String aUserName, String aTitle, String aContent, String aPrice) {
        System.out.println("Submitting a new listing into database.");
        double price = Double.parseDouble(aPrice);
        long userID = this.userService.getUserID(aUserName);
        Listing listing = new Listing(userID, aUserName, aTitle, aContent, price);
        listingRepository.save(listing);
    }
    
    // Update an existing listing in the database.
    @Transactional
    public void updateListing(long aListingID, String aTitle, String aContent, String aPrice) {
        System.out.println("Updating listing " + aTitle);
        Listing listing = listingRepository.findByListingID(aListingID);
        double price = Double.parseDouble(aPrice);
        if(!listing.getTitle().equals(aTitle) && !aTitle.isBlank()) {
            listing.setTitle(aTitle);
        }
        if(!listing.getContent().equals(aContent) && !aContent.isBlank()) {
            listing.setContent(aContent);
        }
        if(listing.getPrice() != price && price != 0) {
            listing.setPrice(price);
        }
        this.listingRepository.save(listing);
    }
    
    // Delete a listing from database base on listingID.
    @Transactional
    public void deleteListing(long aListingID) {
        System.out.println("Deleting entry " + aListingID + " from database.");
        this.listingRepository.deleteByListingID(aListingID);
    }
}
