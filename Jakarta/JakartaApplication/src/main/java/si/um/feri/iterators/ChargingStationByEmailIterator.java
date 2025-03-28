package si.um.feri.iterators;

import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationByEmailIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;

    private ChargingStationVao nextStation;
    private final String currentUserEmail;

    public ChargingStationByEmailIterator(List<ChargingStationVao> stations, String currentUserEmail) {
        this.iterator = stations.iterator();
        this.currentUserEmail = currentUserEmail;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            ChargingStationVao potentialStation = iterator.next();
            if (potentialStation.getCurrentUserEmail().equals(currentUserEmail)) {
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
