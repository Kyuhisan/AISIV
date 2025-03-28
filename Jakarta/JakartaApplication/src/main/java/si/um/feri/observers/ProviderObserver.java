package si.um.feri.observers;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;

public interface ProviderObserver {
    void update(ProviderVao provider, ChargingStationVao station, String action);
}
