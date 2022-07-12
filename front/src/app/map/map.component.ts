import {Component, OnInit} from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import {StationService} from "../station.service";
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';
import {timer} from 'rxjs';
import {Position} from "../position";
import {MapboxService} from "./mapbox.service";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  private readonly TIMER_GEOLOCALISATION = 2000;

  private readonly TIMER_FLYING_MAP = 3000;

  private rechercheDestination?: boolean;

  public isPositionOk: boolean = false;

  public afficherDureeTrajet: boolean = false;

  public map!: mapboxgl.Map;

  public geolocalisation!: mapboxgl.GeolocateControl;

  public positionDepart!: Position;

  public positionArrivee?: Position;
  
  public dureeTrajet?: string;

  public instructionTrajet?: string;

  constructor(private stationService: StationService,
              public mapboxService: MapboxService) {
  }

  ngOnInit(): void {
    this.map = this.mapboxService.initMap();
    this.initGeolocation();
    this.triggerGeolocation();
  }

  ngAfterViewInit(): void {
    this.initStations();
  }

  private initGeolocation(): void {
    this.geolocalisation = this.mapboxService.initGeolocation();
    this.map.addControl(this.geolocalisation);
  }

  public affichageTrajetStationLaPlusProche(): void {
    this.stationService.findStationLaPlusProche(this.positionDepart).subscribe(station => {
      this.positionArrivee = {
        longitude: station.positionDto.longitude,
        latitude: station.positionDto.latitude,
      };
      this.creerRoute();
    })
  }

  public setCoordsOfStartToPosition(): void {
    this.geolocalisation.trigger();
    timer(this.TIMER_GEOLOCALISATION).subscribe(() => {
      this.positionDepart = {
        longitude: this.map.getCenter().lng,
        latitude: this.map.getCenter().lat
      }
      this.isPositionOk = true;
    });
  }

  public setCoordsOfEndToPosition(): void {
    this.rechercheDestination = true;
    this.geolocalisation.trigger();
    timer(this.TIMER_GEOLOCALISATION).subscribe(() => {
      this.positionArrivee = {
        longitude: this.map.getCenter().lng,
        latitude: this.map.getCenter().lat,
      };
      this.isPositionOk = true;
    });
  }

  private creerRoute(): void {
    this.mapboxService.removeRoute(this.map);
    this.afficherDureeTrajet = true;
    this.calculerInformationsItineraire();
  }


  private initStations(): void {

    this.addPointsOnMap();

    this.initRechercheDepart();

    this.initRechercheDestination();
  }

  private initRechercheDestination(): void {
    const rechercheDestination = this.mapboxService.initRechercheDestination(this.map);

    document.getElementById('rechercheDestination')?.appendChild(rechercheDestination.onAdd(this.map));

    rechercheDestination.on('result', () => {
      timer(this.TIMER_FLYING_MAP).subscribe(() => {
        let rechercheTrajet = document.getElementById('rechercheTrajet') as HTMLInputElement | null
        if (rechercheTrajet?.checked) {
          this.rechercheDestination = true;
          new mapboxgl.Marker().setLngLat([this.map.getCenter().lng, this.map.getCenter().lat]).addTo(this.map);
          this.positionArrivee = {
            longitude: this.map.getCenter().lng,
            latitude: this.map.getCenter().lat,
          };
          this.creerRoute();
        }
      })
    })
  }

  private initRechercheDepart(): void {
    const rechercheDepart = this.mapboxService.initRechercheDepart(this.map);

    document.querySelector('#rechercheDepart')?.appendChild(rechercheDepart.onAdd(this.map));
    timer(this.TIMER_FLYING_MAP).subscribe(() => {
      this.positionDepart = {
        longitude: this.map.getCenter().lng,
        latitude: this.map.getCenter().lat
      }
    })

    rechercheDepart.on('result', () => {
      let trajet = document.getElementById('rechercheTrajet') as HTMLInputElement | null
      let positionRecherche: mapboxgl.Marker;
      timer(this.TIMER_FLYING_MAP).subscribe(() => {
        positionRecherche = new mapboxgl.Marker().setLngLat([this.map.getCenter().lng, this.map.getCenter().lat])
        positionRecherche.addTo(this.map)
        if (trajet?.checked) {
          this.positionDepart = {
            longitude: this.map.getCenter().lng,
            latitude: this.map.getCenter().lat
          }
          if (this.rechercheDestination) {
            this.creerRoute();
          }
        }
      })
    });
  }

  private addPointsOnMap(): void {
    this.stationService.getAllStations()
      .subscribe(stations => {
        stations.forEach((station) => {
          const {longitude, latitude} = station?.positionDto;
          const {capacity, availabilitiesDto} = station?.totalStandsDto;
          new mapboxgl.Marker().setLngLat([longitude, latitude]).setPopup(
            new mapboxgl.Popup({offset: [0, -15]}).setLngLat([longitude, latitude])
              .setHTML(
                this.infosStations(capacity, availabilitiesDto.stands))
          ).addTo(this.map)
        })
      });
  }

  private triggerGeolocation(): void {
    this.map.on('load', () => {
      this.geolocalisation.trigger();
    });
  }

  private infosStations(capacity: number, placeDispo: number): string {
    return 'capacité : ' + capacity + '<br>' + 'places disponibles : ' + placeDispo
  }

  private calculerInformationsItineraire() {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    xmlHttpRequest.open('GET', this.mapboxService.buildInformationsItineraireUrl(this.positionDepart, this.positionArrivee), true);
    xmlHttpRequest.onload = () => {
      let apiDirectionResponse = xmlHttpRequest.response;
      let duration = apiDirectionResponse.routes[0].duration / 60;
      let stepsOfRoad = apiDirectionResponse.routes[0].legs[0].steps;
      let coords = apiDirectionResponse.routes[0].geometry;
      let instructionsTrajet = '';
      stepsOfRoad.forEach(function (step: any) {
        instructionsTrajet += step.maneuver.instruction;
      });
      this.instructionTrajet = instructionsTrajet;

      this.dureeTrajet = duration.toFixed(2)
      this.mapboxService.dessinerItineraire(this.map, coords);
    };
    xmlHttpRequest.send();
  }
}
