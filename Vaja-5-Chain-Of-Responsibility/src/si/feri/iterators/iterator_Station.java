package si.feri.iterators;

import si.feri.vao.vao_Station;
import java.util.Iterator;
import java.util.List;

//  Iterates through all stations
public class iterator_Station implements Iterator<vao_Station> {
    private final Iterator<vao_Station> iterator;

    public iterator_Station(List<vao_Station> stations) {
        this.iterator = stations.iterator();
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
