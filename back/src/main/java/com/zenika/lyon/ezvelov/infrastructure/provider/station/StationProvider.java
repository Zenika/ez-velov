package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
    public String getAllStations() {
        return restTemplate.getForObject(
                String.format("https://api.jcdecaux.com/vls/v3/stations?contract=lyon&apiKey=%s", jcDecauxToken),
                String.class);
    }
}
