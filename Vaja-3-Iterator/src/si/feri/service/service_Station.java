package si.feri.service;

import si.feri.dao.Interfaces.i_dao_Station;
import si.feri.dao.dao_Station;
import si.feri.vao.vao_Station;

import java.util.List;
import java.util.Optional;

public class service_Station {
    private final i_dao_Station dao_station = dao_Station.getInstance();

    //  create
    public void addChargingStation(vao_Station station) {
        if (dao_station.getChargingStationByLocation(station.getLocation()).isPresent()) {
            throw new IllegalArgumentException("❌ Station already exists!");
        } else {
            System.out.println("✅ Station added!");
            dao_station.addChargingStation(station);
        }
    }

    //  read
    public List<vao_Station> getChargingStations() {
        if (dao_station.getChargingStations().isEmpty()) {
            throw new IllegalArgumentException("❌ No stations found!");
        } else {
            System.out.println("✅ Stations found!");
            return dao_station.getChargingStations();
        }
    }
    public Optional<vao_Station> getChargingStationByLocation(String location) {
        if (dao_station.getChargingStationByLocation(location).isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        } else {
            System.out.println("✅ Station found!");
            return dao_station.getChargingStationByLocation(location);
        }
    }

    //  update
    public void updateChargingStation(vao_Station station) {
        if (dao_station.getChargingStationByLocation(station.getLocation()).isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        } else {
            System.out.println("✅ Station updated!");
            dao_station.updateChargingStation(station);
        }
    }

    //  delete
    public void deleteChargingStation(String location) {
        if (dao_station.getChargingStationByLocation(location).isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        }
        Optional<vao_Station> station = dao_station.getChargingStationByLocation(location);

        if (station.isPresent()) {
            dao_station.deleteChargingStation(location);
            System.out.println("✅ Station deleted!");
        } else {
            throw new IllegalArgumentException("❌ Station not found!");
        }
    }
}
