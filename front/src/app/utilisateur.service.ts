import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Utilisateur} from "./utilisateur";

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private userUrl = 'http://localhost:8080/utilisateur';

  constructor(
    private httpClient: HttpClient
  ) { }

  public getUtilisateur(): Observable<Utilisateur> {
    return this.httpClient.get<Utilisateur>(this.userUrl);
  }
}
