package si.um.feri.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ProviderService;
import si.um.feri.vao.ProviderVao;
import java.util.List;

@Named("showProviders")
@RequestScoped
public class ShowProviders {
    @Inject
    ProviderService providerService;

    public List<ProviderVao> getProviders() {
        return providerService.getProviders();
    }

    private String selectedProvider;

    public void removeProvider() {
        providerService.deleteProvider(selectedProvider);
    }

    public String getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(String selectedProvider) {
        this.selectedProvider = selectedProvider;
    }
}

