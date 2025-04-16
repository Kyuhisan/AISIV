package si.um.feri.chainofresponsibility;

import si.um.feri.enums.carTypeENUM;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

public class VehicleCompatibilityCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public boolean handleRequest(UserVao user, ChargingStationVao station) {
        if (!user.getCarType().equals(carTypeENUM.ELECTRIC)) {
            System.out.println("❌ Vehicle type not compatible.");
            return false;
        }
        System.out.println("✅ Vehicle compatibility check passed.");
        return next == null || next.handleRequest(user, station);
    }
}
