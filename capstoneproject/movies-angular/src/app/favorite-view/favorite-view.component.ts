import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-favorite-view',
  templateUrl: './favorite-view.component.html',
  styleUrl: './favorite-view.component.css'
})
export class FavoriteViewComponent {
  movies: any[] = [];
  currentIndex: number = 0;
  carouselTransform = 'translateX(0)';
  containerWidth: number = 0;
  visibleItems: number = 0;
  
  constructor(private http: HttpClient) {
  }
  
  ngOnInit(): void {
    const userEmail = localStorage.getItem('userEmail');
    this.http.get<any[]>(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}`)
    .subscribe(response => {
      console.log("Fetched", this.movies)
      this.movies = response;
    }, error => {
      console.error('Error fetching movies:', error);
    });
  }
}
