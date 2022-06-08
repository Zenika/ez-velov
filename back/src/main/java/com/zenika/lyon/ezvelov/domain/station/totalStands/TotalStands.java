package com.zenika.lyon.ezvelov.domain.station.totalStands;

import com.zenika.lyon.ezvelov.domain.station.totalStands.availabilities.Availabilities;

public record TotalStands(int capacity, Availabilities availabilities) {
}
