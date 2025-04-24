package si.feri.iterators;

import si.feri.observers.observer_StationsAvailabilityStatus_Notifier;
import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Iterates through all providers and it's stations
public class iterator_StationsAll implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    public iterator_StationsAll(List<vao_Provider> providers) {
        List<vao_Station> allStations = new ArrayList<>();

        for (vao_Provider provider : providers ) {
            allStations.addAll(provider.getListOfStations());
        }

        this.iterator = allStations.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public vao_Station next() {
        return iterator.next();
    }

}
