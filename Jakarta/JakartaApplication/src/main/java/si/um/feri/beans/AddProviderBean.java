package si.um.feri.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.regionENUM;
import si.um.feri.service.ProviderService;
import si.um.feri.vao.ProviderVao;

import java.io.Serializable;
import java.util.ArrayList;

@Named("addProvider")
@SessionScoped
@Getter
@Setter
public class AddProviderBean implements Serializable {
    private ProviderVao provider = new ProviderVao();

    @Inject
    private ProviderService providerService;

    public void addProvider() {
        provider.setListOfStations(new ArrayList<>());
        providerService.addProvider(provider);
        System.out.println("✅ Provider added: " + provider);
        provider = new ProviderVao();
    }

    public regionENUM[] getRegions() {
        return regionENUM.values();
    }
}