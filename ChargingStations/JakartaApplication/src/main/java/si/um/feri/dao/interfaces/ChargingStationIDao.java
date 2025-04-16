package si.um.feri.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.vao.ChargingStationVao;
import java.util.List;
import java.util.Optional;

@Local
public interface ChargingStationIDao {
    //  create
    void addChargingStation(ChargingStationVao chargingStation);

    //  read
    List<ChargingStationVao> getChargingStations();
    Optional<ChargingStationVao> getChargingStationByLocation(String location);

    //  update
    void updateChargingStation(ChargingStationVao chargingStationVAO);

    //  delete
    void deleteChargingStation(ChargingStationVao station);
}
