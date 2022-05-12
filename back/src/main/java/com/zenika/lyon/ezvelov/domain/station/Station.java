package com.zenika.lyon.ezvelov.domain.station;

import java.util.Objects;

public class Station {
    private final int number;

    private final Position position;

    public Station(int number, Position position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return number == station.number && Objects.equals(position, station.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }

    @Override
    public String toString() {
        return "Station{" +
                "number=" + number +
                ", position=" + position +
                '}';
    }
}
