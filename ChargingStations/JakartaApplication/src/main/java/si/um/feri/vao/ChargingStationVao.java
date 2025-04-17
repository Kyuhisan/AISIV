package si.um.feri.vao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.enums.connectorENUM;
import si.um.feri.observers.ChargingStationObserver;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChargingStationVao {
    //  variables
    @Id
    private String location;

    @Enumerated(EnumType.STRING)
    private connectorENUM connector;

    @ManyToOne
    @JoinColumn(name = "provider_providerName", nullable = true)
    private ProviderVao provider;

    @Transient
    private List<ChargingStationObserver> stationObservers = new ArrayList<>();

    private boolean isAvailable;
    private double chargingSpeed;
    private String currentUserEmail;


    public ChargingStationVao(ProviderVao provider, connectorENUM connector, String location, boolean isAvailable, double chargingSpeed) {
        this();
        this.provider = provider;
        this.connector = connector;
        this.location = location;
        this.isAvailable = isAvailable;
        this.chargingSpeed = chargingSpeed;
        this.currentUserEmail = "";
    }

    public void addObserver(ChargingStationObserver observer) {
        stationObservers.add(observer);
    }
    public void removeObserver(ChargingStationObserver observer) {
        stationObservers.remove(observer);
    }
    public void notifyObservers(ChargingStationVao station, String action) {
        for (ChargingStationObserver observer : stationObservers) {
            observer.update(provider, station, action);
        }
    }
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

    @Override
    public String toString() {
        return "📍 Station: " + "\n" +
                "\t" + "Location: " + getLocation() + "\n" +
                "\t" + "Connector: " + getConnector() + "\n" +
                "\t" + "Available: " + isAvailable() + "\n" +
                "\t" + "ProviderVao: " + getProvider().getProviderName() + "\n" +
                "\t" + "Charging Speed: " + getChargingSpeed() + "kW." + "\n" +
                "\t" + "Active UserVao: " + getCurrentUserEmail() + "\n";
    }
}
