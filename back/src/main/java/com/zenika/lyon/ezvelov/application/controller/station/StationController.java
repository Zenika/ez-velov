package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.domain.station.StationService;
import com.zenika.lyon.ezvelov.infrastructure.provider.station.StationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
public class StationController {

    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String getAllStations(){
        return stationService.getAllStations();
    }
}
