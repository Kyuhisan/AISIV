package si.feri.service;

import si.feri.chainofresponsibility.*;
import si.feri.vao.vao_Station;
import si.feri.vao.vao_User;

public class service_ChargingService {
    private final UserHandler userChain;
    private final StationHandler stationChain;

    public service_ChargingService() {
        // Station chain
        StationAvailabilityCheck availability = new StationAvailabilityCheck();
        stationChain = availability;

        // User chain
        BalanceCheck balance = new BalanceCheck();
        VehicleCompatibilityCheck compatibility = new VehicleCompatibilityCheck();
        balance.setNextHandler(compatibility);
        userChain = balance;
    }

    public boolean startCharging(vao_User user, vao_Station station) {
        System.out.println("🔋 Starting charging process...");

        boolean stationOk = stationChain.handleRequest(station);
        boolean userOk = userChain.handleRequest(user);

        if (stationOk && userOk) {
            System.out.println("✅ Charging started successfully for user: " + user.getName());
            user.setBalance(user.getBalance() - 10); // Simulate cost
            station.setAvailable(false); // Simulate occupation
            return true;
        }

        System.out.println("❌ Charging aborted due to failed validation.");
        return false;
    }

    public void stopCharging(vao_User user, vao_Station station) {
        station.setAvailable(true);
        System.out.println("🛑 Charging stopped for user: " + user.getName());
    }
}

