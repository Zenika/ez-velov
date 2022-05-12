package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StationProviderTest {

    @Test
    public void parser_should_deserialize_one_station() {
        //Given
        Gson gson = new Gson();
        String givenJsonStation = """
                {
                       "number":2010,
                       "contractName":"lyon",
                       "name":"2010 - CONFLUENCE / DARSE",
                       "address":"ANGLE ALLEE ANDRE MURE ET QUAI ANTOINE RIBOUD",
                       "position":{
                          "latitude":45.743317,
                          "longitude":4.815747
                       },
                       "banking":true,
                       "bonus":false,
                       "status":"OPEN",
                       "lastUpdate":"2022-04-25T12:57:59Z",
                       "connected":true,
                       "overflow":false,
                       "shape":null,
                       "totalStands":{
                          "availabilities":{
                             "bikes":6,
                             "stands":15,
                             "mechanicalBikes":3,
                             "electricalBikes":3,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":3
                          },
                          "capacity":22
                       },
                       "mainStands":{
                          "availabilities":{
                             "bikes":6,
                             "stands":15,
                             "mechanicalBikes":3,
                             "electricalBikes":3,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":3
                          },
                          "capacity":22
                       },
                       "overflowStands":null
                    }
                """;
        Station expectedStation = new Station(new Position(4.815747, 45.743317));

        //When
        Station station = gson.fromJson(givenJsonStation, Station.class);

        //Then
        assertThat(station).isEqualTo(expectedStation);
    }

    @Test
    public void parser_should_deserialize_multiple_stations() {
        //Given
        Gson gson = new Gson();
        String givenJsonStation =
                 """
                 [
                    {
                       "number":2010,
                       "contractName":"lyon",
                       "name":"2010 - CONFLUENCE / DARSE",
                       "address":"ANGLE ALLEE ANDRE MURE ET QUAI ANTOINE RIBOUD",
                       "position":{
                          "latitude":45.743317,
                          "longitude":4.815747
                       },
                       "banking":true,
                       "bonus":false,
                       "status":"OPEN",
                       "lastUpdate":"2022-04-25T12:57:59Z",
                       "connected":true,
                       "overflow":false,
                       "shape":null,
                       "totalStands":{
                          "availabilities":{
                             "bikes":6,
                             "stands":15,
                             "mechanicalBikes":3,
                             "electricalBikes":3,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":3
                          },
                          "capacity":22
                       },
                       "mainStands":{
                          "availabilities":{
                             "bikes":6,
                             "stands":15,
                             "mechanicalBikes":3,
                             "electricalBikes":3,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":3
                          },
                          "capacity":22
                       },
                       "overflowStands":null
                    },
                    {
                       "number":5015,
                       "contractName":"lyon",
                       "name":"5015 - FULCHIRON",
                       "address":"Devant le n°41 rue de la Quarantaine",
                       "position":{
                          "latitude":45.75197,
                          "longitude":4.821662
                       },
                       "banking":true,
                       "bonus":false,
                       "status":"OPEN",
                       "lastUpdate":"2022-04-25T13:03:29Z",
                       "connected":true,
                       "overflow":false,
                       "shape":null,
                       "totalStands":{
                          "availabilities":{
                             "bikes":7,
                             "stands":12,
                             "mechanicalBikes":6,
                             "electricalBikes":1,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":1
                          },
                          "capacity":19
                       },
                       "mainStands":{
                          "availabilities":{
                             "bikes":7,
                             "stands":12,
                             "mechanicalBikes":6,
                             "electricalBikes":1,
                             "electricalInternalBatteryBikes":0,
                             "electricalRemovableBatteryBikes":1
                          },
                          "capacity":19
                       },
                       "overflowStands":null
                    }
                ]
                """;

        List<Station> expectedListStation = new ArrayList<>();
        expectedListStation.add(new Station(new Position(4.815747, 45.743317)));
        expectedListStation.add(new Station(new Position(4.821662,45.75197)));


        //When
        Type stationListType = new TypeToken<ArrayList<Station>>(){}.getType();
        List<Station> station = gson.fromJson(givenJsonStation, stationListType);

        //Then
        assertThat(station).isEqualTo(expectedListStation);
    }
}

class Station {
    public Position position;

    public Station(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(position, station.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Station{" +
                "position=" + position +
                '}';
    }
}

class Position{
    public double longitude;

    public double latitude;

    public Position(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.longitude, longitude) == 0 && Double.compare(position.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
