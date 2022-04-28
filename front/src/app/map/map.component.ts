import { Component, OnInit } from '@angular/core';
import * as mapboxgl from "mapbox-gl";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    let map = new mapboxgl.Map({
      style: "mapbox://styles/scorpion6912/cl2iy7ygp004n15mxim5z3l9k",
      accessToken: 'pk.eyJ1Ijoic2NvcnBpb242OTEyIiwiYSI6ImNsMmVoMXFwbjAwbm0zaW1rdjUzcnRrZ2IifQ.bp5c4G0lq1UsWSRJbLnfVg',
      container: 'map',
      center: [4.835659, 45.764043],
      zoom: 12
    })

    map.on('click', (event) => {
      const features = map.queryRenderedFeatures(event.point, {
        layers: ['lyon-velov']
      });

      if (!features.length) {return;}

      const feature = features[0];
      new mapboxgl.Popup({ offset: [0,-15] })
        .setLngLat(event.lngLat)
        .setHTML(
          `<div id="popup">
            <h3>${feature.id} i'm the id</h3><p>génial ça marche</p>
          </div>
          <style>#popup {background-color: antiquewhite; padding: 0; margin: 0;}</style>
          `
        )
        .addTo(map);
    });
  }
}
