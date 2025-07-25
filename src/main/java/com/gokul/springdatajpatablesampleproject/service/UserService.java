package com.gokul.springdatajpatablesampleproject.service;


import com.gokul.springdatajpatablesampleproject.model.User;
import com.gokul.springdatajpatablesampleproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<String> addUser(User user) {
        if(userRepository.findById(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added");
    }

    public ResponseEntity<User> getUser(HttpServletRequest request) {
        String username = getUsernameFromRequest(request);
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public String getUsernameFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        String username = jwtUtil.extractUserName(token);
        return username;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getByUserName(String username){
        return userRepository.findByUsername(username);
    }



}
