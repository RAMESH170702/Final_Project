import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatChipSelectionChange } from '@angular/material/chips';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {
 
  movies: any[] = [];
  movie: any = null;
  pageNo: number = 1;
  searchTitle: string = "";
  genreId: number = 0;
  genres: any[] = [
    {
      "id": 28,
      "name": "Action"
    },
    {
      "id": 12,
      "name": "Adventure"
    },
    {
      "id": 16,
      "name": "Animation"
    },
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 80,
      "name": "Crime"
    },
    {
      "id": 99,
      "name": "Documentary"
    },
    {
      "id": 18,
      "name": "Drama"
    },
    {
      "id": 10751,
      "name": "Family"
    },
    {
      "id": 14,
      "name": "Fantasy"
    },
    {
      "id": 36,
      "name": "History"
    },
    {
      "id": 27,
      "name": "Horror"
    },
    {
      "id": 10402,
      "name": "Music"
    },
    {
      "id": 9648,
      "name": "Mystery"
    },
    {
      "id": 10749,
      "name": "Romance"
    },
    {
      "id": 878,
      "name": "Science Fiction"
    },
    {
      "id": 10770,
      "name": "TV Movie"
    },
    {
      "id": 53,
      "name": "Thriller"
    },
    {
      "id": 10752,
      "name": "War"
    },
    {
      "id": 37,
      "name": "Western"
    }
  ]
  gradientColor = 'linear-gradient(to right, #76eec6, #00bfff)';
  borderColor = '#000000'; // Black color for border



  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.fetchMovies();
  }

  fetchMovies(): void {
    this.http.get<any[]>(`http://localhost:9000/api/v4/movies/page/${this.pageNo}`)
      .subscribe(response => {
        this.movies = response;
      }, error => {
        console.error('Error fetching movies:', error);
      });
  }

  nextPage(): void {
    this.pageNo++;
    this.fetchMovies();
  }

  previousPage(): void {
    if (this.pageNo > 0) {
      this.pageNo--;
      this.fetchMovies();
    }
  }

  fetchMoviesByGenre(event:MatChipSelectionChange): void {
    if (!event.source.selected) {
      this.fetchMovies();
      return
    }

    let genre:any = this.genres.findIndex((genre:any) => genre.name === event.source.value)
    if (genre != -1) {
      let genreId = this.genres[genre].id;
      this.http.get<any[]>(`http://localhost:9000/api/v4/movies/movie/genre/${genreId}/${this.pageNo}`)
      .subscribe(response => {
        this.movies = response;
      }, error => {
        console.error('Error fetching movies:', error);
      });
    }
  }
  onSearch(searchTitle: string) {
    this.searchTitle = searchTitle;
    if (searchTitle === '' || !searchTitle) {
      this.fetchMovies();
    }    
    else {
      this.http.get<any[]>(`http://localhost:9000/api/v4/movies/${this.searchTitle}`)
      .subscribe(data => {
        this.movies = data;
        console.log(this.movies)
        this.movies = this.movies.filter(movie => movie.poster_path != null)
      })
    }
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
  getChipStyle() {
    return {
      // 'background-color': 'rgb(3, 37, 65)',
      // 'color': 'white !important',
      // 'border': '1px solid black' 
    };
  }

}
