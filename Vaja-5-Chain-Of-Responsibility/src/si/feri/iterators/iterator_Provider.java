package si.feri.iterators;

import si.feri.vao.vao_Provider;
import java.util.Iterator;
import java.util.List;

//
public class iterator_Provider implements Iterator<vao_Provider> {
    private final Iterator<vao_Provider> iterator;

    public iterator_Provider(List<vao_Provider> providers) {
        this.iterator = providers.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public vao_Provider next() {
        return iterator.next();
    }
}
