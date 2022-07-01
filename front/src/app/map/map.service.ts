import { Injectable } from '@angular/core';
import * as mapboxgl from "mapbox-gl";
import {Position} from "../position";

@Injectable({
  providedIn: 'root'
})
export class MapService {

  public dureeTrajet!: string;

  public instructionTrajet!: string;

  public coordsEnd?: number[];

  public coordsStart!: Position;

  constructor() { }

  private getCreateRouteUrl() {
    const trajetAVelo = document.getElementById('trajetAVelo') as HTMLInputElement | null;
    const trajetAPied = document.getElementById('trajetAPied') as HTMLInputElement | null;
    const coords = this.coordsStart.longitude + ',' + this.coordsStart.latitude + ';' + this.coordsEnd;

    const typeTrajet = trajetAPied?.checked && !trajetAVelo?.checked
      ? 'walking/'
      : 'cycling/';

    return 'https://api.mapbox.com/directions/v5/mapbox/'
      + typeTrajet
      + coords
      + '?geometries=geojson&steps=true&access_token=pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVo'
      + 'MXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg';
  }

  getRouteInformations(map: mapboxgl.Map) {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    xmlHttpRequest.open('GET', this.getCreateRouteUrl(), true);
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
      this.drawRoute(map, coords);
    };
    xmlHttpRequest.send();
  }

  private drawRoute(map: mapboxgl.Map, coords: any) {
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

  removeRoute(map: mapboxgl.Map) {
    if (map.getSource('route')) {
      map.removeLayer('route');
      map.removeSource('route');
    }
  }
}
