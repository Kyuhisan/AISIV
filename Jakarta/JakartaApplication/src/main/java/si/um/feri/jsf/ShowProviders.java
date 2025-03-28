package si.um.feri.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.ProviderService;

@Named("showProviders")
@RequestScoped
public class ShowProviders {
    @Inject
    private ProviderService providerService;

    public void printToConsole() {
        providerService.getProviders().forEach(System.out::println);
    }
}
