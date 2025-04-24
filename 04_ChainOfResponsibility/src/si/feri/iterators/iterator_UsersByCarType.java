package si.feri.iterators;

import si.feri.enums.enum_CarType;
import si.feri.vao.vao_Station;
import si.feri.vao.vao_User;

import java.util.Iterator;
import java.util.List;

public class iterator_UsersByCarType implements Iterator<vao_User> {
    private final Iterator<vao_User> iterator;

    private vao_User nextUser;
    private final enum_CarType carType;

    public iterator_UsersByCarType(List<vao_User> users, enum_CarType carType) {
        this.iterator = users.iterator();
        this.carType = carType;
        continueSearching();
    }

    private void continueSearching() {
        while (iterator.hasNext()) {
            vao_User potentialUser = iterator.next();
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
    public vao_User next() {
        vao_User currentUser = nextUser;
        continueSearching();
        return currentUser;
    }
}
