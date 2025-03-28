package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public class observer_ProviderStationStatus_Notifier implements observer_Station {
    @Override
    public void update(vao_Provider provider, vao_Station station, String action) {
        if ("charging".equals(action)) {
            System.out.println("🏢 Provider notified: Station " + station.getLocation() + " by provider: " + provider.getProviderName() + " is now occupied.");
        } else if ("stopped".equals(action)) {
            System.out.println("🏢 Provider notified: Station " + station.getLocation() + " by provider: " + provider.getProviderName() + " is now available.");
        }
    }
}
