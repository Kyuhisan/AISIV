package si.um.feri.service.interfaces;

import si.um.feri.vao.ProviderVao;

import java.util.List;
import java.util.Optional;

public interface ProviderIService {
    void addProvider(ProviderVao provider);

    List<ProviderVao> getProviders();

    Optional<ProviderVao> getProviderByName(String providerName);

    void updateProvider(ProviderVao updatedProvider);

    void deleteProvider(String providerName);
}
