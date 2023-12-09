package com.example.security_login_reg1.config;

import java.util.Arrays;
import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security_login_reg1.entity.User;

public class CustomUser implements UserDetails {

    private User user;

    
/*A SimpleGrantedAuthority object is created, representing the user's role.
 The SimpleGrantedAuthority class is part of the Spring Security framework and
  implements the GrantedAuthority interface. */    

  /*
   * user.getRole(): It assumes that the User class has a method named getRole()
   *  that returns the role of the user. The role is typically a string representing 
   * the user's authority or role in the system.
   */

    public CustomUser(User user) {
        
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

       
       return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
    
}
