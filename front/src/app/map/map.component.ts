import {Component, OnInit} from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import {StationService} from "../station.service";
import * as MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';
import {timer} from 'rxjs';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  private rechercheDestination?: boolean;

  private coordsStart?: number[];

  private coordsEnd?: number[];

  public isPositionOk: boolean = false;

  public addDuree: boolean = false;

  public dureeTrajet!: string;

  public instructionTrajet!: string;

  public map!: mapboxgl.Map;

  public geolocalisation!: mapboxgl.GeolocateControl;

  private timerGeolocatlisation = 2000;

  private timerFlyingMap = 3000;

  constructor(private stationService: StationService) {
  }

  ngOnInit(): void {
    this.map = this.initMap();
    this.initStations(this.map);
    this.geolocalisation = this.initGeolocation(this.map);
    this.triggerGeolocation(this.map, this.geolocalisation);
  }

  public returnDureeTrajet(): string {
    return this.dureeTrajet;
  }

  public returnInstructionTrajet(): string {
    return this.instructionTrajet;
  }

  public setCoordsOfStartToPosition(map: mapboxgl.Map, geolocalisation: mapboxgl.GeolocateControl): void {
    geolocalisation.trigger();
    timer(this.timerGeolocatlisation).subscribe(() => {
      this.coordsStart = [map.getCenter().lng, map.getCenter().lat]
      this.isPositionOk = true;
    });
  }

  public setCoordsOfEndToPosition(map: mapboxgl.Map, geolocalisation: mapboxgl.GeolocateControl): void {
    this.rechercheDestination = true;
    geolocalisation.trigger();
    timer(this.timerGeolocatlisation).subscribe(() => {
      this.coordsEnd = [map.getCenter().lng, map.getCenter().lat]
      this.isPositionOk = true;
    });
  }

  private initRoute(map: mapboxgl.Map, coords: string): void {
    let trajetAVelo = document.getElementById('trajetAVelo') as HTMLInputElement | null
    let trajetAPied = document.getElementById('trajetAPied') as HTMLInputElement | null
    let apiCallForRoute: string;
    removeRoute();

    if (trajetAPied?.checked && !trajetAVelo?.checked) {
      apiCallForRoute = 'https://api.mapbox.com/directions/v5/mapbox/walking/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    } else {
      apiCallForRoute = 'https://api.mapbox.com/directions/v5/mapbox/cycling/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    }
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    this.addDuree = true;
    xmlHttpRequest.open('GET', apiCallForRoute, true);
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
      drawRoute(coords);
    };
    xmlHttpRequest.send();
    this.returnDureeTrajet();
    this.returnInstructionTrajet();

    function drawRoute(coords: any) {
      if (map.getSource('route')) {
        map.removeLayer('route');
        map.removeSource('route')
      } else {
        map.addLayer({
          "id": "route",
          "type": "line",
          "source": {
            "type": "geojson",
            "data": {
              "type": "Feature",
              "properties": {},
              "geometry": coords
            }
          },
          "layout": {
            "line-join": "round",
            "line-cap": "round"
          },
          "paint": {
            "line-color": "red",
            "line-width": 8,
            "line-opacity": 0.8
          }
        });
      }
    }

    function removeRoute() {
      if (map.getSource('route')) {
        map.removeLayer('route');
        map.removeSource('route');
      } else {
        return;
      }
    }
  }

  private initMap(): mapboxgl.Map {
    return new mapboxgl.Map({
      style: "mapbox://styles/scorpion6912/cl2rcl9oe006i14o1hqa9s4n8",
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      container: 'map',
      center: [4.835659, 45.764043],
      zoom: 12
    });
  }

  private initStations(map: mapboxgl.Map): void {

    this.addPointsOnMap(map);

    this.initRechercheDepart(map);

    this.initRechercheDestination(map);
  }

  initRechercheDestination(map: mapboxgl.Map): void {
    const rechercheDestination = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Destination',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });

    document.getElementById('rechercheDestination')?.appendChild(rechercheDestination.onAdd(map));

    rechercheDestination.on('result', () => {
      timer(this.timerFlyingMap).subscribe(() => {
        let rechercheTrajet = document.getElementById('rechercheTrajet') as HTMLInputElement | null
        if (rechercheTrajet?.checked) {
          this.rechercheDestination = true;
          new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat]).addTo(map);
          this.coordsEnd = [map.getCenter().lng, map.getCenter().lat];
          let newcoords = this.coordsStart?.toString() + ';' + this.coordsEnd?.toString();
          this.initRoute(map, newcoords);
        }
      })
    })
  }

  initRechercheDepart(map: mapboxgl.Map): void {
    const rechercheDepart = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Départ',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });

    document.getElementById('rechercheDepart')?.appendChild(rechercheDepart.onAdd(map));
    timer(3500).subscribe(() => {
      this.coordsStart = [map.getCenter().lng, map.getCenter().lat];
    })

    rechercheDepart.on('result', () => {
      let trajet = document.getElementById('rechercheTrajet') as HTMLInputElement | null
      let positionRecherche: mapboxgl.Marker;
      timer(this.timerFlyingMap).subscribe(() => {
        positionRecherche = new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat])
        positionRecherche.addTo(map)
        if (trajet?.checked) {
          this.coordsStart = [map.getCenter().lng, map.getCenter().lat];
          if (this.rechercheDestination) {
            let newcoords = this.coordsStart?.toString() + ';' + this.coordsEnd?.toString();
            this.initRoute(map, newcoords);
          }
        }
      })
    });
  }

  addPointsOnMap(map: mapboxgl.Map): void {
    this.stationService.getAllStations()
      .subscribe(stations => {
        stations.forEach((station) => {
          const {longitude, latitude} = station?.positionDto;
          const {capacity, availabilitiesDto} = station?.totalStandsDto;
          new mapboxgl.Marker().setLngLat([longitude, latitude]).setPopup(
            new mapboxgl.Popup({offset: [0, -15]}).setLngLat([longitude, latitude])
              .setHTML(
                this.infosStations(capacity, availabilitiesDto.stands))
          ).addTo(map)

        })
      });
  }

  private initGeolocation(map: mapboxgl.Map): mapboxgl.GeolocateControl {
    const geolocalisation = new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true,
      },
      trackUserLocation: true,
      showUserHeading: true,
      showAccuracyCircle: true
    })
    map.addControl(geolocalisation);
    return geolocalisation;
  }

  private triggerGeolocation(map: mapboxgl.Map, geolocalisation: mapboxgl.GeolocateControl): void {
    map.on('load', function () {
      geolocalisation.trigger();
    });
  }

  infosStations(capacity: number, placeDispo: number): string {
    return 'capacité : ' + capacity + '<br>' + 'places disponibles : ' + placeDispo
  }
}
