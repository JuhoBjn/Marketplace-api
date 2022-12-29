package com.example.Marketplace;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListingController {
    @Autowired
    private ListingService listingService;
    
    // Endpoint to get listings page.
    @GetMapping("/listings")
    public String retrieveListings(Model model) {
        System.out.println("Serving page of listings.");
        ArrayList<Listing> listings = listingService.getListings();
        model.addAttribute("listings", listings);
        return "listings";
    }
    
    // Endpoint to get page for creating a listing.
    @GetMapping("/createlisting")
    public String createListing() {
        System.out.println("Serving a page for creating a new listing.");
        return "createlisting";
    }
    
    // Endpoint to create a new listing.
    @PostMapping("/submitlisting")
    public String submitListing(@RequestParam String aUserName, String aTitle, String aContent, String aPrice) {
        System.out.println("Submitting a new listing.");
        this.listingService.submitListing(aUserName, aTitle, aContent, aPrice);
        return "redirect:/listings";
    }
    
    // Endpoint to serve page for updating a listing.
    @GetMapping("/updatelisting")
    public String updateListing(@RequestParam long aListingID, Model model) {
        System.out.println("Serving page to update listing.");
        Listing listing = this.listingService.getListing(aListingID);
        model.addAttribute("listing", listing);
        return "updatelisting";
    }
    
    // Endpoint to submit updated listing.
    @PostMapping("/submitupdatedlisting")
    public String submitUpdatedListing(@RequestParam long aListingID, String aTitle, String aContent, String aPrice) {
        System.out.println("Update listing endpoint.");
        this.listingService.updateListing(aListingID, aTitle, aContent, aPrice);
        return "redirect:/listings";
    }
    
    // Endpoint for deleting a listing.
    @PostMapping("/deletelisting")
    public String deleteListing(@RequestParam long aListingID) {
        System.out.println("Deleting listing " + aListingID);
        this.listingService.deleteListing(aListingID);
        return "redirect:/listings";
    }
}
