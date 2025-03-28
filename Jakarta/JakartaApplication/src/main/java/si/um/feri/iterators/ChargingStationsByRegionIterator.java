package si.um.feri.iterators;

import si.um.feri.enums.regionENUM;
import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationsByRegionIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;

    private ChargingStationVao nextStation;
    private final regionENUM region;

    public ChargingStationsByRegionIterator(List<ChargingStationVao> stations, regionENUM region) {
        this.iterator = stations.iterator();
        this.region = region;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            ChargingStationVao potentialStation = iterator.next();
            if (potentialStation.getProviderVao().getActiveRegion().equals(region)) {
                nextStation = potentialStation;
                return;
            }
        }
        nextStation = null;
    }

    @Override
    public boolean hasNext() {
        return nextStation != null;
    }

    @Override
    public ChargingStationVao next() {
        ChargingStationVao currentStation = nextStation;
        continueSearching();
        return currentStation;
    }
}
