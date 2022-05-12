package com.zenika.lyon.ezvelov.domain.station;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final IRequestStationStore iRequestStationStore;

    public StationService(IRequestStationStore iRequestStationStore) {
        this.iRequestStationStore = iRequestStationStore;
    }

    public List<Station> getAllStations() {
        return iRequestStationStore.getAllStations();
    }
}
