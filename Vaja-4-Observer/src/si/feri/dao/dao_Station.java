package si.feri.dao;

import si.feri.dao.Interfaces.i_dao_Station;
import si.feri.vao.vao_Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class dao_Station implements i_dao_Station {
    private final List<vao_Station> listOfStations = Collections.synchronizedList(new ArrayList<>());
    private static volatile dao_Station instance;

    private dao_Station() {}

    public static dao_Station getInstance() {
        if (instance == null) {
            synchronized (dao_Station.class) {
                if (instance == null) {
                    instance = new dao_Station();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addChargingStation(vao_Station station) {
        synchronized (listOfStations) {
            listOfStations.add(station);
        }
    }

    //  read
    @Override
    public List<vao_Station> getChargingStations() {
        synchronized (listOfStations) {
            return listOfStations;
        }
    }
    @Override
    public Optional<vao_Station> getChargingStationByLocation(String location) {
        synchronized (listOfStations) {
            return listOfStations.stream().filter(station -> station.getLocation().equals(location)).findFirst();
        }
    }

    //  update
    @Override
    public void updateChargingStation(vao_Station station) {
        synchronized (listOfStations) {
            getChargingStationByLocation(station.getLocation()).ifPresentOrElse(
                    stationToUpdate -> listOfStations.set(listOfStations.indexOf(stationToUpdate), station),
                    () -> System.out.println("❌ Station not found!")
            );
        }
    }

    //  delete
    @Override
    public void deleteChargingStation(String location) {
        synchronized (listOfStations) {
            listOfStations.removeIf(station -> station.getLocation().equals(location));
        }
    }
}
