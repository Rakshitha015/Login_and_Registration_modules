package com.example.security_login_reg1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.security_login_reg1.entity.User;
import com.example.security_login_reg1.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
  private BCryptPasswordEncoder passwordEncoder;


    // is typically used in Spring Data JPA to persist a 
    //new user entity into the database.

    @Override
    public User saveUser(User user) {

    /*The repository interface defines various methods for interacting
with the database, including saving, updating, and querying entities. */

      // Encode the user's password using a password encoder.
      //This ensures that the user's password stored in the database is the encoded version, not the plain text.

      String password = passwordEncoder.encode(user.getPassword());

       // Set the encoded password back to the user object.

      user.setPassword(password);

      /* The user object, now with an encoded password and a role, is saved to the repository 
      (presumably a database). The userRepo.save method is typically provided by a Spring Data 
      JPA repository. */

      user.setRole("ROLE_USER");
      User newuser = userRepo.save(user);
      return newuser;
    }
    @Override
    public void removeSessionMessage() {
      HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
    }
    
    




}
