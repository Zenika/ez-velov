package com.zenika.lyon.ezvelov.infrastructure.repository.station.position;

import com.zenika.lyon.ezvelov.domain.station.position.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionEntityMapper {

    public Position positionEntitytoPosition(PositionEntity positionProvider){
        return new Position(positionProvider.getLongitude(), positionProvider.getLatitude());
    }

    public PositionEntity positionToPositionEntity(Position position){
        return new PositionEntity(position.longitude(), position.latitude());
    }
}
