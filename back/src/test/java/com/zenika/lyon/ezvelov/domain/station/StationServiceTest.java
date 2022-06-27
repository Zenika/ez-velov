package com.zenika.lyon.ezvelov.domain.station;

import com.zenika.lyon.ezvelov.domain.station.position.Position;
import com.zenika.lyon.ezvelov.domain.station.totalStands.TotalStands;
import com.zenika.lyon.ezvelov.domain.station.totalStands.availabilities.Availabilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {

    @InjectMocks
    public StationService stationService;

    @Mock
    public IRequestStationStore iRequestStationStore;

    @Test
    public void getStationLaplusProcheShouldReturnTheClosestStation() {
        //given
        Station expectedStation = new Station(2010, new Position(4.815747, 45.743317),
                new TotalStands(22, new Availabilities(15)));
        Position givenPosition = new Position(4.825747, 45.753317);
        when(iRequestStationStore.getAllStations()).thenReturn(List.of(
                expectedStation,
                new Station(32001, new Position(4.832409, 45.846034),
                        new TotalStands(20, new Availabilities(8)))
        ));

        //when
        Station returnStation = stationService.getStationLaPlusProche(givenPosition);

        //then
        assertThat(returnStation).isEqualTo(expectedStation);
    }
}
