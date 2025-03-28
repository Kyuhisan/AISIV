package si.um.feri.chainofresponsibility;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

public class BalanceCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public boolean handleRequest(UserVao user, ChargingStationVao station) {
        if (user.getBalance() < 0) {
            System.out.println("❌ User does not have sufficient balance.");
            return false;
        }
        System.out.println("✅ Balance check passed.");
        return next == null || next.handleRequest(user, station);
    }
}

