package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import com.zenika.lyon.ezvelov.domain.station.Station;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("!test")
public class StationProvider implements IRequestStationStore {

    private final RestTemplate restTemplate;

    @Value("${jcdecaux.token}")
    private String jcDecauxToken;

    public StationProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Station> getAllStations() {
        String jsonStations = restTemplate.getForObject(
                String.format("https://api.jcdecaux.com/vls/v3/stations?contract=lyon&apiKey=%s", jcDecauxToken),
                String.class);

        return List.of(new Gson().fromJson(jsonStations, Station[].class));
    }
}
