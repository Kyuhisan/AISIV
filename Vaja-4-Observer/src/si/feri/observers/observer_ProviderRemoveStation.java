package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public class observer_ProviderRemoveStation implements observer_Provider {
    @Override
    public void update(vao_Provider provider, vao_Station station, String action) {
        if ("removed".equals(action)) {
            System.out.println("Notification: " + provider.getProviderName() + " removed: " + station.getLocation() + " station.");
        }
    }
}
