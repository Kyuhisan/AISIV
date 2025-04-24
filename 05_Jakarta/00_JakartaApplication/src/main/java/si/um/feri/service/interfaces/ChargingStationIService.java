package si.um.feri.service.interfaces;

import si.um.feri.vao.ChargingStationVao;

import java.util.List;
import java.util.Optional;

public interface ChargingStationIService {
    void addChargingStation(ChargingStationVao station);

    List<ChargingStationVao> getChargingStations();

    Optional<ChargingStationVao> getChargingStationByLocation(String location);

    void updateChargingStation(ChargingStationVao station);

    void deleteChargingStation(String location);
}
