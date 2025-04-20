package si.um.feri.vao;

import lombok.*;
import si.um.feri.enums.connectorENUM;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ChargingStationVao {
    private String location;
    private connectorENUM connector;
    private ProviderVao provider;
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
}
