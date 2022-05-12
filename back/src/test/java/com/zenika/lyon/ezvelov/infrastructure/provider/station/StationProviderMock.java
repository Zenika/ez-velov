package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StationProviderMock implements IRequestStationStore {

    public List<com.zenika.lyon.ezvelov.domain.station.Station> getAllStations() {
        List<com.zenika.lyon.ezvelov.domain.station.Station> expectedListStation = new ArrayList<>();
        expectedListStation.add(new com.zenika.lyon.ezvelov.domain.station.Station(
                0, new com.zenika.lyon.ezvelov.domain.station.Position(4.815747, 45.743317)));
        return expectedListStation;
    }
}
