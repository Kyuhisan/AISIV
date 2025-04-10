package si.um.feri.dao;

import si.um.feri.dao.interfaces.ProviderIDao;
import si.um.feri.vao.ProviderVao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProviderDao implements ProviderIDao {
    private final List<ProviderVao> listOfProviders = Collections.synchronizedList(new ArrayList<>());
    private static volatile ProviderDao instance;

    private ProviderDao() {}

    public static ProviderDao getInstance() {
        if (instance == null) {
            synchronized (ProviderDao.class) {
                if (instance == null) {
                    instance = new ProviderDao();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addProvider(ProviderVao provider) {
        synchronized (listOfProviders) {
            listOfProviders.add(provider);
        }
    }

    //  read
    @Override
    public List<ProviderVao> getProviders() {
        synchronized (listOfProviders) {
            return listOfProviders;
        }
    }
    @Override
    public Optional<ProviderVao> getProviderByName(String providerName) {
        synchronized (listOfProviders) {
            return listOfProviders.stream().filter(provider -> provider.getProviderName().equals(providerName)).findFirst();
        }
    }

    //  update
    @Override
    public void updateProvider(ProviderVao updatedProvider) {
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