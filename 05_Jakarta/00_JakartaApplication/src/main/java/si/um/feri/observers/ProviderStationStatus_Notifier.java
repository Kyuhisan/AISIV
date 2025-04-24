package si.um.feri.observers;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;

public class ProviderStationStatus_Notifier implements ChargingStationObserver {
    @Override
    public void update(ProviderVao provider, ChargingStationVao station, String action) {
        if ("charging".equals(action)) {
            System.out.println("🏢 Provider notified: Station " + station.getLocation() + " by provider: " + provider.getProviderName() + " is now occupied.");
        } else if ("stopped".equals(action)) {
            System.out.println("🏢 Provider notified: Station " + station.getLocation() + " by provider: " + provider.getProviderName() + " is now available.");
        }
    }
}
