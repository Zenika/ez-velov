import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { StationComponent } from './station/station.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    StationComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
