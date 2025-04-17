package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.vao.ChargingStationVao;
import java.util.List;

@Named("showChargingStations")
@RequestScoped
public class ShowChargingStationsBean {
    @EJB
    private ChargingStationIService stationService;

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

    public void removeStation() {
        stationService.deleteChargingStation(selectedLocation);
    }
}


