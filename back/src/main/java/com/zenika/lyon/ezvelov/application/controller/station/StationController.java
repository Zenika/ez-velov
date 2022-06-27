package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDto;
import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDtoMapper;
import com.zenika.lyon.ezvelov.domain.station.StationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    private final StationDtoMapper stationDtoMapper;

    private final PositionDtoMapper positionDtoMapper;

    public StationController(StationService stationService, StationDtoMapper stationDtoMapper, PositionDtoMapper positionDtoMapper) {
        this.stationService = stationService;
        this.stationDtoMapper = stationDtoMapper;
        this.positionDtoMapper = positionDtoMapper;
    }

    @GetMapping
    public List<StationDto> getAllStations() {
        return stationService.getAllStations().stream()
                .map(stationDtoMapper::stationToStationDto)
                .toList();
    }

    @PostMapping("/proche")
    public StationDto getStationLaPlusProche(@RequestBody @Valid PositionDto positionDto) {
        return stationDtoMapper.stationToStationDto(
                stationService.getStationLaPlusProche(positionDtoMapper.positionDtotoPosition(positionDto)));
    }
}
