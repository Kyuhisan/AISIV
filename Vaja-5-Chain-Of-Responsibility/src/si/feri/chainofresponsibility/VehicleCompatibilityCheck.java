package si.feri.chainofresponsibility;

import si.feri.enums.enum_CarType;
import si.feri.vao.vao_User;

public class VehicleCompatibilityCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(vao_User user) {
        if (user.getCarType().equals(enum_CarType.ELECTRIC)) {
            System.out.println("Car type of user is valid!");
            return;
        }
        if (next != null) {
            next.handleRequest(user);
        }
    }
}