package com.zenika.lyon.ezvelov.application.controller.station;


import com.zenika.lyon.ezvelov.domain.station.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionDtoMapper {

    Position positionDtotoPosition(PositionDto positionDto){
        return new Position(positionDto.longitude, positionDto.latitude);
    }

    PositionDto positionToPositionDto(Position position){
        return new PositionDto(position.longitude, position.latitude);
    }
}
