package com.zenika.lyon.ezvelov.domain.station;

import org.springframework.stereotype.Service;

@Service
public class StationService {

    private final IRequestStationStore iRequestStationStore;

    public StationService(IRequestStationStore iRequestStationStore) {
        this.iRequestStationStore = iRequestStationStore;
    }

    public String getAllStations() {
        return iRequestStationStore.getAllStations();
    }
}
