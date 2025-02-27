package com.bej.movieservice.service;

import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.MovieDTO;
import com.bej.movieservice.domain.User;
import com.bej.movieservice.exception.MovieNotFoundException;
import com.bej.movieservice.exception.UserAlreadyExistsException;
import com.bej.movieservice.proxy.FavoriteProxy;
import com.bej.movieservice.proxy.MovieProxy;
import com.bej.movieservice.proxy.UserProxy;
import com.bej.movieservice.repository.UserMovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMovieServiceImpl implements UserMovieService {
    private UserProxy userProxy;
    private FavoriteProxy favoriteProxy;
    private UserMovieRepository userMovieRepository;
    private MovieProxy movieProxy;
    @Autowired
    public UserMovieServiceImpl(UserProxy userProxy, UserMovieRepository userMovieRepository, FavoriteProxy favoriteProxy, MovieProxy movieProxy) {
        this.userProxy = userProxy;
        this.userMovieRepository = userMovieRepository;
        this.favoriteProxy = favoriteProxy;
        this.movieProxy = movieProxy;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userMovieRepository.findById(user.getUserEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        user.setOrders(new ArrayList<>());
        User savedUser = userMovieRepository.save(user);
        if(!(savedUser.getUserName().isEmpty())) {
            ResponseEntity r = userProxy.saveUser(user);
            ResponseEntity responseEntity = favoriteProxy.saveUser(user);
        }
        return savedUser;
    }

//    @Override
//    public List<Movie> getAllRecommendedMovies(Long movieId) throws MovieNotFoundException {
//        ResponseEntity<MovieDTO> responseEntity = movieProxy.getMovieRecommendationsById(movieId);
//        List<Movie> movies = new ArrayList<>();
//        System.out.println("response is :" + responseEntity );
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            MovieDTO movieDTOs = responseEntity.getBody();
//            if (movieDTOs != null) {
//                for (Movie movieDTO : movieDTOs.getResults()) {
//                    movies.add(movieDTO);
//                }
//
//            }
//        }
//        return movies;
//    }
@Override
public List<Movie> getAllRecommendedMovies(Long movieId) throws MovieNotFoundException {
    ResponseEntity<List<Movie>> responseEntity = movieProxy.getMovieRecommendationsById(movieId);
    List<Movie> movies = new ArrayList<>();
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
        movies = responseEntity.getBody();
    }
    return movies;
}
    @Override
    public User getCurrentUserDetails(String emailId) {
        return userMovieRepository.findById(emailId).get();
    }

    @Override
    public String getUsernameByEmail(String email) {
        Optional<User> user = userMovieRepository.findById(email);
        User user1=user.get();
        return user1.getUserName();
    }

}
