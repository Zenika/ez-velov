package com.zenika.lyon.ezvelov.infrastructure.repository.station.position;

import java.util.Objects;

public class PositionEntity {

    private final double longitude;

    private final double latitude;

    public PositionEntity(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionEntity that = (PositionEntity) o;
        return Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    @Override
    public String toString() {
        return "PositionEntity{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
