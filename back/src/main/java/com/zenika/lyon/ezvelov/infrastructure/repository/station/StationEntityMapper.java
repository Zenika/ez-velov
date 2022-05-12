package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import org.springframework.stereotype.Component;

@Component
public class StationEntityMapper {

    PositionEntityMapper positionEntityMapper;

    StationEntity stationToStationEntity(Station station){
        return new StationEntity(0, positionEntityMapper.positionToPositionEntity(station.getPosition()));
    }

    Station stationEntitytoStation(StationEntity stationEntity){
        return new Station(0, positionEntityMapper.positionEntitytoPosition(stationEntity.getPosition()));
    }
}
