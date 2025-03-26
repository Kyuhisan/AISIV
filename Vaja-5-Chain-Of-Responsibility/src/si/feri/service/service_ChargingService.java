package si.feri.service;

import si.feri.chainofresponsibility.*;
import si.feri.enums.enum_CarType;
import si.feri.vao.vao_Station;
import si.feri.vao.vao_User;

public class service_ChargingService {
    private final StationHandler stationChain;
    private final UserHandler userChain;

    public service_ChargingService() {
        // Build station handler chain
        StationAvailabilityCheck availabilityCheck = new StationAvailabilityCheck();
        stationChain = availabilityCheck;

        // Build user handler chain
        BalanceCheck balanceCheck = new BalanceCheck();
        VehicleCompatibilityCheck compatibilityCheck = new VehicleCompatibilityCheck();

        balanceCheck.setNextHandler(compatibilityCheck);
        userChain = balanceCheck;
    }

    public boolean startCharging(vao_User user, vao_Station station) {
        System.out.println("🔌 Attempting to start charging for " + user.getName());

        // Check station availability
        if (!station.isAvailable()) {
            System.out.println("❌ Station is not available.");
            return false;
        }
        stationChain.handleRequest(station);

        // Check user constraints
        if (user.getBalance() < 0) {
            System.out.println("❌ User does not have sufficient balance.");
            return false;
        }
        if (!user.getCarType().equals(enum_CarType.ELECTRIC)) {
            System.out.println("❌ Car type not compatible with station.");
            return false;
        }

        userChain.handleRequest(user);

        // If passed all checks, start charging
        System.out.println("✅ Charging started successfully.");
        user.setBalance(user.getBalance() - 10); // simulate balance deduction
        station.setAvailable(false); // mark station as busy
        return true;
    }

    public void stopCharging(vao_User user, vao_Station station) {
        station.setAvailable(true); // make station free again
        System.out.println("🛑 Charging stopped for " + user.getName());
    }
}
