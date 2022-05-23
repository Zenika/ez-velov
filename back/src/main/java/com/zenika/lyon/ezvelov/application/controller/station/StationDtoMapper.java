package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDtoMapper;
import com.zenika.lyon.ezvelov.domain.station.Station;
import org.springframework.stereotype.Component;

@Component
public class StationDtoMapper {

    private final PositionDtoMapper positionDtoMapper;

    StationDtoMapper(PositionDtoMapper positionDtoMapper) {
        this.positionDtoMapper = positionDtoMapper;
    }

    StationDto stationToStationDto(Station station) {
        return new StationDto(positionDtoMapper.positionToPositionDto(station.position()));
    }

    Station stationDtotoStation(StationDto stationDto) {
        return new Station(null, positionDtoMapper.positionDtotoPosition(stationDto.positionDto()));
    }
}
