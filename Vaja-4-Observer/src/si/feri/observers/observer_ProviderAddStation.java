package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public class observer_ProviderAddStation implements observer_Provider {
    @Override
    public void update(vao_Provider provider, vao_Station station, String action) {
        if ("added".equals(action)) {
            System.out.println("Notification: " + provider.getProviderName() + " added a new station: " + station.getLocation() + ".");
        }
    }
}
