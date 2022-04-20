import {Component, OnInit} from '@angular/core';
import {StationService} from "../station.service";


@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css']
})
export class StationComponent implements OnInit {

  public stations?: String;

  constructor(private stationService: StationService) {
  }

  ngOnInit(): void {
    this.stationService.getAllStationsbyContract()
      .subscribe(stations => this.stations = stations);
  }

}
