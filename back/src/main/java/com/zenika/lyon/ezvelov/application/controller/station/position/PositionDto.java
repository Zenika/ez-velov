package com.zenika.lyon.ezvelov.application.controller.station.position;

import javax.validation.constraints.NotNull;

public record PositionDto(@NotNull double longitude, @NotNull double latitude) {
}
