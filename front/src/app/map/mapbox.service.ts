import {Injectable} from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import * as MapboxGeocoder from "@mapbox/mapbox-gl-geocoder";
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';
import { Position } from '../position';

@Injectable({
  providedIn: 'root'
})
export class MapboxService {

  private readonly MAPBOX_API_KEY = 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';

  constructor() {
  }

  public buildInformationsItineraireUrl(positionDepart: Position, positionArrivee?: Position) {
    const trajetAVelo = document.getElementById('trajetAVelo') as HTMLInputElement | null;
    const trajetAPied = document.getElementById('trajetAPied') as HTMLInputElement | null;
    const coordoneeArrivee = positionArrivee
    ? positionArrivee.longitude + ',' + positionArrivee.latitude
    : null;
    const coords =
    positionDepart.longitude + ','
    + positionDepart.latitude + ';'
    +  coordoneeArrivee;

    const typeTrajet = trajetAPied?.checked && !trajetAVelo?.checked
      ? 'walking/'
      : 'cycling/';

    return 'https://api.mapbox.com/directions/v5/mapbox/'
      + typeTrajet
      + coords
      + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo'
      + 'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
  }

  public dessinerItineraire(map: mapboxgl.Map, coords: any) {
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

  public removeRoute(map: mapboxgl.Map) {
    if (map.getSource('route')) {
      map.removeLayer('route');
      map.removeSource('route');
    }
  }

  public initMap(): mapboxgl.Map {
    return new mapboxgl.Map({
      style: 'mapbox://styles/mapbox/streets-v11',
      accessToken: this.MAPBOX_API_KEY,
      container: 'map',
      center: [4.835659, 45.764043],
      zoom: 12
    });
  }

  public initRechercheDestination(map: mapboxgl.Map): MapboxGeocoder {
    return new MapboxGeocoder({
      accessToken: this.MAPBOX_API_KEY,
      placeholder: 'Destination',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });
  }

  public initRechercheDepart(map: mapboxgl.Map): MapboxGeocoder {
    return new MapboxGeocoder({
      accessToken: this.MAPBOX_API_KEY,
      placeholder: 'Départ',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });
  }

  public initGeolocation(): mapboxgl.GeolocateControl {
    return new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true,
      },
      trackUserLocation: true,
      showUserHeading: true,
      showAccuracyCircle: true
    });
  }


}
