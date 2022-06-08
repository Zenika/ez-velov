package com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands;

import com.zenika.lyon.ezvelov.domain.station.totalStands.TotalStands;
import com.zenika.lyon.ezvelov.infrastructure.repository.station.totalStands.availabilities.AvailabilitiesEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class TotalStandsEntityMapper {

    private final AvailabilitiesEntityMapper availabilitiesEntityMapper;

    public TotalStandsEntityMapper(AvailabilitiesEntityMapper availabilitiesEntityMapper) {
        this.availabilitiesEntityMapper = availabilitiesEntityMapper;
    }

    public TotalStands totalStandsEntityToTotalStands(TotalStandsEntity totalStandsEntity) {
        return new TotalStands(totalStandsEntity.getCapacity(),
                availabilitiesEntityMapper.availabilitiesEntityToAvailabilities(totalStandsEntity.getAvailabilities()));
    }

    public TotalStandsEntity totalStandsToTotalStandsEntity(TotalStands totalStands) {
        return new TotalStandsEntity(totalStands.capacity(),
                availabilitiesEntityMapper.availabilitiesToAvailabilitiesEntity(totalStands.availabilities()));
    }
}
