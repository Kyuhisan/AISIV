package si.um.feri.dao.interfaces;

import si.um.feri.vao.ChargingStationVao;
import java.util.List;
import java.util.Optional;

public interface ChargingStationIDao {
    //  create
    void addChargingStation(ChargingStationVao chargingStation);

    //  read
    List<ChargingStationVao> getChargingStations();
    Optional<ChargingStationVao> getChargingStationByLocation(String location);

    //  update
    void updateChargingStation(ChargingStationVao chargingStationVAO);

    //  delete
    void deleteChargingStation(String location);
}
