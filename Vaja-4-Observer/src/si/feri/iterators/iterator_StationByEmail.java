package si.feri.iterators;

import si.feri.vao.vao_Station;

import java.util.Iterator;
import java.util.List;

public class iterator_StationByEmail implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    private vao_Station nextStation;
    private final String currentUserEmail;

    public iterator_StationByEmail(List<vao_Station> stations, String currentUserEmail) {
        this.iterator = stations.iterator();
        this.currentUserEmail = currentUserEmail;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            vao_Station potentialStation = iterator.next();
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
    public vao_Station next() {
        vao_Station currentStation = nextStation;
        continueSearching();
        return currentStation;
    }
}
