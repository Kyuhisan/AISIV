package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import si.um.feri.dao.interfaces.ChargingStationIDao;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Stateless
public class ChargingStationService implements Serializable, ChargingStationIService {
    @EJB
    private ChargingStationIDao dao_station;

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
        if (station == null || station.getLocation() == null || station.getLocation().isBlank()) {
            throw new IllegalArgumentException("❌ Invalid station or missing location!");
        }

        if (dao_station.getChargingStationByLocation(station.getLocation()).isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        } else {
            System.out.println("✅ Station updated!");
            dao_station.updateChargingStation(station);
        }
    }

    //  delete
    @Transactional
    public void deleteChargingStation(String location) {
        Optional<ChargingStationVao> stationOpt = dao_station.getChargingStationByLocation(location);

        if (stationOpt.isEmpty()) {
            throw new IllegalArgumentException("❌ Station not found!");
        }

        ChargingStationVao station = stationOpt.get();

        // Unlink the provider *inside an active transaction/session*
        if (station.getProvider() != null) {
            ProviderVao provider = station.getProvider();
            if (provider.getListOfStations().contains(station)) {
                provider.getListOfStations().remove(station); // now this won't throw LazyInitializationException
            }
            station.setProvider(null);
        }

        dao_station.deleteChargingStation(station);
        System.out.println("✅ Station deleted!");
    }
}
