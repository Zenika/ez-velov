package com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands;

import com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.availabilities.AvailabilitiesEntity;

import java.util.Objects;

public class TotalStandsEntity {
    private final int capacity;

    private final AvailabilitiesEntity availabilities;

    public TotalStandsEntity(int capacity, AvailabilitiesEntity availabilitiesEntity) {
        this.capacity = capacity;
        this.availabilities = availabilitiesEntity;
    }

    public int getCapacity() {
        return capacity;
    }

    public AvailabilitiesEntity getAvailabilities() {
        return availabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalStandsEntity that = (TotalStandsEntity) o;
        return capacity == that.capacity && Objects.equals(availabilities, that.availabilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, availabilities);
    }

    @Override
    public String toString() {
        return "TotalStandsEntity{" +
                "capacity=" + capacity +
                ", availabilitiesEntity=" + availabilities +
                '}';
    }
}
