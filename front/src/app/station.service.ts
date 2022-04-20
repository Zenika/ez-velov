import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StationService {

  private userUrl = 'http://localhost:8080/station';

  constructor(
    private httpClient: HttpClient) {
  }

  public getAllStationsbyContract(): Observable<String> {
    return this.httpClient.get(this.userUrl, {responseType: 'text'});
  }
}
