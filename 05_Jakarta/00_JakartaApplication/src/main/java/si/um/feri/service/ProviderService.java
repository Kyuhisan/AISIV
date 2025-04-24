package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.ProviderIDao;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.service.interfaces.ProviderIService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProviderService implements Serializable, ProviderIService {
    @EJB
    private ProviderIDao dao_provider;
    @EJB
    private ChargingStationIService chargingStationService;

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
        if (updatedProvider == null || updatedProvider.getProviderName() == null || updatedProvider.getProviderName().isBlank()) {
            throw new IllegalArgumentException("❌ Invalid provider or missing name!");
        }

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
            List<ChargingStationVao> stations = chargingStationService.getChargingStations();
            for (ChargingStationVao station : stations) {
                if (station.getProvider() != null &&
                        station.getProvider().getProviderName().equals(providerName)) {
                    station.setProvider(null);
                    chargingStationService.updateChargingStation(station); // persist change
                }
            }
            System.out.println("✅ Provider deleted!");
            dao_provider.deleteProvider(providerName);
        } else {
            throw new IllegalArgumentException("❌ Provider not found!");
        }
    }
}