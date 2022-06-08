package com.zenika.lyon.ezvelov.application.controller.station.totalStands;

import com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities.AvailabilitiesDto;

public record TotalStandsDto(int capacity, AvailabilitiesDto availabilitiesDto) {
}
