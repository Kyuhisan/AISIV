package si.um.feri.iterators;

import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationsActiveIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;
    private ChargingStationVao nextStation;
    private final boolean isAvailable;

    public ChargingStationsActiveIterator(List<ChargingStationVao> stations, boolean isAvailable) {
        this.iterator = stations.iterator();
        this.isAvailable = isAvailable;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            ChargingStationVao potentialStation = iterator.next();
            if (potentialStation.isAvailable() == this.isAvailable) {
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
