package com.bej.movieservice.service;

import com.bej.movieservice.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ExternalAPIServiceImpl implements ExternalAPIService {

    private ObjectMapper objectMapper;
    private  RestTemplate restTemplate;
    @Autowired
    public ExternalAPIServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate){
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }


    private final String apiUrl = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&region=IND&page=";

    private final String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxODdhOGY2NDEwNDUxMzJkMjA3ZDQ1YzQ0MWQ4MTk2NiIsInN1YiI6IjY1ZTgwYmQ3NDJmMTlmMDE4NzhkNWFiMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KzbeReLQGd1JvU4VxrKzRw76xLiitY-7dLnpyQWltVM"; // Replace with your actual API key





    public List<Movie> fetchAllMovies(int page) {
        List<Movie> movies = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = apiUrl + page;
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));

        ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(requestEntity, MovieDTO.class);
        // Process the responseEntity.getBody() as needed for each page
        movies = responseEntity.getBody().getResults();
        return movies;
    }
    public void findGenre() {
        String apiURL = "https://api.themoviedb.org/3/genre/movie/list?language=en";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));
        ResponseEntity<GenreDTO> responseEntity = restTemplate.exchange(requestEntity, GenreDTO.class);

//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            GenreDTO genreDTO = responseEntity.getBody();
//            if(genreDTO != null){
//                List<Genre> genres = genreDTO.getGenres();
//                return genres.get(1);
//            }
//        }
    }

    public MovieDetails getMovieById(Long movieId) {
        String apiURL = "https://api.themoviedb.org/3/movie/" + movieId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));
        ResponseEntity<MovieDetails> responseEntity = restTemplate.exchange(requestEntity, MovieDetails.class);

        return responseEntity.getBody();
    }
    public List<Movie> getMovieByTitle(String title) {
        String apiURL = "https://api.themoviedb.org/3/search/movie?query=" + URLEncoder.encode(title);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));
        ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(requestEntity, MovieDTO.class);
        MovieDTO movieDTO = responseEntity.getBody();
        List<Movie> movie = movieDTO.getResults();
        return movie;
    }
    public List<Movie> getMovieRecommendationsById (Long movieId) {
        String apiURL = "https://api.themoviedb.org/3/movie/" + movieId + "/recommendations?language=en-US&page=1";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));

        ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(requestEntity, MovieDTO.class);
        MovieDTO movieDTO = responseEntity.getBody();

        return movieDTO.getResults();
    }

    public List<CastMember> getMovieCast (Long movieId) {
        String apiURL = "https://api.themoviedb.org/3/movie/" + movieId + "/credits?language=en-US";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));

        ResponseEntity<CastDetails> responseEntity = restTemplate.exchange(requestEntity, CastDetails.class);
        List<CastMember> castList = responseEntity.getBody().getCast();

        List<CastMember> castMembers = new ArrayList<>();
        for(CastMember cast: castList)
        {
            if(cast.getProfilePath()!=null&&cast.getKnownForDepartment().equals("Acting")){
                castMembers.add(cast);
            }
        }
        return castMembers;
    }

    public List<MovieVideoResultDTO> getMovieVideoById (Long movieId) {
        String apiURL = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?language=en-US";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));

        ResponseEntity<MovieVideoDTO> responseEntity = restTemplate.exchange(requestEntity, MovieVideoDTO.class);
        List<MovieVideoResultDTO> videoResultDTOS  = responseEntity.getBody().getResults();

        List<MovieVideoResultDTO> videoDTO = new ArrayList<>();
        for(MovieVideoResultDTO video: videoResultDTOS){
            if (video.getType().equals("Trailer") ||
                video.getType().equals("Teaser"))
                {
                videoDTO.add(video);
            }
        }
//        System.out.println("Videos" + videoDTO);
        return videoDTO;
    }
    public List<Movie> sortMoviesByGenre(int genreId, int pageNo){
        String apiURL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page="+pageNo+"&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200&"+"with_genres="+genreId+"&without_genres=99%2C10755";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiURL));
        ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(requestEntity, MovieDTO.class);
        MovieDTO movieDTO = responseEntity.getBody();
        return movieDTO.getResults();
    }
}


