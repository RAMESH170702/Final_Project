import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteViewComponent } from './favorite-view.component';

describe('FavoriteViewComponent', () => {
  let component: FavoriteViewComponent;
  let fixture: ComponentFixture<FavoriteViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FavoriteViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FavoriteViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
