package si.um.feri.iterators;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChargingStationsAllOrderedIterator implements Iterator<ChargingStationVao> {
    private final Iterator<ChargingStationVao> iterator;

    public ChargingStationsAllOrderedIterator(List<ProviderVao> providers) {
        List<ChargingStationVao> allStations = new ArrayList<>();

        for (ProviderVao provider : providers) {
            allStations.addAll(provider.getListOfStations());
        }

        allStations.sort(Comparator.comparing(ChargingStationVao::getLocation));

        this.iterator = allStations.iterator();
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
