package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("!test")
public class StationProvider implements IRequestStationStore {

    private final RestTemplate restTemplate;

    public StationProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String getAllStations() {
        return restTemplate.getForObject(
                "https://api.jcdecaux.com/vls/v3/stations?contract=lyon" +
                        "&apiKey=20e619f2897e7fe10ea90a724e30dfd152ad4a59",
                String.class);
    }
}
