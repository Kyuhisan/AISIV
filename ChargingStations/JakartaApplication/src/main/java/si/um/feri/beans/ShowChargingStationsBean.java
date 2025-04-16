package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.vao.ChargingStationVao;
import java.util.List;
import java.util.Optional;

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

    public void onCellEdit(CellEditEvent event) {
        ChargingStationVao edited = (ChargingStationVao) event.getRowData();

        try {
            stationService.updateChargingStation(edited);
            System.out.println("✅ Station updated: " + edited.getLocation());
        } catch (Exception e) {
            System.err.println("❌ Error updating station: " + e.getMessage());
        }
    }
}


