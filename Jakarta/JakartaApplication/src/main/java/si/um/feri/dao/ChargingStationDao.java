package si.um.feri.dao;

import si.um.feri.dao.interfaces.ChargingStationIDao;
import si.um.feri.vao.ChargingStationVao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChargingStationDao implements ChargingStationIDao {
    private final List<ChargingStationVao> listOfStations = Collections.synchronizedList(new ArrayList<>());
    private static volatile ChargingStationDao instance;

    private ChargingStationDao() {}

    public static ChargingStationDao getInstance() {
        if (instance == null) {
            synchronized (ChargingStationDao.class) {
                if (instance == null) {
                    instance = new ChargingStationDao();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addChargingStation(ChargingStationVao station) {
        synchronized (listOfStations) {
            listOfStations.add(station);
        }
    }

    //  read
    @Override
    public List<ChargingStationVao> getChargingStations() {
        synchronized (listOfStations) {
            return listOfStations;
        }
    }
    @Override
    public Optional<ChargingStationVao> getChargingStationByLocation(String location) {
        synchronized (listOfStations) {
            return listOfStations.stream().filter(station -> station.getLocation().equals(location)).findFirst();
        }
    }

    //  update
    @Override
    public void updateChargingStation(ChargingStationVao station) {
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
