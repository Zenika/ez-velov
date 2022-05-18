package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import org.springframework.stereotype.Component;
import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.domain.station.position.Position;

import java.util.List;

@Component
public class StationProviderMock implements IRequestStationStore {

    public List<Station> getAllStations() {

        return List.of(
                new Station(2010, new Position(4.815747, 45.743317)),
                new Station(5015, new Position(4.821662,45.75197)),
                new Station(32001, new Position(4.832409,45.846034))
        );
    }
}
