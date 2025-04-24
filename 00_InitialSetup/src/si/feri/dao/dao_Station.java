package si.feri.dao;

import si.feri.dao.Interfaces.i_dao_Station;
import si.feri.vao.vao_Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class dao_Station implements i_dao_Station {
    private final List<vao_Station> listOfStations = Collections.synchronizedList(new ArrayList<>());

    //  create
    @Override
    public void addChargingStation(vao_Station station) {
        listOfStations.add(station);
    }

    //  read
    @Override
    public List<vao_Station> getChargingStations() {
        return listOfStations;
    }
    @Override
    public Optional<vao_Station> getChargingStationByLocation(String location) {
        return listOfStations.stream().filter(station -> station.getLocation().equals(location)).findFirst();
    }

    //  update
    @Override
    public void updateChargingStation(vao_Station station) {
        getChargingStationByLocation(station.getLocation()).ifPresentOrElse(
                stationToUpdate -> listOfStations.set(listOfStations.indexOf(stationToUpdate), station),
                () -> System.out.println("❌ Station not found!")
        );
    }

    //  delete
    @Override
    public void deleteChargingStation(String location) {
        listOfStations.removeIf(station -> station.getLocation().equals(location));
    }
}
