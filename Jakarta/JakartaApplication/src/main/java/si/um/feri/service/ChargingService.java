package si.um.feri.service;

import si.um.feri.chainofresponsibility.*;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

public class ChargingService {
    private final UserHandler userChain;

    public ChargingService() {
        StationAvailabilityCheck availability = new StationAvailabilityCheck();
        BalanceCheck balance = new BalanceCheck();
        VehicleCompatibilityCheck compatibility = new VehicleCompatibilityCheck();

        availability.setNextHandler(balance);
        balance.setNextHandler(compatibility);
        userChain = balance;
    }

    public boolean startCharging(UserVao user, ChargingStationVao station) {
        System.out.println("🔋 Starting charging process...");

        boolean userOk = userChain.handleRequest(user, station);

        if (userOk) {
            System.out.println("✅ Charging started successfully for user: " + user.getName());
            user.setBalance(user.getBalance() - 10);
            station.setAvailable(false);
            return true;
        }

        System.out.println("❌ Charging aborted due to failed validation.");
        return false;
    }

    public void stopCharging(UserVao user, ChargingStationVao station) {
        station.setAvailable(true);
        System.out.println("🛑 Charging stopped for user: " + user.getName());
    }
}

