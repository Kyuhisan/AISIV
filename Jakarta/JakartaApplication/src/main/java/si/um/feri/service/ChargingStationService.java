package si.um.feri.service;

import si.um.feri.dao.ChargingStationDao;
import si.um.feri.dao.interfaces.ChargingStationIDao;
import si.um.feri.vao.ChargingStationVao;
import java.util.List;
import java.util.Optional;

public class ChargingStationService {
    private final ChargingStationIDao dao_station = ChargingStationDao.getInstance();

    //  create
    public void addChargingStation(ChargingStationVao station) {
        if (dao_station.getChargingStationByLocation(station.getLocation()).isPresent()) {
            throw new IllegalArgumentException("❌ Station already exists!");
        } else {
            System.out.println("✅ Station added!");
            dao_station.addChargingStation(station);
        }
    }

    //  read
    public List<ChargingStationVao> getChargingStations() {
        if (dao_station.getChargingStations().isEmpty()) {
            throw new IllegalArgumentException("❌ No stations found!");
        } else {
            System.out.println("✅ Stations found!");
            return dao_station.getChargingStations();
        }
    }
    public Optional<ChargingStationVao> getChargingStationByLocation(String location) {
        if (dao_station.getChargingStationByLocation(location).isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        } else {
            System.out.println("✅ Station found!");
            return dao_station.getChargingStationByLocation(location);
        }
    }

    //  update
    public void updateChargingStation(ChargingStationVao station) {
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
        Optional<ChargingStationVao> station = dao_station.getChargingStationByLocation(location);

        if (station.isPresent()) {
            dao_station.deleteChargingStation(location);
            System.out.println("✅ Station deleted!");
        } else {
            throw new IllegalArgumentException("❌ Station not found!");
        }
    }
}
