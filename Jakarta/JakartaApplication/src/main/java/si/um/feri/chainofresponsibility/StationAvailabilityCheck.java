package si.um.feri.chainofresponsibility;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

public class StationAvailabilityCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public boolean handleRequest(UserVao user, ChargingStationVao station) {
        if (!station.isAvailable()) {
            System.out.println("❌ Station is not available.");
            return false;
        }
        System.out.println("✅ Station availability check passed.");
        return next == null || next.handleRequest(user, station);
    }
}

