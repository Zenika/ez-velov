package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import org.springframework.stereotype.Component;

@Component
public class StationDtoMapper {

    PositionDtoMapper positionDtoMapper;

    public StationDtoMapper(PositionDtoMapper positionDtoMapper) {
        this.positionDtoMapper = positionDtoMapper;
    }

    StationDto stationToStationDto(Station station){
        return new StationDto(positionDtoMapper.positionToPositionDto(station.getPosition()));
    }

    Station stationDtotoStation(StationDto stationDto){
        return new Station(0, positionDtoMapper.positionDtotoPosition(stationDto.positionDto()));
    }
}
