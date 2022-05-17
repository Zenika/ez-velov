package com.zenika.lyon.ezvelov.application.controller.position;


import com.zenika.lyon.ezvelov.domain.position.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionDtoMapper {

    public Position positionDtotoPosition(PositionDto positionDto){
        return new Position(positionDto.longitude(), positionDto.latitude());
    }

    public PositionDto positionToPositionDto(Position position){
        return new PositionDto(position.getLongitude(), position.getLatitude());
    }
}
