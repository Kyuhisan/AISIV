package si.um.feri.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.dao.interfaces.ProviderIDao;
import si.um.feri.vao.ProviderVao;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProviderDao implements ProviderIDao {
    @PersistenceContext
    EntityManager em;

    //  create
    @Override
    public void addProvider(ProviderVao provider) {
        em.persist(provider);
    }

    //  read
    @Override
    public List<ProviderVao> getProviders() {
        return em.createQuery("SELECT p FROM ProviderVao p", ProviderVao.class).getResultList();
    }
    @Override
    public Optional<ProviderVao> getProviderByName(String providerName) {
        return em.createQuery("SELECT p FROM ProviderVao p WHERE p.providerName = :providerName", ProviderVao.class)
                .setParameter("providerName", providerName)
                .getResultList()
                .stream()
                .findFirst();
    }

    //  update
    @Override
    public void updateProvider(ProviderVao updatedProvider) {
//        synchronized (listOfProviders) {
//            getProviderByName(updatedProvider.getProviderName()).ifPresentOrElse(
//                    provider -> listOfProviders.set(listOfProviders.indexOf(provider), updatedProvider),
//                    () -> System.out.println("❌ Provider not found!")
//            );
//        }
    }

    //  delete
    @Override
    public void deleteProvider(String providerName) {
        getProviderByName(providerName).ifPresent(provider -> {
            em.remove(em.contains(provider) ? provider : em.merge(provider));
        });
    }
}