package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.domain.station.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    private final StationDtoMapper stationDtoMapper;

    public StationController(StationService stationService, StationDtoMapper stationDtoMapper) {
        this.stationService = stationService;
        this.stationDtoMapper = stationDtoMapper;
    }

    @GetMapping
    public List<StationDto> getAllStations(){
        List<Station> listStation;
        listStation = stationService.getAllStations();
        return listStation.stream().map(stationDtoMapper::stationToStationDto).toList();
    }
}
