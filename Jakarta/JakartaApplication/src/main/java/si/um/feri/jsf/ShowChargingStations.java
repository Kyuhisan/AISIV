package si.um.feri.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ChargingStationService;
import si.um.feri.vao.ChargingStationVao;
import java.util.List;

@Named("showChargingStations")
@RequestScoped
public class ShowChargingStations {
    @Inject
    ChargingStationService stationService;

    public List<ChargingStationVao> getStations() {
        return stationService.getChargingStations();
    }
}


