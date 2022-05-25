package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.infrastructure.repository.station.position.PositionEntity;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.TotalStandsEntity;

import java.util.Objects;

public class StationEntity {
    private final Integer number;

    private final PositionEntity position;

    private final TotalStandsEntity totalStands;

    public StationEntity(Integer number, PositionEntity position, TotalStandsEntity totalStands) {
        this.number = number;
        this.position = position;
        this.totalStands = totalStands;
    }

    public Integer getNumber() {
        return number;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public TotalStandsEntity getTotalStands() {
        return totalStands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return Objects.equals(number, that.number) && Objects.equals(position, that.position) && Objects.equals(totalStands, that.totalStands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position, totalStands);
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "number=" + number +
                ", position=" + position +
                ", totalStands=" + totalStands +
                '}';
    }
}
