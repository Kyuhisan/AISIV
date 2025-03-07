package si.feri.dao;

import si.feri.dao.Interfaces.Provider_DAO_Interface;
import si.feri.vao_model.Provider_VAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Provider_DAO implements Provider_DAO_Interface {
    private List<Provider_VAO> providers = Collections.synchronizedList(new ArrayList<>());
    //private List<Provider_VAO> providers = new ArrayList<>();
    //private List<Provider_VAO> providers = new CopyOnWriteArrayList<>();

    @Override
    public void createProvider(Provider_VAO provider) {
        providers.add(provider);
    }

    @Override
    public List<Provider_VAO> getProviders() {
        return providers;
    }

    @Override
    public Optional<Provider_VAO> getProviderByName(String providerName) {
        return providers.stream().filter(provider -> provider.getProviderName().equals(providerName)).findFirst();
    }

    @Override
    public void updateProvider(Provider_VAO updatedProvider) {
        for (int i = 0; i < providers.size(); i++) {
            if (providers.get(i).getProviderName().equals(updatedProvider.getProviderName())) {
                providers.set(i, updatedProvider);
                return;
            }
        }
        System.out.println("❌ Provider not found!");
    }

    @Override
    public void deleteProvider(String providerName) {
        providers.removeIf(provider -> provider.getProviderName().equals(providerName));
    }
}
