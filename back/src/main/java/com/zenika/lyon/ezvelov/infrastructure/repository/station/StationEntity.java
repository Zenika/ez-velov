package com.zenika.lyon.ezvelov.infrastructure.repository.station;

import java.util.Objects;

public class StationEntity {
    private final int number;

    private final PositionEntity position;

    public StationEntity(int number, PositionEntity position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
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
                "number=" + number +
                ", position=" + position +
                '}';
    }
}
