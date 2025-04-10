package si.um.feri.service;

import si.um.feri.dao.ProviderDao;
import si.um.feri.dao.interfaces.ProviderIDao;
import si.um.feri.vao.ProviderVao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ProviderService implements Serializable {
    private final ProviderIDao dao_provider = ProviderDao.getInstance();

    //  create
    public void addProvider(ProviderVao provider) {
        if (dao_provider.getProviderByName(provider.getProviderName()).isPresent()) {
            throw new IllegalArgumentException("❌ Provider already exists!");
        } else {
            dao_provider.addProvider(provider);
            System.out.println("✅ Provider added!");
        }
    }

    //  read
    public List<ProviderVao> getProviders() {
        if (dao_provider.getProviders().isEmpty()) {
            throw new IllegalArgumentException("❌ No providers found!");
        } else {
            System.out.println("✅ Providers found!");
            return dao_provider.getProviders();
        }
    }
    public Optional<ProviderVao> getProviderByName(String providerName) {
        if (dao_provider.getProviderByName(providerName).isEmpty()) {
            throw new IllegalArgumentException("❌ Provider not found!");
        } else {
            System.out.println("✅ Provider found!");
            return dao_provider.getProviderByName(providerName);
        }
    }

    //  update
    public void updateProvider(ProviderVao updatedProvider) {
        if (dao_provider.getProviderByName(updatedProvider.getProviderName()).isEmpty()) {
            throw new IllegalArgumentException("❌ Provider not found!");
        } else {
            System.out.println("✅ Provider updated!");
            dao_provider.updateProvider(updatedProvider);
        }
    }

    //  delete
    public void deleteProvider(String providerName) {
        if (dao_provider.getProviderByName(providerName).isEmpty()) {
            throw new IllegalArgumentException("❌ Provider not found!");
        }
        Optional<ProviderVao> provider = dao_provider.getProviderByName(providerName);

        if (provider.isPresent()) {
            System.out.println("✅ Provider deleted!");
            dao_provider.deleteProvider(providerName);
        } else {
            throw new IllegalArgumentException("❌ Provider not found!");
        }
    }
}