package com.example.Marketplace;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    // Endpoint for creating a new user based on arguments.
    @PostMapping("/createuser")
    public String createUser(@RequestParam String aName, String aEmail, String aAddress, String aPhone, Model model) {
        System.out.println("Creatin a new user, " + aName);
        User.incrementUserCount();
        userService.createUser(aName, aEmail, aAddress, aPhone);
        return "redirect:/users";
    }
    
    // Endpoint for retrieving details of one user.
    @GetMapping("/userpage/{aUserID}")
    public String retrieveUser(@PathVariable String aUserID, Model model) {
        System.out.println("Serving details of requested user.");
        long id = Long.parseLong(aUserID);
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userpage";
    }
    
    // Endpoint to serve a page of all users.
    @GetMapping("/users")
    public String retrieveUsers(Model model) {
        System.out.println("Serving list of all users.");
        ArrayList<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    // Endpoint to update user details.
    @PostMapping("/updateuser")
    public String updateUser(@RequestParam long aUserID, String aName, String aEmail, String aAddress, String aPhone, Model model) {
        System.out.println("Updating user " + aName);
        userService.updateUser(aUserID, aName, aEmail, aAddress, aPhone);
        User user = userService.getUser(aUserID);
        model.addAttribute("user", user);
        return "userpage";
    }
    
    // Endpoint to delete a user.
    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam String aName, Model model) {
        System.out.println("Deleting user " + aName + " from user database.");
        long userID = userService.getUserID(aName);
        userService.deleteUser(userID);
        return "redirect:/users";
    }
}