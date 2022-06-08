package com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.availabilities;

import java.util.Objects;

public class AvailabilitiesEntity {
    private final int stands;

    public AvailabilitiesEntity(int stands) {
        this.stands = stands;
    }

    public int getStands() {
        return stands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilitiesEntity that = (AvailabilitiesEntity) o;
        return stands == that.stands;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stands);
    }

    @Override
    public String toString() {
        return "AvailabilitiesEntity{" +
                "stands=" + stands +
                '}';
    }
}
