package com.zenika.lyon.ezvelov.domain.station;

import com.zenika.lyon.ezvelov.domain.station.position.Position;
import com.zenika.lyon.ezvelov.domain.station.totalStands.TotalStands;

public record Station(Integer id, Position position, TotalStands totalStands) {
}
