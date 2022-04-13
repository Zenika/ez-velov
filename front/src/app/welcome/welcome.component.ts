import { Component, OnInit } from '@angular/core';
import {UtilisateurService} from "../utilisateur.service";
import {Utilisateur} from "../utilisateur";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  public utilisateur?: Utilisateur;

  constructor(private userService:UtilisateurService) { }

  ngOnInit(): void {
    this.userService.getUtilisateur()
      .subscribe(utilisateur => this.utilisateur = utilisateur)
  }

}
