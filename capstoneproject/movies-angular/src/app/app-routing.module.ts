import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SignupComponent } from './signup/signup.component';
import { ViewMovieComponent } from './view-movie/view-movie.component';
import { PlayerComponent } from './player/player.component';
import { authGuard } from './service/auth.guard';
import { OrdersComponent } from './orders/orders.component';
import { FavoriteViewComponent } from './favorite-view/favorite-view.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "", component: HomepageComponent },
  { path: "signup", component: SignupComponent },
  { path: "movie/:id", component: ViewMovieComponent },
  { path: "movie/:id/play", component:PlayerComponent, canActivate: [authGuard]},
  { path: "orders", component:OrdersComponent, canActivate: [authGuard]},
  { path: "favorites", component:FavoriteViewComponent, canActivate: [authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
