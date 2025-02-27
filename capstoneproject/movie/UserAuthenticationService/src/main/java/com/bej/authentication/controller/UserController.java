package com.bej.authentication.controller;

import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.getUserByUserEmailAndPassword(user.getUserEmail(),user.getPassword());
        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        Map<String, String> token = securityTokenGenerator.createToken(user);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
