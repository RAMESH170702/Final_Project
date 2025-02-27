  import { Component, Input, NgZone, OnInit } from '@angular/core';
import { Movie } from '../model/Movie';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { MovieService } from '../service/movie.service';
import { AuthserviceService } from '../service/authservice.service';

@Component({
  selector: 'app-view-movie',
  templateUrl: './view-movie.component.html',
  styleUrl: './view-movie.component.css',
})
export class ViewMovieComponent implements OnInit {
  selectedMovieId: string = "";
  selectedMovie: any = {};
  recommendedMovies: any[] = [];
  genres: any[] = [];
  movie_average: number = 0;
  year: String = "";
  cast: any[] = [];
  backgroundImageUrl: string = "";
  movieOwned:boolean = false;

  currentIndex = 0;
  currentIndexRecommended = 0; 
  visibleCards = 5; // Number of cards visible at a time
  cardWidth = 230; // Width of each card (adjust as needed)
  nextCastNum = 4;
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router,
    private ngZone: NgZone, private movieService: MovieService, private authService: AuthserviceService) { }


  isFavorite: boolean = false;

  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
// toggleFavorite(movieId : any): void {
//     const userEmail = localStorage.getItem('userEmail');
//     if (!userEmail) {
//       alert('User is not logged in. Please log in to add favorites.');
//       return;
//     }
//     this.isFavorite = !this.isFavorite;
//     const apiUrl = `http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}/movie/${movieId}`;
//     if (this.isFavorite) {
//       this.http.post<any>(apiUrl, {}).subscribe(
//         response => {
//           console.log('Movie added to favorites:', response);
//         },
//         error => {
//           console.error('Failed to add movie to favorites:', error);
//         }
//       );
//     } else {
//       this.http.delete(apiUrl).subscribe(
//         response => {
//           console.log('Movie removed from favorites:', response);
//         },
//         error => {
//           console.error('Failed to remove movie from favorites:', error);
//         }
//       );
//     }
//   }

  next() {
    // if (this.hasNext()){
    this.currentIndex = Math.min(this.currentIndex + this.nextCastNum, this.cast.length - this.visibleCards);
    // console.log(this.currentIndex);
    // }
  }
  
  // hasNext(): boolean {
  //   return this.currentIndex < this.cast.length - 7;
  // }

  previous() {
    this.currentIndex = Math.max(this.currentIndex - this.nextCastNum, 0);
    console.log(this.currentIndex);
  }

  nextRecommended() {
    this.currentIndexRecommended = Math.min(this.currentIndexRecommended + this.nextCastNum, this.recommendedMovies.length - this.visibleCards);
  }

  previousRecommended() {
    this.currentIndexRecommended = Math.max(this.currentIndexRecommended - this.nextCastNum, 0);
  }



  ngOnInit(): void {
 
    // Retrieve the selected movie ID from route parameters
    this.route.params.subscribe(params => {
      this.selectedMovieId = params['id'];
      // Fetch movie details and recommended movies using the selected movie ID
      this.fetchMovieDetails(this.selectedMovieId);
      this.currentIndex = 0;
      this.currentIndexRecommended = 0; 
      this.movieOwned = false;
      this.updateFavoriteStatus();
      this.getMovieOwned();
    });
    // this.fetchFavoriteStatus(this.selectedMovieId);
  }

  fetchMovieDetails(movieId: string): void {
    // Make HTTP request to fetch movie details by ID
    this.http.get<any>(`http://localhost:9000/api/v4/movies/movie/${movieId}`)
      .subscribe(response => {
        this.selectedMovie = response;
        this.genres = this.selectedMovie.genres;
        this.movie_average = Math.ceil(this.selectedMovie.vote_average * 10);
        const rel_date = this.selectedMovie.release_date.split("-")
        this.year = rel_date[0];
        this.backgroundImageUrl = 'https://image.tmdb.org/t/p/w500/'+ this.selectedMovie.backdrop_path;
        // Fetch recommended movies once movie details are fetched
        this.fetchCastOfMovie(movieId);
        this.fetchRecommendedMovies(movieId);
      }, error => {
        console.error('Error fetching movie details:', error);
      });
  }

  fetchRecommendedMovies(movieId: string): void {
    // Make HTTP request to fetch recommended movies for the selected movie ID
    this.http.get<any[]>(`http://localhost:9000/api/v4/movies/movie/recommendations/${movieId}`)
      .subscribe(response => {
        this.recommendedMovies = response;
        
      }, error => {
        console.error('Error fetching cast:', error);
      });
  }

  fetchCastOfMovie(movieId: string): void {
    this.http.get<any[]>(`http://localhost:9000/api/v4/movies/movie/cast/${movieId}`)
    .subscribe(response => {
      this.cast = response;
    }, error => {
      console.error('Error fetching recommended movies:', error);
    });
  }

  // addToFavorite(movieId: string): void {
  //   this.http.get<any>(`http://localhost:9000/api/v3/favorites/favorite/user/{userEmail}/movie/{movieId}`)
  // }
  // scrollNext(): void {
  //   const carousel = document.querySelector('.carousel');
  //   if (carousel instanceof HTMLElement) {
  //     carousel.scrollBy({ left: 200, behavior: 'smooth' });
  //   }
  // }
  // fetchFavoriteStatus(movieId: string): Observable<boolean> {
  //   const userEmail = localStorage.getItem('userEmail');
  //   if (!userEmail) {
  //     // If user is not logged in, return false
  //     return of(false);
  //   }

  //   const apiUrl = `http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}`;
  //   return this.http.get<any[]>(apiUrl).pipe(
  //     map((response: any[]) => {
  //       const favoriteMoviesId = response.map(favorite => favorite.id);
  //       return favoriteMoviesId.includes(movieId);
  //     }),
  //     catchError(error => {
  //       console.error('Error fetching favorite movies:', error);
  //       return of(false); // Return false in case of error
  //     })
  //   );
  // }
  updateFavoriteStatus(): void {
    const userEmail = localStorage.getItem('userEmail')
    this.movieService.getFavoriteStatus(userEmail, this.selectedMovieId).subscribe(
      isFavorite => this.isFavorite = isFavorite,
      error => console.error('Error fetching favorite status:', error)
    );
  }
  toggleFavorite(): void {
    const userEmail = localStorage.getItem('userEmail');
    if (!userEmail) {
      alert('User is not logged in. Please log in to add favorites.');
      return;
    }
    this.movieService.toggleFavorite(userEmail, this.selectedMovieId).subscribe(
      () => {
        this.isFavorite = !this.isFavorite;
        console.log('Toggle favorite success');
      },
      error => console.error('Toggle favorite error:', error)
    );
  }
  // checkIsFavorite(): void {
  //   // Assume you have a method in MovieService to fetch user's favorite movies
  //   const userEmail = localStorage.getItem('userEmail');

  //   this.movieService.getAllFavorites(userEmail).subscribe(favoriteMovies => {
  //     this.isFavorite = favoriteMovies.some(movie => movie.id === this.movieId);
  //   });
  // }
  // toggleFavorite(): void {
  //   if (this.authService.isLoggedIn()) {
  //     const userEmail = localStorage.getItem('userEmail');
  //     if (this.isFavorite) {
  //       this.movieService.removeFromFavorites(userEmail, this.movieId).subscribe(() => {
  //         this.isFavorite = false;
  //       });
  //     } else {
  //       this.movieService.addToFavorites(userEmail, this.movieId).subscribe(() => {
  //         this.isFavorite = true;
  //       });
  //     }
  //   }
  // }
  isMoviePurchased(event:boolean) {
    this.ngZone.run(() => {
      this.movieOwned = event;
    })
  }

  getMovieOwned() {
    this.movieService.getMovieOwnedStatus(this.selectedMovieId).subscribe((owned) => {
      this.movieOwned = owned;
    })
  }
}
