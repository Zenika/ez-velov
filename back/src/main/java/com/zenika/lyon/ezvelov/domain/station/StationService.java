package com.zenika.lyon.ezvelov.domain.station;

import com.zenika.lyon.ezvelov.domain.station.position.Position;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        return iRequestStationStore.getAllStations()
                .stream()
                .min(Comparator.comparing(station -> this.calculDistance(station.position(), position)))
                .orElseThrow(() -> new NullPointerException("La Liste de station ne doit pas être vide"));
    }

    private double calculDistance(Position position1, Position position2) {
        return Math.sqrt(
                Math.pow(position1.longitude() - position2.longitude(), 2)
                + Math.pow(position1.latitude() - position2.latitude(), 2)
        );
    }
}
