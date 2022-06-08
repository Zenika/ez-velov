package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.domain.station.position.Position;
import com.zenika.lyon.ezvelov.domain.station.totalStands.TotalStands;
import com.zenika.lyon.ezvelov.domain.station.totalStands.availabilities.Availabilities;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationProviderMock implements IRequestStationStore {

    public List<Station> getAllStations() {

        return List.of(
                new Station(2010, new Position(4.815747, 45.743317),
                        new TotalStands(20, new Availabilities(8))),
                new Station(5015, new Position(4.821662, 45.75197),
                        new TotalStands(20, new Availabilities(8))),
                new Station(32001, new Position(4.832409, 45.846034),
                        new TotalStands(20, new Availabilities(8)))
        );
    }
}
