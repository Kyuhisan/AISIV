package si.um.feri.iterators;

import si.um.feri.enums.carTypeENUM;
import si.um.feri.vao.UserVao;
import java.util.Iterator;
import java.util.List;

public class UsersByCarTypeIterator implements Iterator<UserVao> {
    private final Iterator<UserVao> iterator;

    private UserVao nextUser;
    private final carTypeENUM carType;

    public UsersByCarTypeIterator(List<UserVao> users, carTypeENUM carType) {
        this.iterator = users.iterator();
        this.carType = carType;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            UserVao potentialUser = iterator.next();
            if (potentialUser.getCarType().equals(carType)) {
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
