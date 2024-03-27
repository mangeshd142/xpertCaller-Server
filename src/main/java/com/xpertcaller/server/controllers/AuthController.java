package com.xpertcaller.server.controllers;

import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.beans.jwt.JwtRequest;
import com.xpertcaller.server.beans.jwt.JwtRequestMobile;
import com.xpertcaller.server.beans.jwt.JwtResponse;
import com.xpertcaller.server.beans.user.User;
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
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login2(@RequestBody JwtRequestMobile request) {
        this.doAuthenticate(request.getMobileNumber(), request.getPassword());

        User userDetails = (User) userDetailsService.loadUserByUsername(request.getMobileNumber());
        String token = this.helper.generateToken(userDetails);
        if (token != null && !token.isEmpty()){
            userService.deleteOtp(request.getMobileNumber());
        }
        userDetails.setOtp("");
        userDetails.setPassword("");
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .user(userDetails).build();
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
    public User sendOtp(@RequestBody String mobileNumber, HttpServletResponse httpResponse) throws BusinessException {
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
