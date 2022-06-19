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

  private  coordsStart?: number[];

  private coordsEnd?: number[];

  constructor(private stationService: StationService) {
  }

  ngOnInit(): void {
    let map = this.initMap();
    this.initStations(map);
    let geolocalisation = this.initGeolocation(map);
    this.triggerGeolocation(map, geolocalisation);
    this.setCoordsOfStartOrEndToPosition(map, geolocalisation);
  }

  private setCoordsOfStartOrEndToPosition(map: mapboxgl.Map, geolocalisation: mapboxgl.GeolocateControl): void {
    let buttonSetStartingPositionOnUser = document.getElementById('buttonSetStartingPositionOnUser');
    let buttonSetEndingPositionOnUser = document.getElementById('buttonSetEndingPositionOnUser');
    let confirmationSetPosition = document.getElementById('confirmationAjoutPosition');
    buttonSetStartingPositionOnUser?.addEventListener('click', () => {
      geolocalisation.trigger()
      timer(2000).subscribe(() => {
        this.coordsStart = [map.getCenter().lng, map.getCenter().lat]
        confirmationSetPosition!.innerHTML = '';
        confirmationSetPosition?.insertAdjacentHTML('beforeend', '<p>'
          + 'Votre Position a bien été prise en compte comme départ' + '</p>');
      });
    })
    buttonSetEndingPositionOnUser?.addEventListener('click', () => {
      this.rechercheDestination = true;
      geolocalisation.trigger();
      timer(2000).subscribe(() => {
        this.coordsEnd = [map.getCenter().lng, map.getCenter().lat]
        confirmationSetPosition!.innerHTML = '';
        confirmationSetPosition?.insertAdjacentHTML('beforeend', '<p>'
          + 'Votre Position a bien été prise en compte comme destination' + '</p>');
      });
    })
  }

  private initRoute(map: mapboxgl.Map, coords: string): void {
    let choixBike = document.getElementById('choixBike') as HTMLInputElement | null
    let choixWalk = document.getElementById('choixWalk') as HTMLInputElement | null
    let time = document.getElementById('resultatTempsTrajet')
    let instructions = document.getElementById('instructionsTrajet');
    let apiCallForTrajet: string;
    removeRoute();

    if (choixWalk?.checked && choixBike?.checked) {
      alert("veuillez ne choisir qu'un type de trajet a la fois puis recommencer");
      apiCallForTrajet = '';
    } else if (choixWalk?.checked && !choixBike?.checked) {
      apiCallForTrajet = 'https://api.mapbox.com/directions/v5/mapbox/walking/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    } else {
      apiCallForTrajet = 'https://api.mapbox.com/directions/v5/mapbox/cycling/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    }
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    xmlHttpRequest.open('GET', apiCallForTrajet, true);
    xmlHttpRequest.onload = function () {
      let apiDirectionResponse = xmlHttpRequest.response;
      let duration = apiDirectionResponse.routes[0].duration / 60;
      let stepsOfRoad = apiDirectionResponse.routes[0].legs[0].steps;
      let coords = apiDirectionResponse.routes[0].geometry;


      stepsOfRoad.forEach(function (step: any) {
        instructions!.insertAdjacentHTML('beforeend', '<p>'
          + step.maneuver.instruction + '</p>');
      });

      time!.insertAdjacentHTML('beforeend', '<p>'
        + ' Durée: ' + duration.toFixed(2)
        + ' minutes' + '</p>');

      drawRoute(coords);
    };
    xmlHttpRequest.send();

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
        instructions!.innerHTML = '';
        time!.innerHTML = '';
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
      timer(3000).subscribe(() => {
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
      timer(3000).subscribe(() => {
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
