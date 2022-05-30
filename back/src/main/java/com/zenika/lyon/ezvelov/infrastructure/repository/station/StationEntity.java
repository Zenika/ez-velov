package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import com.zenika.lyon.ezvelov.infrastructure.repository.station.position.PositionEntity;

import java.util.Objects;

public class StationEntity {
    private final Integer number;

    private final PositionEntity position;

    public StationEntity(Integer number, PositionEntity position) {
        this.number = number;
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public PositionEntity getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return number == that.number && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id=" + number +
                ", position=" + position +
                '}';
    }
}
