package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;
import si.um.feri.service.interfaces.ProviderIService;
import si.um.feri.vao.ProviderVao;
import java.util.List;

@Named("showProviders")
@RequestScoped
public class ShowProvidersBean {
    @EJB
    private ProviderIService providerService;
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

    public void removeProvider() {
        providerService.deleteProvider(selectedProviderName);
    }

    public void onCellEdit(CellEditEvent event) {
        ProviderVao edited = (ProviderVao) event.getRowData();

        try {
            providerService.updateProvider(edited);
            System.out.println("✅ Provider updated: " + edited.getProviderName());
        } catch (Exception e) {
            System.err.println("❌ Error updating provider: " + e.getMessage());
        }
    }
}

