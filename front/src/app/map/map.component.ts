import {Component, OnInit} from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import {StationService} from "../station.service";
import {Station} from "../station";
import * as MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';
import {timer} from 'rxjs';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  public stations?: Station[];

  constructor(private stationService: StationService) {
  }

  ngOnInit(): void {
    let map = this.initMap();
    this.initStations(map);
    let geolocator = this.initGeolocator(map);
    this.triggerGeolocator(map, geolocator);
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

    this.addGeocoderOnMap(map);
  }

  addGeocoderOnMap(map: mapboxgl.Map): void {
    const geocoder = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Recherchez dans Lyon',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });

    document.getElementById('geocoder')?.appendChild(geocoder.onAdd(map));

    geocoder.on('result', (event) => {
      let positionRecherche: mapboxgl.Marker;
      timer(3000).subscribe(x => {
        positionRecherche = new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat])
        positionRecherche.addTo(map)
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
    map.on('load', function() {
      geolocator.trigger();
    });
  }
  
  infosStations(capacity: number, placeDispo: number): string {
    return 'capacité : ' + capacity + '<br>' + 'places disponibles : ' + placeDispo
  }
}
