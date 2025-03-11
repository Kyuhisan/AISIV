package si.feri.dao.Interfaces;

import si.feri.vao.vao_Provider;
import java.util.List;
import java.util.Optional;

public interface i_dao_Provider {
    //  create
    void addProvider(vao_Provider provider);

    //  read
    List<vao_Provider> getProviders();
    Optional<vao_Provider> getProviderByName(String providerName);

    //  update
    void updateProvider(vao_Provider providerVAO);

    //  delete
    void deleteProvider(String providerName);
}