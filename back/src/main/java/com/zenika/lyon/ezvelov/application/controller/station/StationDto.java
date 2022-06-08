package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDto;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.TotalStandsDto;

public record StationDto(PositionDto positionDto, TotalStandsDto totalStandsDto) {
}
