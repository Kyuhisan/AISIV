package si.um.feri.vao;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.connectorENUM;
import si.um.feri.observers.ChargingStationObserver;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChargingStationVao {
    //  variables
    private ProviderVao providerVao;
    private connectorENUM connector;
    private String location;
    private boolean isAvailable;
    private double chargingSpeed;
    private String currentUserEmail;
    private List<ChargingStationObserver> stationObservers = new ArrayList<>();

    //  constructors
    public ChargingStationVao() {

    }
    public ChargingStationVao(ProviderVao provider, connectorENUM connector, String location, boolean isAvailable, double chargingSpeed) {
        this();
        this.providerVao = provider;
        this.connector = connector;
        this.location = location;
        this.isAvailable = isAvailable;
        this.chargingSpeed = chargingSpeed;
        this.currentUserEmail = "";
    }

    //  observers
    public void addObserver(ChargingStationObserver observer) {
        stationObservers.add(observer);
    }
    public void removeObserver(ChargingStationObserver observer) {
        stationObservers.remove(observer);
    }
    public void notifyObservers(ChargingStationVao station, String action) {
        for (ChargingStationObserver observer : stationObservers) {
            observer.update(providerVao, station, action);
        }
    }

    //  observer linked functions
    public void getStationsAvailabilityStatus(ChargingStationVao station) {
        notifyObservers(station, "status");
    }
    public void startCharging(ChargingStationVao station, String userEmail) {
        setCurrentUserEmail(userEmail);
        setAvailable(false);
        notifyObservers(station, "charging");
    }
    public void stopCharging(ChargingStationVao station) {
        setCurrentUserEmail(null);
        setAvailable(true);
        notifyObservers(station, "stopped");
    }

    //  toString
    @Override
    public String toString() {
        return "📍 Station: " + "\n" +
                "\t" + "Location: " + getLocation() + "\n" +
                "\t" + "Connector: " + getConnector() + "\n" +
                "\t" + "Available: " + isAvailable() + "\n" +
                "\t" + "ProviderVao: " + getProviderVao().getProviderName() + "\n" +
                "\t" + "Charging Speed: " + getChargingSpeed() + "kW." + "\n" +
                "\t" + "Active UserVao: " + getCurrentUserEmail() + "\n";
    }
}
