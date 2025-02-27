import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrl: './favorite.component.css'
})
export class FavoriteComponent implements OnInit{
  movies: any[] = [];
  currentIndex: number = 0;
  carouselTransform = 'translateX(0)';
  containerWidth: number = 0;
  visibleItems: number = 0;
  
  constructor(private http: HttpClient, private elementRef: ElementRef) {
  }
  
  ngOnInit(): void {
    console.log("Calling favorite")
    const userEmail = localStorage.getItem('userEmail');
    this.http.get<any[]>(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}`)
    .subscribe(response => {
      console.log("Fetched", this.movies)
      this.movies = response;
    }, error => {
      console.error('Error fetching movies:', error);
    });
    this.containerWidth = this.elementRef.nativeElement.querySelector('.carousel-container1').clientWidth;
    this.visibleItems = Math.floor(this.containerWidth / 200); // Adjust this value according to your card width
  }

  // get carouselTransform(): string {
  //   return `translateX(calc(-100% * ${this.currentIndex}))`;
  // }

  scroll(direction: 'prev' | 'next'): void {
    const itemWidth = 200; // Adjust this value according to your card width
    // const containerWidth = 230; // Adjust this value according to your container width
    // const visibleItems = Math.floor(this.containerWidth / itemWidth);
    const scrollDistance = itemWidth * this.visibleItems;
    console.log('Before scroll - currentIndex:', this.currentIndex);


    if (direction === 'prev' && this.currentIndex > 0) {
      this.currentIndex = Math.max(0, this.currentIndex - 1);
    } else if (direction === 'next') {
      this.currentIndex = Math.min(this.movies.length - this.visibleItems, this.currentIndex + 1);
    }
    console.log('After scroll - currentIndex:', this.currentIndex);


    this.carouselTransform = `translateX(-${scrollDistance * this.currentIndex}px)`;
  }

  isPrevDisabled(): boolean {
    return this.currentIndex === 0;
  }

  isNextDisabled(): boolean {
    // const visibleItems = Math.floor(this.containerWidth / 250); // Adjust this value according to your card width
    return this.movies.length <= 3 || this.currentIndex === this.movies.length - this.visibleItems;
  }
}
