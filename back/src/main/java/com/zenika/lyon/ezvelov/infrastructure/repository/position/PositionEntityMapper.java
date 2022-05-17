package com.zenika.lyon.ezvelov.infrastructure.repository.position;

import com.zenika.lyon.ezvelov.domain.position.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionEntityMapper {

    public Position positionEntitytoPosition(PositionEntity positionProvider){
        return new Position(positionProvider.longitude(), positionProvider.latitude());
    }

    public PositionEntity positionToPositionEntity(Position position){
        return new PositionEntity(position.getLongitude(), position.getLatitude());
    }
}
