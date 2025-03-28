package si.feri.iterators;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;
import si.feri.vao.vao_User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class iterator_UsersAllOrdered implements Iterator<vao_User> {
    private final Iterator<vao_User> iterator;

    public iterator_UsersAllOrdered(List<vao_User> users) {
        users.sort(Comparator.comparing(vao_User::getEmail));
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
