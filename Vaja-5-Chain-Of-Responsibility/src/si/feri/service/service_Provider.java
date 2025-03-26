package si.feri.service;

import si.feri.dao.Interfaces.i_dao_Provider;
import si.feri.dao.dao_Provider;
import si.feri.vao.vao_Provider;

import java.util.List;
import java.util.Optional;

public class service_Provider {
    private final i_dao_Provider dao_provider = dao_Provider.getInstance();

    //  create
    public void addProvider(vao_Provider provider) {
        if (dao_provider.getProviderByName(provider.getProviderName()).isPresent()) {
            throw new IllegalArgumentException("❌ Provider already exists!");
        } else {
            dao_provider.addProvider(provider);
            System.out.println("✅ Provider added!");
        }
    }

    //  read
    public List<vao_Provider> getProviders() {
        if (dao_provider.getProviders().isEmpty()) {
            throw new IllegalArgumentException("❌ No providers found!");
        } else {
            System.out.println("✅ Providers found!");
            return dao_provider.getProviders();
        }
    }
    public Optional<vao_Provider> getProviderByName(String providerName) {
        if (dao_provider.getProviderByName(providerName).isEmpty()) {
            throw new IllegalArgumentException("❌ Provider not found!");
        } else {
            System.out.println("✅ Provider found!");
            return dao_provider.getProviderByName(providerName);
        }
    }

    //  update
    public void updateProvider(vao_Provider updatedProvider) {
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
        Optional<vao_Provider> provider = dao_provider.getProviderByName(providerName);

        if (provider.isPresent()) {
            System.out.println("✅ Provider deleted!");
            dao_provider.deleteProvider(providerName);
        } else {
            throw new IllegalArgumentException("❌ Provider not found!");
        }
    }
}