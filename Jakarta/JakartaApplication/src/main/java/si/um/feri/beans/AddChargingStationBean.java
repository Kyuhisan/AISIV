package si.um.feri.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.connectorENUM;
import si.um.feri.service.ChargingStationService;
import si.um.feri.service.ProviderService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;

import java.io.Serializable;
import java.util.List;

@Named("addChargingStation")
@SessionScoped
@Getter
@Setter
public class AddChargingStationBean implements Serializable {
    private ChargingStationVao station = new ChargingStationVao();
    private String selectedProviderName;

    @Inject
    private ChargingStationService stationService;
    @Inject
    private ProviderService providerService;

    public void addChargingStation() {
        ProviderVao selectedProvider = providerService.getProviderByName(selectedProviderName).orElse(null);
        station.setProviderVao(selectedProvider);
        station.setAvailable(false);
        stationService.addChargingStation(station);
        System.out.println("✅ Charging Station added: " + station);
        station = new ChargingStationVao();
        selectedProviderName = null;
    }

    public connectorENUM[] getConnector() {
        return connectorENUM.values();
    }

    public List<ProviderVao> getProviders() {
        return providerService.getProviders();
    }
}
