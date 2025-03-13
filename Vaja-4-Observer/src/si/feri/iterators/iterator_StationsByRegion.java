package si.feri.iterators;

import si.feri.enums.enum_Region;
import si.feri.vao.vao_Station;
import java.util.Iterator;
import java.util.List;

public class iterator_StationsByRegion implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    private vao_Station nextStation;
    private final enum_Region region;

    public iterator_StationsByRegion(List<vao_Station> stations, enum_Region region) {
        this.iterator = stations.iterator();
        this.region = region;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            vao_Station potentialStation = iterator.next();
            if (potentialStation.getProvider().getActiveRegion().equals(region)) {
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
