package com.example.security_login_reg1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.security_login_reg1.entity.User;
import com.example.security_login_reg1.repository.UserRepo;
import com.example.security_login_reg1.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/signin")
    public String login()
    {
        return "login";
    }

    //The Principal object is used to retrieve information about the currently authenticated user. 

    @GetMapping("/user/profile")
    public String profile(Principal p,Model m)
    {
        // Retrieve the email from the Principal object (representing the currently authenticated user).
        String email = p.getName();

        // Use the email to find the corresponding user in the repository (presumably a database)
        User user = userRepo.findByEmail(email);

        // Add the user object to the model so it can be accessed in the view
        m.addAttribute("user", user);

        // // Return the logical view name "profile" (likely resolving to a Thymeleaf or JSP template)
        return "profile";
    }
    @GetMapping("/user/home")
    public String home()
    {
        return "home1";
    }

    //@ModelAttribute is primarily used for binding form data from user request  to method parameters(model object user).
//The @ModelAttribute annotation in Spring MVC is used to bind form data from the view to a model object in a controller method
    //Model is used for adding attributes to the model to make them available for rendering in a view.

    
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,HttpSession session)
    {
        //System.out.println(user);
        User u = userService.saveUser(user);
        if(u!= null)
        {
            //System.out.println("save success");
            session.setAttribute("msg", "Register successfully");
        }
        else{
            session.setAttribute("msg", "error");
        }
        return "redirect:/register";

    }

}
