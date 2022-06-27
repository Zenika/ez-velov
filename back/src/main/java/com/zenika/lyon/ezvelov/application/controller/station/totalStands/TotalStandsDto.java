package com.zenika.lyon.ezvelov.application.controller.station.totalStands;

import com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities.AvailabilitiesDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record TotalStandsDto(@Min(0) int capacity, @Valid @NotNull AvailabilitiesDto availabilitiesDto) {
}
