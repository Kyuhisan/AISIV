package si.um.feri.vao;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.um.feri.enums.regionENUM;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProviderVao {
    private String providerName;
    private regionENUM activeRegion;
    private List<ChargingStationVao> listOfStations;

    public ProviderVao(String providerName, regionENUM activeRegion) {
        this();
        this.providerName = providerName;
        this.listOfStations = new ArrayList<>();
        this.activeRegion = activeRegion;
    }
}
