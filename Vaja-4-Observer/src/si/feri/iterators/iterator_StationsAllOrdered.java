package si.feri.iterators;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class iterator_StationsAllOrdered implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    public iterator_StationsAllOrdered(List<vao_Provider> providers) {
        List<vao_Station> allStations = new ArrayList<>();

        for (vao_Provider provider : providers) {
            allStations.addAll(provider.getListOfStations());
        }

        allStations.sort(Comparator.comparing(vao_Station::getLocation));

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
