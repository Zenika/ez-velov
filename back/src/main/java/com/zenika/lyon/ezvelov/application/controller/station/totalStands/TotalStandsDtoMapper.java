package com.zenika.lyon.ezvelov.application.controller.station.totalStands;

import com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities.AvailabilitiesDtoMapper;
import com.zenika.lyon.ezvelov.domain.station.totalStands.TotalStands;
import org.springframework.stereotype.Component;

@Component
public class TotalStandsDtoMapper {

    private final AvailabilitiesDtoMapper availabilitiesDtoMapper;

    public TotalStandsDtoMapper(AvailabilitiesDtoMapper availabilitiesDtoMapper) {
        this.availabilitiesDtoMapper = availabilitiesDtoMapper;
    }

    public TotalStandsDto totalStandsToTotalStandsDto(TotalStands totalStands) {
        return new TotalStandsDto(totalStands.capacity(),
                availabilitiesDtoMapper.availabilitiesToAvailabilitiesDto(totalStands.availabilities()));
    }

    public TotalStands totalStandsDtoToTotalStands(TotalStandsDto totalStandsDto) {
        return new TotalStands(totalStandsDto.capacity(),
                availabilitiesDtoMapper.availabilitiesDtoToAvaibilities(totalStandsDto.availabilitiesDto()));
    }
}
