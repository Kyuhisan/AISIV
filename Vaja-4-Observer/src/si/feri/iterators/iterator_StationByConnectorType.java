package si.feri.iterators;

import si.feri.enums.enum_Connector;
import si.feri.vao.vao_Station;

import java.util.Iterator;
import java.util.List;

//  Iterates through all stations based on Connector Type
public class iterator_StationByConnectorType implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    private vao_Station nextStation;
    private final enum_Connector connector;

    public iterator_StationByConnectorType(List<vao_Station> stations, enum_Connector connector) {
        this.iterator = stations.iterator();
        this.connector = connector;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            vao_Station potentialStation = iterator.next();
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
    public vao_Station next() {
        vao_Station currentStation = nextStation;
        continueSearching();
        return currentStation;
    }
}
