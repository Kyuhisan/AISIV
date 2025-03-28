package si.um.feri.iterators;

import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationsBySpeedIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;
    private ChargingStationVao nextStation;
    private final double chargingSpeed;

    public ChargingStationsBySpeedIterator(List<ChargingStationVao> stations, double chargingSpeed) {
        this.iterator = stations.iterator();
        this.chargingSpeed = chargingSpeed;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            ChargingStationVao potentialStation = iterator.next();
            if (potentialStation.getChargingSpeed() >= chargingSpeed) {
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
