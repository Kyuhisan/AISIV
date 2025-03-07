package si.feri.dao;

import si.feri.dao.Interfaces.ChargingStation_DAO_Interface;
import si.feri.vao_model.ChargingStation_VAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChargingStation_DAO implements ChargingStation_DAO_Interface {
    private List<ChargingStation_VAO> chargingStations = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void createChargingStation(ChargingStation_VAO chargingStation) {
        chargingStations.add(chargingStation);
    }

    @Override
    public List<ChargingStation_VAO> getChargingStations() {
        return chargingStations;
    }

    @Override
    public Optional<ChargingStation_VAO> getChargingStationByLocation(String location) {
        return chargingStations.stream().filter(chargingStation -> chargingStation.getLocation().equals(location)).findFirst();
    }

    @Override
    public void updateChargingStation(ChargingStation_VAO chargingStationVAO) {
        for (int i = 0; i < chargingStations.size(); i++) {
            if (chargingStations.get(i).getLocation().equals(chargingStationVAO.getLocation())) {
                chargingStations.set(i, chargingStationVAO);
                return;
            }
        }
    }


    @Override
    public void deleteChargingStation(String location) {
        chargingStations.removeIf(chargingStation -> chargingStation.getLocation().equals(location));
    }
}
