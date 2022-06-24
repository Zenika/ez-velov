package com.zenika.lyon.ezvelov.domain.station;

import com.zenika.lyon.ezvelov.domain.station.position.Position;
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

    public Station getStationLaPlusProche(Position position) {
        Station stationLaPlusProche = null;
        double longFinal = 0, latFinal = 0, lowerDistance = 100;
        List<Station> listStation = iRequestStationStore.getAllStations();
        for (Station station : listStation) {
            double distanceBeetweenStationAndUser = Math.sqrt(Math.pow(station.position().longitude() - position.longitude(), 2)
                    + Math.pow(station.position().latitude() - position.latitude(), 2));
            if (distanceBeetweenStationAndUser < lowerDistance) {
                lowerDistance = distanceBeetweenStationAndUser;
                longFinal = station.position().longitude();
                latFinal = station.position().longitude();
            }
        }
        for (Station station : listStation) {
            if (station.position().longitude() == longFinal && station.position().latitude() == latFinal) {
                stationLaPlusProche = station;
            }
        }
        return stationLaPlusProche;
    }
}
