package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public class observer_StationsAvailabilityStatus_Notifier implements observer_Station {
    @Override
    public void update(vao_Provider provider, vao_Station station, String action) {
        if (station.isAvailable()) {
            System.out.println("    ✅ Free charging stations: " + station.getLocation());
        } else {
            System.out.println("    ⛔ Occupied charging stations: " + station.getLocation());
        }
    }
}
