package com.zenika.lyon.ezvelov.infrastructure.provider.station;

import com.zenika.lyon.ezvelov.domain.station.IRequestStationStore;
import org.springframework.stereotype.Component;

@Component
public class StationProviderMock implements IRequestStationStore {

    @Override
    public String getAllStations() {
        return """
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
                """;
    }
}
