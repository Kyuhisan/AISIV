package si.um.feri.iterators;

import si.um.feri.vao.UserVao;
import java.util.Iterator;
import java.util.List;

public class UsersWithBalanceIterator implements Iterator<UserVao> {
    private final Iterator<UserVao> iterator;
    private UserVao nextUser;
    private final double balance = 0;

    public UsersWithBalanceIterator(List<UserVao> users) {
        this.iterator = users.iterator();
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            UserVao potentialUser = iterator.next();
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
    public UserVao next() {
        UserVao currentUser = nextUser;
        continueSearching();
        return currentUser;
    }
}
