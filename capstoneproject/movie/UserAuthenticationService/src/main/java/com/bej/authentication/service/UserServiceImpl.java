package com.bej.authentication.service;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl( UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {

        if(userRepository.findById(user.getUserEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialsException {
        User loggedInUser = userRepository.findByUserEmailAndPassword(userEmail,password);
        if(loggedInUser == null)
        {
            throw new InvalidCredentialsException();
        }

        return loggedInUser;
    }

}
