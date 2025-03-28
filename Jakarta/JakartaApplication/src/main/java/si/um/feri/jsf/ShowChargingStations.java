package si.um.feri.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ChargingStationService;

@Named("showChargingStations")
@RequestScoped
public class ShowChargingStations {
    @Inject
    private ChargingStationService stationService;

    public void printToConsole() {
        stationService.getChargingStations().forEach(System.out::println);
    }
}

