package si.um.feri;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.chainofresponsibility.*;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

import java.util.Optional;

@Stateless
public class ChargingService implements ChargingIService {
    private final UserHandler userChain;
    @EJB
    private ChargingStationIService stationService;
    @EJB
    private UserIService userService;

    public ChargingService() {
        StationAvailabilityCheck availability = new StationAvailabilityCheck();
        BalanceCheck balance = new BalanceCheck();
        VehicleCompatibilityCheck compatibility = new VehicleCompatibilityCheck();

        availability.setNextHandler(balance);
        balance.setNextHandler(compatibility);
        userChain = balance;
    }

    @Override
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

    @Override
    public void stopCharging(UserVao user, ChargingStationVao station) {
        station.setAvailable(true);
        System.out.println("🛑 Charging stopped for user: " + user.getName());
    }

    @Override
    public String canCharge(String userName, String stationName) {
        Optional<UserVao> user;
        Optional<ChargingStationVao> station;

        user = userService.getUserByEmail(userName);
        station = stationService.getChargingStationByLocation(stationName);

        if (user.isPresent() && station.isPresent()) {
            boolean result = startCharging(user.get(), station.get());
            if (result) {
                return "Yes the user can charge at this station.";
            } else {
                return "The user cannot charge at this station.";
            }
        } return "User or station not found.";
    }
}

