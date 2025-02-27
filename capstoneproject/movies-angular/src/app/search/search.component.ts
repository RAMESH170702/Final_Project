import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  searchTitle:string = '';
  @Output() searchMovie: EventEmitter<string> = new EventEmitter<string>();
  constructor() { }
  ngOnInit(): void { }
  search() {
    console.log(("here it is" + this.searchTitle));
    this.searchMovie.emit(this.searchTitle);
  } 

}
