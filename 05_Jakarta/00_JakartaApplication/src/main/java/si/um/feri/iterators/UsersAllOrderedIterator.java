package si.um.feri.iterators;

import si.um.feri.vao.UserVao;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UsersAllOrderedIterator implements Iterator<UserVao> {
    private final Iterator<UserVao> iterator;

    public UsersAllOrderedIterator(List<UserVao> users) {
        users.sort(Comparator.comparing(UserVao::getEmail));
        this.iterator = users.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public UserVao next() {
        return iterator.next();
    }
}
