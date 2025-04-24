package si.um.feri.iterators;

import si.um.feri.vao.ChargingStationVao;
import java.util.Iterator;
import java.util.List;

public class ChargingStationIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;

    public ChargingStationIterator(List<ChargingStationVao> stations) {
        this.iterator = stations.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ChargingStationVao next() {
        return iterator.next();
    }
}
