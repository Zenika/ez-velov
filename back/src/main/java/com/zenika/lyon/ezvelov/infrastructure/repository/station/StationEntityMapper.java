package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.position.PositionEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StationEntityMapper {

    private final PositionEntityMapper positionEntityMapper;

    public StationEntityMapper(PositionEntityMapper positionEntityMapper) {
        this.positionEntityMapper = positionEntityMapper;
    }

    public StationEntity stationToStationEntity(Station station){
        return new StationEntity(station.number(), positionEntityMapper.positionToPositionEntity(station.position()));
    }

    public Station stationEntitytoStation(StationEntity stationEntity){
        return new Station(stationEntity.getNumber(), positionEntityMapper
                .positionEntitytoPosition(stationEntity.getPosition()));
    }
}
