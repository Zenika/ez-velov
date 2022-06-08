import { Component, OnInit } from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import {StationService} from "../station.service";
import {Station} from "../station";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  constructor(private stationService: StationService) { }

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
    this.stationService.getAllStations()
      .subscribe(stations => {
        stations.forEach((station) => {
          const {longitude, latitude} = station?.positionDto;
          new mapboxgl.Marker().setLngLat([longitude,latitude]).addTo(map)
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
}
