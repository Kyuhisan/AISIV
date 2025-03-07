package si.feri.dao.Interfaces;

import si.feri.vao_model.Provider_VAO;
import java.util.List;
import java.util.Optional;

public interface Provider_DAO_Interface {
    //create
    void createProvider(Provider_VAO provider);
    //read
    List<Provider_VAO> getProviders();
    Optional<Provider_VAO> getProviderByName(String providerName);
    //update
    void updateProvider(Provider_VAO providerVAO);
    //delete
    void deleteProvider(String providerName);
}