package com.harshal.web.Controller;

import com.harshal.web.Model.Users;
import com.harshal.web.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12); //creating an instance of BCryptPasswordEncoder with a strength of 12 i.e. 2^12 iterations.

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //encrypting the password before saving it to the database
        return new ResponseEntity<>(userServices.registerUser(user), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user) {
        return new ResponseEntity<>(userServices.verifyUser(user), HttpStatusCode.valueOf(200));
    }

    @GetMapping(("/getAllUsers"))
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatusCode.valueOf(200));
    }
}
