package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDtoMapper;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.TotalStandsDto;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.TotalStandsDtoMapper;
import com.zenika.lyon.ezvelov.domain.station.Station;
import org.springframework.stereotype.Component;

@Component
public class StationDtoMapper {

    private final PositionDtoMapper positionDtoMapper;

    private final TotalStandsDtoMapper totalStandsDtoMapper;

    public StationDtoMapper(PositionDtoMapper positionDtoMapper, TotalStandsDtoMapper totalStandsDtoMapper) {
        this.positionDtoMapper = positionDtoMapper;
        this.totalStandsDtoMapper = totalStandsDtoMapper;
    }

    StationDto stationToStationDto(Station station) {
        return new StationDto(positionDtoMapper.positionToPositionDto(station.position()),
                totalStandsDtoMapper.totalStandsToTotalStandsDto(station.totalStands()));
    }

    Station stationDtotoStation(StationDto stationDto) {
        return new Station(null, positionDtoMapper.positionDtotoPosition(stationDto.positionDto()),
                totalStandsDtoMapper.totalStandsDtoToTotalStands(stationDto.totalStandsDto()));
    }
}
