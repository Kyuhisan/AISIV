package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.primefaces.PrimeFaces;
import si.um.feri.service.interfaces.ProviderIService;
import si.um.feri.vao.ProviderVao;

import java.io.Serializable;
import java.util.List;

@Named("showProviders")
@ViewScoped
public class ShowProvidersBean implements Serializable {
    public ShowProvidersBean() {
        selectedProvider = new ProviderVao();
    }

    @EJB
    private ProviderIService providerService;

    private String selectedProviderName;
    private ProviderVao selectedProvider;

    public String getSelectedProviderName() {
        return selectedProviderName;
    }
    public void setSelectedProviderName(String selectedProvider) {
        this.selectedProviderName = selectedProvider;
    }

    public ProviderVao getSelectedProvider() {
        return selectedProvider;
    }
    public void setSelectedProvider(ProviderVao selectedProvider) {
        this.selectedProvider = selectedProvider;
    }
    public void setSelectedProvider(String selectedProviderName) {
        this.selectedProvider = providerService.getProviderByName(selectedProviderName).orElse(null);
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
        if (selectedProvider == null || selectedProvider.getProviderName() == null || selectedProvider.getProviderName().isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Provider", "Provider name is required."));
            return;
        }

        providerService.updateProvider(selectedProvider);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Provider Updated"));
        PrimeFaces.current().executeScript("PF('manageProviderDialog').hide()");
        PrimeFaces.current().ajax().update("mainForm:datatable-providers");
    }

    public void removeProvider() {
        providerService.deleteProvider(selectedProviderName);
    }
}

