package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.infrastructure.repository.position.PositionEntity;

public record StationEntity(int number, PositionEntity position) {
}
