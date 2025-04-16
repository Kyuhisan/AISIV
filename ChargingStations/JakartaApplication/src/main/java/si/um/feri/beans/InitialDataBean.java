package si.um.feri.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import si.um.feri.enums.carTypeENUM;
import si.um.feri.enums.connectorENUM;
import si.um.feri.enums.regionENUM;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.service.interfaces.ProviderIService;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import si.um.feri.vao.UserVao;

@Startup
@Singleton
public class InitialDataBean {
    @PersistenceContext
    EntityManager em;

    @EJB
    ProviderIService provider;
    @EJB
    UserIService user;
    @EJB
    ChargingStationIService chargingStation;

    @PostConstruct
    @Transactional
    public void init() {
        em.createNativeQuery("DELETE FROM ChargingStationVao").executeUpdate();
        em.createNativeQuery("DELETE FROM UserVao").executeUpdate();
        em.createNativeQuery("DELETE FROM ProviderVao").executeUpdate();

        System.out.println(">> Populating test data on startup...");
        ProviderVao provider1 = new ProviderVao("Elektro Maribor", regionENUM.EUROPE);
        ProviderVao provider2 = new ProviderVao("Petrol Slovenia", regionENUM.EUROPE);
        ProviderVao provider3 = new ProviderVao("Tesla Superchargers", regionENUM.AMERICA);
        ProviderVao provider4 = new ProviderVao("Shell Recharge", regionENUM.ASIA);
        ProviderVao provider5 = new ProviderVao("Green Energy", regionENUM.EUROPE);
        provider.addProvider(provider1);
        provider.addProvider(provider2);
        provider.addProvider(provider3);
        provider.addProvider(provider4);
        provider.addProvider(provider5);

        ChargingStationVao station1 = new ChargingStationVao(provider1, connectorENUM.TYPE2, "Maribor - Center", true, 50.2);
        ChargingStationVao station2 = new ChargingStationVao(provider1, connectorENUM.CCS, "Ljubljana - BTC", false, 22.5);
        ChargingStationVao station3 = new ChargingStationVao(provider2, connectorENUM.CHADEMO, "Kranj - Main Road", true, 15.0);
        ChargingStationVao station4 = new ChargingStationVao(provider2, connectorENUM.TYPE1, "Celje - South", true, 77.0);
        ChargingStationVao station5 = new ChargingStationVao(provider3, connectorENUM.TESLA, "San Francisco - Market St.", true, 12.5);
        ChargingStationVao station6 = new ChargingStationVao(provider3, connectorENUM.CCS, "Los Angeles - Hollywood Blvd.", false, 22.5);
        ChargingStationVao station7 = new ChargingStationVao(provider4, connectorENUM.DOMESTIC, "Tokyo - Shibuya", true, 34.44);
        ChargingStationVao station8 = new ChargingStationVao(provider4, connectorENUM.TYPE2, "Shanghai - Pudong", false, 50.2);
        ChargingStationVao station9 = new ChargingStationVao(provider5, connectorENUM.TYPE1, "Vienna - City Center", true, 77.0);
        ChargingStationVao station10 = new ChargingStationVao(provider5, connectorENUM.CCS, "Berlin - Alexanderplatz", true, 22.5);
        station2.setCurrentUserEmail("matickuhar@gmail.com");
        station6.setCurrentUserEmail("janezplece@gmail.com");
        station5.setCurrentUserEmail("aleskokot@gmail.com");
        station4.setCurrentUserEmail("nejcpetric@gmail.com");
        station1.setCurrentUserEmail("peterkotnik@gmail.com");
        chargingStation.addChargingStation(station1);
        chargingStation.addChargingStation(station2);
        chargingStation.addChargingStation(station3);
        chargingStation.addChargingStation(station4);
        chargingStation.addChargingStation(station5);
        chargingStation.addChargingStation(station6);
        chargingStation.addChargingStation(station7);
        chargingStation.addChargingStation(station8);
        chargingStation.addChargingStation(station9);
        chargingStation.addChargingStation(station10);

        provider1.getListOfStations().add(station1);
        provider1.getListOfStations().add(station2);
        provider2.getListOfStations().add(station3);
        provider2.getListOfStations().add(station4);
        provider3.getListOfStations().add(station5);
        provider3.getListOfStations().add(station6);
        provider4.getListOfStations().add(station7);
        provider4.getListOfStations().add(station8);
        provider5.getListOfStations().add(station9);
        provider5.getListOfStations().add(station10);

        UserVao user1 = new UserVao("Matic Kuhar", "matickuhar@gmail.com", 125.22, carTypeENUM.GAS);
        UserVao user2 = new UserVao("Peter Kotnik", "peterkotnik@gmail.com", -25.2, carTypeENUM.ELECTRIC);
        UserVao user3 = new UserVao("Janez Pleče", "janezplece@gmail.com", 0, carTypeENUM.ELECTRIC);
        UserVao user4 = new UserVao("Aleš Kokot", "aleskokot@gmail.com", 0, carTypeENUM.DIESEL);
        UserVao user5 = new UserVao("Nejc Petrič", "nejcpetric@gmail.com", 241.22, carTypeENUM.ELECTRIC);
        user.addUser(user1);
        user.addUser(user2);
        user.addUser(user3);
        user.addUser(user4);
        user.addUser(user5);
        System.out.println(">> Test data successfully loaded.");
    }
}
