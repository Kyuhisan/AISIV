package si.um.feri.vao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.regionENUM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class ProviderVao {
    @Id
    private String providerName;

    @Enumerated(EnumType.STRING)
    private regionENUM activeRegion;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private List<ChargingStationVao> listOfStations;

    //  constructors
    public ProviderVao() {

    }
    public ProviderVao(String providerName, regionENUM activeRegion) {
        this();
        this.providerName = providerName;
        this.listOfStations = new ArrayList<>();
        this.activeRegion = activeRegion;
    }

    //  toString
    @Override
    public String toString() {
        return "🏢 ProviderVao: " + "\n" +
                "\t" + "ProviderVao Name: " +  getProviderName() + "\n" +
                "\t" + "Active Region: " + getActiveRegion() + "\n" +
                "\t" + "Stations: " + "\n" + getListOfStations().stream().map(ChargingStationVao::toString).collect(Collectors.joining());
    }
}
