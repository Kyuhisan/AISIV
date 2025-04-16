package si.um.feri.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.dao.interfaces.ChargingStationIDao;
import si.um.feri.vao.ChargingStationVao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class ChargingStationDao implements ChargingStationIDao {
    private final List<ChargingStationVao> listOfStations = Collections.synchronizedList(new ArrayList<>());

    @PersistenceContext
    EntityManager em;

    //  create
    @Override
    public void addChargingStation(ChargingStationVao station) {
        em.persist(station);
    }

    //  read
    @Override
    public List<ChargingStationVao> getChargingStations() {
        return em.createQuery("SELECT c FROM ChargingStationVao c", ChargingStationVao.class).getResultList();
    }
    @Override
    public Optional<ChargingStationVao> getChargingStationByLocation(String location) {
        return em.createQuery("SELECT c FROM ChargingStationVao c WHERE c.location = :location", ChargingStationVao.class)
                .setParameter("location", location)
                .getResultList()
                .stream()
                .findFirst();
    }

    //  update
    @Override
    public void updateChargingStation(ChargingStationVao station) {
//        synchronized (listOfStations) {
//            getChargingStationByLocation(station.getLocation()).ifPresentOrElse(
//                    stationToUpdate -> listOfStations.set(listOfStations.indexOf(stationToUpdate), station),
//                    () -> System.out.println("❌ Station not found!")
//            );
//        }
    }

    //  delete
    @Override
    public void deleteChargingStation(ChargingStationVao station) {
        ChargingStationVao managed = em.contains(station) ? station : em.merge(station);
        em.remove(managed);
    }
}
