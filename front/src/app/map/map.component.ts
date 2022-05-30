import { Component, OnInit } from '@angular/core';
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

    const geocoder = new MapboxGeocoder({
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      placeholder: 'Recherchez dans Lyon',
      marker: false,
      mapboxgl: map,
      flyTo: true
    });
    map.addControl(geocoder);

    geocoder.on('result', (event) => {
      let positionRecherche: mapboxgl.Marker;
      timer(3000).subscribe(x => {
        positionRecherche = new mapboxgl.Marker().setLngLat([map.getCenter().lng, map.getCenter().lat])
        positionRecherche.addTo(map)
      })
    });
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
}
