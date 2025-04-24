package si.feri.dao;

import si.feri.dao.Interfaces.i_dao_Provider;
import si.feri.vao.vao_Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class dao_Provider implements i_dao_Provider {
    private final List<vao_Provider> listOfProviders = Collections.synchronizedList(new ArrayList<>());

    //  create
    @Override
    public void addProvider(vao_Provider provider) {
        listOfProviders.add(provider);
    }

    //  read
    @Override
    public List<vao_Provider> getProviders() {
        return listOfProviders;
    }
    @Override
    public Optional<vao_Provider> getProviderByName(String providerName) {
        return listOfProviders.stream().filter(provider -> provider.getProviderName().equals(providerName)).findFirst();
    }

    //  update
    @Override
    public void updateProvider(vao_Provider updatedProvider) {
        getProviderByName(updatedProvider.getProviderName()).ifPresentOrElse(
                provider -> listOfProviders.set(listOfProviders.indexOf(provider), updatedProvider),
                () -> System.out.println("❌ Provider not found!")
        );
    }

    //  delete
    @Override
    public void deleteProvider(String providerName) {
        listOfProviders.removeIf(provider -> provider.getProviderName().equals(providerName));
    }
}
