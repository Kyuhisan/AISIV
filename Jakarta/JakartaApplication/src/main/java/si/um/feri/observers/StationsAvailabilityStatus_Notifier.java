package si.um.feri.observers;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;

public class StationsAvailabilityStatus_Notifier implements ChargingStationObserver {
    @Override
    public void update(ProviderVao provider, ChargingStationVao station, String action) {
        if (station.isAvailable()) {
            System.out.println("    ✅ Free charging stations: " + station.getLocation());
        } else {
            System.out.println("    ⛔ Occupied charging stations: " + station.getLocation());
        }
    }
}
