package si.feri.dao;

import si.feri.dao.Interfaces.i_dao_Provider;
import si.feri.vao.vao_Provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class dao_Provider implements i_dao_Provider {
    private final List<vao_Provider> listOfProviders = Collections.synchronizedList(new ArrayList<>());
    private static volatile dao_Provider instance;

    private dao_Provider() {}

    public static dao_Provider getInstance() {
        if (instance == null) {
            synchronized (dao_Provider.class) {
                if (instance == null) {
                    instance = new dao_Provider();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addProvider(vao_Provider provider) {
        synchronized (listOfProviders) {
            listOfProviders.add(provider);
        }
    }

    //  read
    @Override
    public List<vao_Provider> getProviders() {
        synchronized (listOfProviders) {
            return listOfProviders;
        }
    }
    @Override
    public Optional<vao_Provider> getProviderByName(String providerName) {
        synchronized (listOfProviders) {
            return listOfProviders.stream().filter(provider -> provider.getProviderName().equals(providerName)).findFirst();
        }
    }

    //  update
    @Override
    public void updateProvider(vao_Provider updatedProvider) {
        synchronized (listOfProviders) {
            getProviderByName(updatedProvider.getProviderName()).ifPresentOrElse(
                    provider -> listOfProviders.set(listOfProviders.indexOf(provider), updatedProvider),
                    () -> System.out.println("❌ Provider not found!")
            );
        }
    }

    //  delete
    @Override
    public void deleteProvider(String providerName) {
        synchronized (listOfProviders) {
            listOfProviders.removeIf(provider -> provider.getProviderName().equals(providerName));
        }
    }
}