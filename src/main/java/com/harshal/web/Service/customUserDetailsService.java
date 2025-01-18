package com.harshal.web.Service;

import com.harshal.web.Model.User;
import com.harshal.web.Model.UserPrincipal;
import com.harshal.web.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //this will create a constructor with all the final fields in the class it helps in doing the constructor injection.
public class customUserDetailsService implements UserDetailsService {

    final private UserRepo userRepo; //autowired not written cause lombok created constructor with final fields using requiredArgsConstructor.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByUsername(username); //this line is used to get the user from the database using the username.
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user); //this line is used to return the user principal object which is used to authenticate the user.
    }
}
