package si.feri.iterators;

import si.feri.vao.vao_Station;
import si.feri.vao.vao_User;

import java.util.Iterator;
import java.util.List;

public class iterator_UsersWithBalance implements Iterator<vao_User> {
    private final Iterator<vao_User> iterator;
    private vao_User nextUser;
    private final double balance = 0;

    public iterator_UsersWithBalance(List<vao_User> users) {
        this.iterator = users.iterator();
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            vao_User potentialUser = iterator.next();
            if (potentialUser.getBalance() > 0) {
                nextUser = potentialUser;
                return;
            }
        }
        nextUser = null;
    }

    @Override
    public boolean hasNext() {
        return nextUser != null;
    }

    @Override
    public vao_User next() {
        vao_User currentUser = nextUser;
        continueSearching();
        return currentUser;
    }
}
