package com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.availabilities;

import com.zenika.lyon.ezvelov.domain.station.totalStands.availabilities.Availabilities;
import org.springframework.stereotype.Component;

@Component
public class AvailabilitiesEntityMapper {

    public Availabilities availabilitiesEntityToAvailabilities(AvailabilitiesEntity availabilitiesEntity) {
        return new Availabilities(availabilitiesEntity.getStands());
    }

    public AvailabilitiesEntity availabilitiesToAvailabilitiesEntity(Availabilities availabilities) {
        return new AvailabilitiesEntity(availabilities.stands());
    }
}
