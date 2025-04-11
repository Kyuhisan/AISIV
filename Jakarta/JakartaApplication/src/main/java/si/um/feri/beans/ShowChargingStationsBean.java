package si.um.feri.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ChargingStationService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

import java.util.List;
import java.util.Optional;

@Named("showChargingStations")
@RequestScoped
public class ShowChargingStationsBean {
    @Inject
    ChargingStationService stationService;
    private String selectedLocation;

    public String getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    //  CRUD
    public List<ChargingStationVao> getStations() {
        return stationService.getChargingStations();
    }

    public void updateStation() {
        Optional<ChargingStationVao> selectedStation = stationService.getChargingStationByLocation(selectedLocation);

        if (selectedStation.isPresent()) {
            ChargingStationVao station = selectedStation.get();
            stationService.updateChargingStation(station);
        } else {
            System.out.println("Station not found with email: " + selectedStation);
        }
    }

    public void removeStation() {
        stationService.deleteChargingStation(selectedLocation);
    }
}


