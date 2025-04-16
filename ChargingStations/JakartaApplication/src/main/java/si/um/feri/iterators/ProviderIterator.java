package si.um.feri.iterators;

import si.um.feri.vao.ProviderVao;
import java.util.Iterator;
import java.util.List;

public class ProviderIterator implements Iterator<ProviderVao> {
    private final Iterator<ProviderVao> iterator;

    public ProviderIterator(List<ProviderVao> providers) {
        this.iterator = providers.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ProviderVao next() {
        return iterator.next();
    }
}
