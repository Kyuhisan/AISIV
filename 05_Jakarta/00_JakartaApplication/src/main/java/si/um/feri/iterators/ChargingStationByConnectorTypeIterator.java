package si.um.feri.iterators;

import si.um.feri.enums.connectorENUM;
import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationByConnectorTypeIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;

    private ChargingStationVao nextStation;
    private final connectorENUM connector;

    public ChargingStationByConnectorTypeIterator(List<ChargingStationVao> stations, connectorENUM connector) {
        this.iterator = stations.iterator();
        this.connector = connector;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            ChargingStationVao potentialStation = iterator.next();
            if (potentialStation.getConnector().equals(connector)) {
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
