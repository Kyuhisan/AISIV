package si.feri.dao.Interfaces;

import si.feri.vao_model.ChargingStation_VAO;

import java.util.List;
import java.util.Optional;

public interface ChargingStation_DAO_Interface {
    //create
    void createChargingStation(ChargingStation_VAO chargingStation);
    //read
    List<ChargingStation_VAO> getChargingStations();
    Optional<ChargingStation_VAO> getChargingStationByLocation(String location);
    //update
    void updateChargingStation(ChargingStation_VAO chargingStationVAO);
    //delete
    void deleteChargingStation(String location);
}