package si.um.feri.iterators;

import si.um.feri.vao.UserVao;
import java.util.Iterator;
import java.util.List;

public class UsersAllIterator implements Iterator<UserVao> {
    private final Iterator<UserVao> iterator;

    public UsersAllIterator(List<UserVao> users) {
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