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

  private static c = 0;

  private static coordsstart: any;

  private static coordsend: any;

  constructor(private stationService: StationService) {
  }

  ngOnInit(): void {
    let map = this.initMap();
    this.initStations(map);
    let geolocator = this.initGeolocator(map);
    this.triggerGeolocator(map, geolocator);
  }


  private drawTrajet(map: mapboxgl.Map, coords: any): void {
    let bike = document.getElementById('bike') as HTMLInputElement | null
    let walk = document.getElementById('walk') as HTMLInputElement | null
    let time = document.getElementById('time')
    let instructions = document.getElementById('instructions');
    let apiCall: string;
    removeRoute();

    if (walk?.checked && bike?.checked) {
      alert("veuillez ne choisir qu'un type de trajet a la fois puis recommencer");
      apiCall = '';
    } else if (walk?.checked && !bike?.checked) {
      apiCall = 'https://api.mapbox.com/directions/v5/mapbox/walking/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    } else {
      apiCall = 'https://api.mapbox.com/directions/v5/mapbox/cycling/' + coords
        + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo' +
        'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
    }
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    xmlHttpRequest.open('GET', apiCall, true);
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
        + ' <br>Duration: ' + duration.toFixed(2)
        + ' minutes' + '</p>');

      addRoute(coords);
    };
    xmlHttpRequest.send();

    function addRoute(coords: any) {
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

    this.addFirstGeocoderOnMap(map);

    this.addSecondGeocoderOnMap(map);
  }

  addSecondGeocoderOnMap(map: mapboxgl.Map): void {
    const geocoderDest = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Destination',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });

    document.getElementById('geocoderDest')?.appendChild(geocoderDest.onAdd(map));

    geocoderDest.on('result', () => {
      timer(3000).subscribe(() => {
        let trajet = document.getElementById('trajet') as HTMLInputElement | null
        if (trajet?.checked) {
          MapComponent.c++;
          new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat]).addTo(map);
          MapComponent.coordsend = [map.getCenter().lng, map.getCenter().lat];
          let newcoords = MapComponent.coordsstart?.toString() + ';' + MapComponent.coordsend?.toString();
          this.drawTrajet(map, newcoords);
        }
      })
    })
  }

  addFirstGeocoderOnMap(map: mapboxgl.Map): void {
    const geocoder = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Depart - Ma position',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });

    document.getElementById('geocoder')?.appendChild(geocoder.onAdd(map));
    timer(3500).subscribe(() => {
      MapComponent.coordsstart = [map.getCenter().lng, map.getCenter().lat];
    })

    geocoder.on('result', () => {
      let trajet = document.getElementById('trajet') as HTMLInputElement | null
      let positionRecherche: mapboxgl.Marker;
      timer(3000).subscribe(() => {
        positionRecherche = new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat])
        positionRecherche.addTo(map)
      })

      timer(3000).subscribe(() => {
        if (trajet?.checked) {
          MapComponent.coordsstart = [map.getCenter().lng, map.getCenter().lat];
          if (MapComponent.c > 0) {
            let newcoords = MapComponent.coordsstart?.toString() + ';' + MapComponent.coordsend?.toString();
            this.drawTrajet(map, newcoords);
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

  private initGeolocator(map: mapboxgl.Map): mapboxgl.GeolocateControl {
    const geolocator = new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true,
      },
      trackUserLocation: true,
      showUserHeading: true,
      showAccuracyCircle: true
    })
    map.addControl(geolocator);
    return geolocator;
  }

  private triggerGeolocator(map: mapboxgl.Map, geolocator: mapboxgl.GeolocateControl): void {
    map.on('load', function () {
      geolocator.trigger();
    });
  }

  infosStations(capacity: number, placeDispo: number): string {
    return 'capacité : ' + capacity + '<br>' + 'places disponibles : ' + placeDispo
  }
}
