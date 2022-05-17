package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.infrastructure.repository.position.PositionEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StationEntityMapper {

    PositionEntityMapper positionEntityMapper;

    StationEntity stationToStationEntity(Station station){
        return new StationEntity(station.getNumber(), positionEntityMapper.positionToPositionEntity(station.getPosition()));
    }

    Station stationEntitytoStation(StationEntity stationEntity){
        return new Station(stationEntity.number(), positionEntityMapper.positionEntitytoPosition(stationEntity.position()));
    }
}
