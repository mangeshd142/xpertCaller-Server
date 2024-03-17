package com.xpertcaller.server.controllers;

import com.xpertcaller.server.moduls.jwt.JwtRequest;
import com.xpertcaller.server.moduls.jwt.JwtRequestMobile;
import com.xpertcaller.server.moduls.jwt.JwtResponse;
import com.xpertcaller.server.moduls.user.User;
import com.xpertcaller.server.security.JwtHelper;
import com.xpertcaller.server.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login2")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login2(@RequestBody JwtRequestMobile request) {
        this.doAuthenticate(request.getMobileNumber(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getMobileNumber());
        String token = this.helper.generateToken(userDetails);
        if (token != null && !token.isEmpty()){
            userService.deleteOtp(request.getMobileNumber());
        }

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


    @CrossOrigin(origins = "*")
    @RequestMapping("/sendOtp")
    public User sendOtp(@RequestBody String mobileNumber, HttpServletResponse httpResponse) {
        User user = userService.sendOtp(mobileNumber);
        if(user == null)
            httpResponse.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return user;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
