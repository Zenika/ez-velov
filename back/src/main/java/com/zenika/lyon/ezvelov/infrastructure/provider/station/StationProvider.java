package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
public class StationProvider {

    private final RestTemplate restTemplate;

    public StationProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllStationsByContract(String contract) {
        return restTemplate.getForObject(
                "https://api.jcdecaux.com/vls/v3/stations?contract="
                        + contract
                        + "&apiKey=20e619f2897e7fe10ea90a724e30dfd152ad4a59",
                String.class);
    }
}
