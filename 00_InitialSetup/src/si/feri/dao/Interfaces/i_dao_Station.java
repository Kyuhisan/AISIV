package si.feri.dao.Interfaces;

import si.feri.vao.vao_Station;
import java.util.List;
import java.util.Optional;

public interface i_dao_Station {
    //  create
    void addChargingStation(vao_Station chargingStation);

    //  read
    List<vao_Station> getChargingStations();
    Optional<vao_Station> getChargingStationByLocation(String location);

    //  update
    void updateChargingStation(vao_Station chargingStationVAO);

    //  delete
    void deleteChargingStation(String location);
}
