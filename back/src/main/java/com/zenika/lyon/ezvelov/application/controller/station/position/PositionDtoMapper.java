package com.zenika.lyon.ezvelov.application.controller.station.position;


import com.zenika.lyon.ezvelov.domain.station.position.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionDtoMapper {

    public Position positionDtotoPosition(PositionDto positionDto){
        return new Position(positionDto.longitude(), positionDto.latitude());
    }

    public PositionDto positionToPositionDto(Position position){
        return new PositionDto(position.longitude(), position.latitude());
    }
}
