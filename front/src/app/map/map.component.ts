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

  private stations?: Station[];

  private geolocator?: mapboxgl.GeolocateControl;

  constructor(private stationService: StationService) { }

  ngOnInit(): void {
    let map = new mapboxgl.Map({
      style: "mapbox://styles/scorpion6912/cl2rcl9oe006i14o1hqa9s4n8",
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      container: 'map',
      center: [4.835659, 45.764043],
      zoom: 12
    });

    this.addPointsOnMap(map);


    this.geolocator = this.initGeolocate(map);

    this.loadUserGeoloc(this.geolocator, map);
  }

  addPointsOnMap(map: mapboxgl.Map): void{
    this.stationService.getAllStations()
      .subscribe(stations => {
        this.stations = stations
        stations.forEach((station) => {
          const {longitude, latitude} = station?.positionDto;
          new mapboxgl.Marker().setLngLat([longitude,latitude]).addTo(map)
        })
      });
  }

  initGeolocate(map: mapboxgl.Map): mapboxgl.GeolocateControl {
    const geolocate = new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy: true,
      },
      trackUserLocation: true,
      showUserHeading: true,
      showAccuracyCircle: true
    })
    map.addControl(geolocate);
    return geolocate;
  }

  loadUserGeoloc(geolocate: mapboxgl.GeolocateControl, map: mapboxgl.Map): void {
    map.on('load', function() {
      geolocate.trigger();
    });
  }
}
