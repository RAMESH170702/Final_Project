import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrl: './player.component.css'
})
export class PlayerComponent implements OnInit {
  selectedMovieId: string = "";
  movieYoutubeId: string = "";

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
 
    // Retrieve the selected movie ID from route parameters
    this.route.params.subscribe(params => {
      this.selectedMovieId = params['id'];
          // Fetch movie video details and recommended movies using the selected movie ID
      this.fetchMovieVideoDetails(this.selectedMovieId);     
    });
  }

  fetchMovieVideoDetails(movieId:string) {
    // Make HTTP request to fetch movies videos for the selected movie ID
    this.http.get<any[]>(`http://localhost:9000/api/v4/movies/movie/video/${movieId}`)
    .subscribe(response => {
      this.movieYoutubeId = response[0].key;      
    }, error => {
      console.error('Error fetching video:', error);
    });
  }
}
