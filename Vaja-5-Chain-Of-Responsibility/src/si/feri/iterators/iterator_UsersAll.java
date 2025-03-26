package si.feri.iterators;

import si.feri.vao.vao_User;
import java.util.Iterator;
import java.util.List;

public class iterator_UsersAll implements Iterator<vao_User> {
    private final Iterator<vao_User> iterator;

    public iterator_UsersAll(List<vao_User> users) {
        this.iterator = users.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public vao_User next() {
        return iterator.next();
    }

}