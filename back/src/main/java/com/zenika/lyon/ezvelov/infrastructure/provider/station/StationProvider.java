package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.google.gson.Gson;
import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import com.zenika.lyon.ezvelov.domain.station.Station;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.StationEntity;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.StationEntityMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Profile("!test")
public class StationProvider implements IRequestStationStore {

    private final RestTemplate restTemplate;

    private final StationEntityMapper stationEntityMapper;

    @Value("${jcdecaux.token}")
    private String jcDecauxToken;

    public StationProvider(RestTemplate restTemplate, StationEntityMapper stationEntityMapper) {
        this.restTemplate = restTemplate;
        this.stationEntityMapper = stationEntityMapper;
    }

    @Override
    public List<Station> getAllStations() {
        String jsonStations = restTemplate.getForObject(
                String.format("https://api.jcdecaux.com/vls/v3/stations?contract=lyon&apiKey=%s", jcDecauxToken),
                String.class);

        List<StationEntity> listStationsEntity = List.of(new Gson().fromJson(jsonStations, StationEntity[].class));
        return listStationsEntity.stream()
                .map(stationEntityMapper::stationEntitytoStation)
                .toList();
    }
}
