package com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities;

import com.zenika.lyon.ezvelov.domain.station.totalStands.availabilities.Availabilities;
import org.springframework.stereotype.Component;

@Component
public class AvailabilitiesDtoMapper {

    public AvailabilitiesDto availabilitiesToAvailabilitiesDto(Availabilities availabilities) {
        return new AvailabilitiesDto(availabilities.stands());
    }

    public Availabilities availabilitiesDtoToAvaibilities(AvailabilitiesDto availabilitiesDto) {
        return new Availabilities(availabilitiesDto.stands());
    }
}
