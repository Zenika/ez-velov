import {Injectable} from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import * as MapboxGeocoder from "@mapbox/mapbox-gl-geocoder";
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';

@Injectable({
  providedIn: 'root'
})
export class MapboxService {

  private mapboxAPIKey = 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';

  constructor() {
  }

  public initMap(): mapboxgl.Map {
    return new mapboxgl.Map({
      style: 'mapbox://styles/mapbox/streets-v11',
      accessToken: this.mapboxAPIKey,
      container: 'map',
      center: [4.835659, 45.764043],
      zoom: 12
    });
  }

  public initRechercheDestination(map: mapboxgl.Map): MapboxGeocoder {
    return new MapboxGeocoder({
      accessToken: this.mapboxAPIKey,
      placeholder: 'Destination',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });
  }

  public initRechercheDepart(map: mapboxgl.Map): MapboxGeocoder {
    return new MapboxGeocoder({
      accessToken: this.mapboxAPIKey,
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
