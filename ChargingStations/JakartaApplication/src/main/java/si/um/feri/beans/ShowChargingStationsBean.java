package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.vao.ChargingStationVao;

import java.io.Serializable;
import java.util.List;

@Named("showChargingStations")
@ViewScoped
public class ShowChargingStationsBean implements Serializable {
    public ShowChargingStationsBean() {
        selectedStation = new ChargingStationVao();
    }

    @EJB
    private ChargingStationIService stationService;

    private String selectedLocation;
    private ChargingStationVao selectedStation;

    public String getSelectedLocation() {
        return selectedLocation;
    }
    public void setSelectedLocation(String selectedLocation) {
        this.selectedLocation = selectedLocation;
    }
    public ChargingStationVao getSelectedStation() {
        return selectedStation;
    }
    public void setSelectedStation(ChargingStationVao selectedStation) {
        this.selectedStation = selectedStation;
    }
    public void setSelectedStation(String selectedLocation) {
        this.selectedStation = stationService.getChargingStationByLocation(selectedLocation).orElse(null);
    }

    //  CRUD
    public List<ChargingStationVao> getStations() {
        return stationService.getChargingStations();
    }

    public void updateStation() {
        if (selectedStation == null || selectedStation.getLocation() == null || selectedStation.getLocation().isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Station", "Station location is required."));
            return;
        }

        stationService.updateChargingStation(selectedStation);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Station Updated"));
        PrimeFaces.current().executeScript("PF('manageStationDialog').hide()");
        PrimeFaces.current().ajax().update("mainForm:datatable-stations");
    }

    public void removeStation() {
        stationService.deleteChargingStation(selectedLocation);
    }
}


