package si.um.feri.dao.interfaces;

import si.um.feri.vao.ProviderVao;
import java.util.List;
import java.util.Optional;

public interface ProviderIDao {
    //  create
    void addProvider(ProviderVao provider);

    //  read
    List<ProviderVao> getProviders();
    Optional<ProviderVao> getProviderByName(String providerName);

    //  update
    void updateProvider(ProviderVao providerVAO);

    //  delete
    void deleteProvider(String providerName);
}