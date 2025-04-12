package si.um.feri.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ProviderService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import java.util.List;
import java.util.Optional;

@Named("showProviders")
@RequestScoped
public class ShowProvidersBean {
    @Inject
    ProviderService providerService;
    private String selectedProviderName;

    public String getSelectedProviderName() {
        return selectedProviderName;
    }

    public void setSelectedProviderName(String selectedProvider) {
        this.selectedProviderName = selectedProvider;
    }

    public Object getProviderNames() {
        return providerService.getProviders().stream()
                .map(ProviderVao::getProviderName)
                .toList();
    }

    //  CRUD
    public List<ProviderVao> getProviders() {
        return providerService.getProviders();
    }

    public void updateProvider() {
        Optional<ProviderVao> selectedProvider = providerService.getProviderByName(selectedProviderName);

        if (selectedProvider.isPresent()) {
            ProviderVao provider = selectedProvider.get();
            providerService.updateProvider(provider);
        } else {
            System.out.println("Provider not found with email: " + selectedProvider);
        }
    }

    public void removeProvider() {
        providerService.deleteProvider(selectedProviderName);
    }


}

