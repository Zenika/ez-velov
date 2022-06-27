package com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities;

import javax.validation.constraints.Min;

public record AvailabilitiesDto(@Min(0) int stands) {
}
