package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.position.PositionEntityMapper;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.TotalStandsEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StationEntityMapper {

    private final PositionEntityMapper positionEntityMapper;

    private final TotalStandsEntityMapper totalStandsEntityMapper;


    public StationEntityMapper(PositionEntityMapper positionEntityMapper, TotalStandsEntityMapper totalStandsEntityMapper) {
        this.positionEntityMapper = positionEntityMapper;
        this.totalStandsEntityMapper = totalStandsEntityMapper;
    }

    public StationEntity stationToStationEntity(Station station) {
        return new StationEntity(station.id(), positionEntityMapper.positionToPositionEntity(station.position()),
                totalStandsEntityMapper.totalStandsToTotalStandsEntity(station.totalStands()));
    }

    public Station stationEntitytoStation(StationEntity stationEntity) {
        return new Station(stationEntity.getNumber(), positionEntityMapper
                .positionEntitytoPosition(stationEntity.getPosition()),
                totalStandsEntityMapper.totalStandsEntityToTotalStands(stationEntity.getTotalStands()));
    }
}
