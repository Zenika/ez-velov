package com.zenika.lyon.ezvelov.domain.station;

import com.zenika.lyon.ezvelov.infrastructure.provider.station.StationProvider;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    private IRequestStationStore iRequestStationStore;

    public StationService(IRequestStationStore iRequestStationStore) {
        this.iRequestStationStore = iRequestStationStore;
    }

    public String getAllStations(){
        return iRequestStationStore.getAllStations();
    }
}
