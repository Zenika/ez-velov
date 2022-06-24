package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDto;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.TotalStandsDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record StationDto(@Valid @NotNull PositionDto positionDto, @Valid @NotNull TotalStandsDto totalStandsDto) {
}
