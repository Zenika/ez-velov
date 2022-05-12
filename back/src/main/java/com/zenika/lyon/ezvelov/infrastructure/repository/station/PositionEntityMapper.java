package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.domain.station.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionEntityMapper {

    Position positionEntitytoPosition(PositionEntity positionProvider){
        return new Position(positionProvider.longitude, positionProvider.latitude);
    }

    PositionEntity positionToPositionEntity(Position position){
        return new PositionEntity(position.longitude, position.latitude);
    }
}
