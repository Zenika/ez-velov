package com.zenika.lyon.ezvelov.application.controller.station;

import com.zenika.lyon.ezvelov.EzVelovIT;
import com.zenika.lyon.ezvelov.application.controller.station.position.PositionDto;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.TotalStandsDto;
import com.zenika.lyon.ezvelov.application.controller.station.totalStands.availabilities.AvailabilitiesDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StationControllerIT extends EzVelovIT {

    @Test
    public void getAllStations_should_return_array_of_stations() {
        //given
        List<StationDto> expectedListStation = List.of(
                new StationDto(new PositionDto(4.815747, 45.743317),
                        new TotalStandsDto(20, new AvailabilitiesDto(8))),
                new StationDto(new PositionDto(4.821662, 45.75197),
                        new TotalStandsDto(20, new AvailabilitiesDto(8))),
                new StationDto(new PositionDto(4.832409, 45.846034),
                        new TotalStandsDto(20, new AvailabilitiesDto(8)))
        );

        //when
        StationDto[] tabResult = restTemplate.getForObject(this.uri + "/station", StationDto[].class);
        List<StationDto> result = Arrays.asList(tabResult);

        //then
        assertThat(result).isEqualTo(expectedListStation);
    }
}
