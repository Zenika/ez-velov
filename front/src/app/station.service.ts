import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Station} from "./station";

@Injectable({
  providedIn: 'root'
})
export class StationService {

  private userUrl = 'http://localhost:8080/station';

  constructor(
    private httpClient: HttpClient) {
  }

  public getAllStations(): Observable<Station[]> {
    return this.httpClient.get<Station[]>(this.userUrl);
  }
}
