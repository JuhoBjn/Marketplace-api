package com.example.Marketplace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketplaceController {
    // Direct empty path to listings.
    @GetMapping("/")
    public String getListings() {
        return "redirect:/listings";
    }
    
    // Redirect mistyped paths to listings.
    @GetMapping("*")
    public String getDefault() {
        return "redirect:/listings";
    }
}
