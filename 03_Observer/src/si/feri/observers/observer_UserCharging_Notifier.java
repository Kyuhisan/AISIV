package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public class observer_UserCharging_Notifier implements observer_Station {
    @Override
    public void update(vao_Provider provider, vao_Station station, String action) {
        if ("charging".equals(action)) {
            System.out.println("\uD83D\uDCE9 [EMAIL] From: noreply@chargingstations.com");
            System.out.println("\uD83D\uDCE9 [EMAIL] For: " + station.getCurrentUserEmail());
            System.out.println("\uD83D\uDCE9 [EMAIL] Subject: Charging started! ⚡");
            System.out.println();
            System.out.println("Hello,");
            System.out.println();
            System.out.println("your charging at the station **" + station.getLocation() + "** has started. \uD83D\uDD0C");
            System.out.println("🚗 Charging power: " + station.getChargingSpeed() + " kW");
            System.out.println();
            System.out.println("Best regards,");
            System.out.println(provider.getProviderName());
        } else if ("stopped".equals(action)) {
            System.out.println("\uD83D\uDCE9 [EMAIL] From: noreply@chargingstations.com");
            System.out.println("\uD83D\uDCE9 [EMAIL] For: " + station.getCurrentUserEmail());
            System.out.println("\uD83D\uDCE9 [EMAIL] Subject: Charging stopped! ⚡");
            System.out.println();
            System.out.println("Hello,");
            System.out.println();
            System.out.println("your charging at the station **" + station.getLocation() + "** has concluded. \uD83D\uDD0C");
            System.out.println("🚗 Charging power: " + station.getChargingSpeed() + " kW");
            System.out.println();
            System.out.println("Best regards,");
            System.out.println(provider.getProviderName());
        }
    }
}
