import si.feri.dao.ChargingStation_DAO;
import si.feri.dao.Provider_DAO;
import si.feri.vao_model.ChargingStation_VAO;
import si.feri.vao_model.Connector_VAO;
import si.feri.vao_model.Provider_VAO;
import si.feri.vao_model.Regions_VAO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final ChargingStation_DAO chargingStationDAO = new ChargingStation_DAO();
    private static final Provider_DAO providerDAO = new Provider_DAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        while (true) {
            System.out.println("\n🔌 EV Charging Station System");
            System.out.println("1️⃣ Add Provider");
            System.out.println("2️⃣ View Providers");
            System.out.println("3️⃣ Update Provider");
            System.out.println("4️⃣ Delete Provider");
            System.out.println("5️⃣ Add Charging Station");
            System.out.println("6️⃣ View Charging Stations");
            System.out.println("7️⃣ Update Charging Station");
            System.out.println("8️⃣ Delete Charging Station");
            System.out.println("9️⃣ Find Provider");
            System.out.println("🔟 Find Charging Station");
            System.out.println("0️⃣ Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addProvider();
                case 2 -> viewProviders();
                case 3 -> updateProvider();
                case 4 -> deleteProvider();
                case 5 -> addChargingStation();
                case 6 -> viewChargingStations();
                case 7 -> updateChargingStation();
                case 8 -> deleteChargingStation();
                case 9 -> findProvider();
                case 10 -> findChargingStation();
                case 0 -> {
                    System.out.println("🚪 Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void initializeData() {
        Provider_VAO provider1 = new Provider_VAO("Elektro Maribor", Regions_VAO.EUROPE);
        Provider_VAO provider2 = new Provider_VAO("Petrol Slovenia", Regions_VAO.EUROPE);
        Provider_VAO provider3 = new Provider_VAO("Tesla Superchargers", Regions_VAO.AMERICA);
        Provider_VAO provider4 = new Provider_VAO("Shell Recharge", Regions_VAO.ASIA);
        Provider_VAO provider5 = new Provider_VAO("Green Energy", Regions_VAO.EUROPE);

        // Adding Providers to DAO
        providerDAO.createProvider(provider1);
        providerDAO.createProvider(provider2);
        providerDAO.createProvider(provider3);
        providerDAO.createProvider(provider4);
        providerDAO.createProvider(provider5);

        // Creating Charging Stations
        ChargingStation_VAO station1 = new ChargingStation_VAO(provider1.getProviderName(), Connector_VAO.TYPE2, "Maribor - Center", true);
        ChargingStation_VAO station2 = new ChargingStation_VAO(provider1.getProviderName(), Connector_VAO.CCS, "Ljubljana - BTC", false);
        ChargingStation_VAO station3 = new ChargingStation_VAO(provider2.getProviderName(), Connector_VAO.CHADEMO, "Kranj - Main Road", true);
        ChargingStation_VAO station4 = new ChargingStation_VAO(provider2.getProviderName(), Connector_VAO.TYPE1, "Celje - South", true);
        ChargingStation_VAO station5 = new ChargingStation_VAO(provider3.getProviderName(), Connector_VAO.TESLA, "San Francisco - Market St.", true);
        ChargingStation_VAO station6 = new ChargingStation_VAO(provider3.getProviderName(), Connector_VAO.CCS, "Los Angeles - Hollywood Blvd.", false);
        ChargingStation_VAO station7 = new ChargingStation_VAO(provider4.getProviderName(), Connector_VAO.DOMESTIC, "Tokyo - Shibuya", true);
        ChargingStation_VAO station8 = new ChargingStation_VAO(provider4.getProviderName(), Connector_VAO.TYPE2, "Shanghai - Pudong", false);
        ChargingStation_VAO station9 = new ChargingStation_VAO(provider5.getProviderName(), Connector_VAO.TYPE1, "Vienna - City Center", true);
        ChargingStation_VAO station10 = new ChargingStation_VAO(provider5.getProviderName(), Connector_VAO.CCS, "Berlin - Alexanderplatz", true);

        // Adding Charging Stations to DAO
        chargingStationDAO.createChargingStation(station1);
        chargingStationDAO.createChargingStation(station2);
        chargingStationDAO.createChargingStation(station3);
        chargingStationDAO.createChargingStation(station4);
        chargingStationDAO.createChargingStation(station5);
        chargingStationDAO.createChargingStation(station6);
        chargingStationDAO.createChargingStation(station7);
        chargingStationDAO.createChargingStation(station8);
        chargingStationDAO.createChargingStation(station9);
        chargingStationDAO.createChargingStation(station10);

        // Adding Charging Stations to providers
        provider1.getListOfStations().add(station1.getLocation());
        provider1.getListOfStations().add(station2.getLocation());
        provider2.getListOfStations().add(station3.getLocation());
        provider2.getListOfStations().add(station4.getLocation());
        provider3.getListOfStations().add(station5.getLocation());
        provider3.getListOfStations().add(station6.getLocation());
        provider4.getListOfStations().add(station7.getLocation());
        provider4.getListOfStations().add(station8.getLocation());
        provider5.getListOfStations().add(station9.getLocation());
        provider5.getListOfStations().add(station10.getLocation());
    }

    private static void addProvider() {
        System.out.print("Enter Provider Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Active Region (EUROPE, ASIA, AMERICA, AFRICA, AUSTRALIA, ANTARCTICA, UNKNOWN): ");
        Regions_VAO region = Regions_VAO.valueOf(scanner.nextLine().toUpperCase());
        Provider_VAO provider = new Provider_VAO(name, region);
        providerDAO.createProvider(provider);
        System.out.println("✅ Provider added!");
    }

    private static void viewProviders() {
        System.out.println("\n📌 Providers:");
        providerDAO.getProviders().forEach(provider ->
                System.out.println("🏢 " + provider.getProviderName() + " | Charging Stations: " + provider.getListOfStations() + " | Active Region: " + provider.getActiveRegion())
        );
    }

    private static void updateProvider() {
        System.out.print("Enter Provider Name to Update: ");
        String name = scanner.nextLine();
        Optional<Provider_VAO> existingProvider = providerDAO.getProviderByName(name);

        if (existingProvider.isPresent()) {
            Provider_VAO provider = existingProvider.get();
            System.out.print("Enter New Provider Name: ");
            provider.setProviderName(scanner.nextLine());

            System.out.print("Enter New Active Region: ");
            provider.setActiveRegion(Regions_VAO.valueOf(scanner.nextLine().toUpperCase()));

            System.out.print("Enter Charging Station Name to Add (or press Enter to skip): ");
            String stationLocation = scanner.nextLine();
            if (!stationLocation.isEmpty()) {
                Optional<ChargingStation_VAO> station = chargingStationDAO.getChargingStationByLocation(stationLocation);
                if (station.isPresent()) {
                    List<String> updatedList = provider.getListOfStations();
                    updatedList.add(station.get().getLocation());
                    provider.setListOfStations(updatedList);
                    System.out.println("✅ Charging Station added to provider!");
                } else {
                    System.out.println("❌ Charging Station not found!");
                }
            }

            providerDAO.updateProvider(provider);
            System.out.println("✅ Provider updated!");
        } else {
            System.out.println("❌ Provider not found!");
        }
    }

    private static void deleteProvider() {
        System.out.print("Enter Provider Name to Delete: ");
        String name = scanner.nextLine();
        providerDAO.deleteProvider(name);
        System.out.println("✅ Provider deleted!");
    }

    private static void addChargingStation() {
        System.out.print("Enter Charging Station Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Connector Type (CHADEMO, CCS, TYPE2, TYPE1, DOMESTIC, TESLA): ");
        Connector_VAO connector = Connector_VAO.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter Provider Name: ");
        String providerName = scanner.nextLine();
        Optional<Provider_VAO> provider = providerDAO.getProviderByName(providerName);

        if (provider.isPresent()) {
            ChargingStation_VAO station = new ChargingStation_VAO(provider.get().getProviderName(), connector, location, true);
            chargingStationDAO.createChargingStation(station);
            provider.get().getListOfStations().add(station.getProviderName());
            System.out.println("✅ Charging Station added!");
        } else {
            System.out.println("❌ Provider not found!");
        }
    }

    private static void viewChargingStations() {
        System.out.println("\n🔋 Charging Stations:");
        chargingStationDAO.getChargingStations().forEach(station ->
                System.out.println("📍 Location: " + station.getLocation() +
                        " | Provider: " + station.getProviderName() +
                        " | Connector: " + station.getConnector() +
                        " | Available: " + (station.isAvailable() ? "Yes" : "No"))
        );
    }

    private static void updateChargingStation() {
        System.out.print("Enter Charging Station Location to Update: ");
        String location = scanner.nextLine();
        Optional<ChargingStation_VAO> existingStation = chargingStationDAO.getChargingStationByLocation(location);

        if (existingStation.isPresent()) {
            ChargingStation_VAO station = existingStation.get();
            System.out.print("Enter New Connector Type: ");
            station.setConnector(Connector_VAO.valueOf(scanner.nextLine().toUpperCase()));

            System.out.print("Is Available? (true/false): ");
            station.setAvailable(scanner.nextBoolean());
            scanner.nextLine();

            System.out.print("Enter New Provider Name (or press Enter to keep current): ");
            String newProviderName = scanner.nextLine();
            if (!newProviderName.isEmpty()) {
                Optional<Provider_VAO> newProvider = providerDAO.getProviderByName(newProviderName);
                if (newProvider.isPresent()) {
                    station.setProvider(newProvider.get().getProviderName());
                    System.out.println("✅ Provider updated!");
                } else {
                    System.out.println("❌ New provider not found! Keeping current provider.");
                }
            }

            System.out.print("Enter New Location (or press Enter to keep current): ");
            String newLocation = scanner.nextLine();
            if (!newLocation.isEmpty()) {
                station.setLocation(newLocation);
                System.out.println("✅ Location updated!");
            }

            chargingStationDAO.updateChargingStation(station);
            System.out.println("✅ Charging Station updated!");
        } else {
            System.out.println("❌ Charging Station not found!");
        }
    }

    private static void deleteChargingStation() {
        System.out.print("Enter Charging Station Location to Delete: ");
        String location = scanner.nextLine();
        chargingStationDAO.deleteChargingStation(location);
        System.out.println("✅ Charging Station deleted!");
    }

    private static void findProvider() {
        System.out.print("Enter Provider Name: ");
        String name = scanner.nextLine();
        Optional<Provider_VAO> provider = providerDAO.getProviderByName(name);
        provider.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("❌ Provider not found!")
        );
    }

    private static void findChargingStation() {
        System.out.print("Enter Charging Station Location: ");
        String location = scanner.nextLine();
        Optional<ChargingStation_VAO> station = chargingStationDAO.getChargingStationByLocation(location);
        station.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("❌ Charging Station not found!")
        );
    }
}


//import si.feri.dao.ChargingStation_DAO;
//import si.feri.dao.Provider_DAO;
//import si.feri.vao_model.ChargingStation_VAO;
//import si.feri.vao_model.Connector_VAO;
//import si.feri.vao_model.Provider_VAO;
//import si.feri.vao_model.Regions_VAO;
//
//public class Main {
//    public static void main(String[] args) {
//        ChargingStation_DAO chargingStationDAO = new ChargingStation_DAO();
//        Provider_DAO providerDAO = new Provider_DAO();
//
//        // Creating Providers
//        Provider_VAO provider1 = new Provider_VAO("Elektro Maribor", Regions_VAO.EUROPE);
//        Provider_VAO provider2 = new Provider_VAO("Petrol Slovenia", Regions_VAO.EUROPE);
//        Provider_VAO provider3 = new Provider_VAO("Tesla Superchargers", Regions_VAO.AMERICA);
//        Provider_VAO provider4 = new Provider_VAO("Shell Recharge", Regions_VAO.ASIA);
//        Provider_VAO provider5 = new Provider_VAO("Green Energy", Regions_VAO.EUROPE);
//
//        // Adding Providers to DAO
//        providerDAO.createProvider(provider1);
//        providerDAO.createProvider(provider2);
//        providerDAO.createProvider(provider3);
//        providerDAO.createProvider(provider4);
//        providerDAO.createProvider(provider5);
//
//        // Creating Charging Stations
//        ChargingStation_VAO station1 = new ChargingStation_VAO(provider1.getProviderName(), Connector_VAO.TYPE2, "Maribor - Center", true);
//        ChargingStation_VAO station2 = new ChargingStation_VAO(provider1.getProviderName(), Connector_VAO.CCS, "Ljubljana - BTC", false);
//        ChargingStation_VAO station3 = new ChargingStation_VAO(provider2.getProviderName(), Connector_VAO.CHADEMO, "Kranj - Main Road", true);
//        ChargingStation_VAO station4 = new ChargingStation_VAO(provider2.getProviderName(), Connector_VAO.TYPE1, "Celje - South", true);
//        ChargingStation_VAO station5 = new ChargingStation_VAO(provider3.getProviderName(), Connector_VAO.TESLA, "San Francisco - Market St.", true);
//        ChargingStation_VAO station6 = new ChargingStation_VAO(provider3.getProviderName(), Connector_VAO.CCS, "Los Angeles - Hollywood Blvd.", false);
//        ChargingStation_VAO station7 = new ChargingStation_VAO(provider4.getProviderName(), Connector_VAO.DOMESTIC, "Tokyo - Shibuya", true);
//        ChargingStation_VAO station8 = new ChargingStation_VAO(provider4.getProviderName(), Connector_VAO.TYPE2, "Shanghai - Pudong", false);
//        ChargingStation_VAO station9 = new ChargingStation_VAO(provider5.getProviderName(), Connector_VAO.TYPE1, "Vienna - City Center", true);
//        ChargingStation_VAO station10 = new ChargingStation_VAO(provider5.getProviderName(), Connector_VAO.CCS, "Berlin - Alexanderplatz", true);
//
//        // Adding Charging Stations to DAO
//        chargingStationDAO.createChargingStation(station1);
//        chargingStationDAO.createChargingStation(station2);
//        chargingStationDAO.createChargingStation(station3);
//        chargingStationDAO.createChargingStation(station4);
//        chargingStationDAO.createChargingStation(station5);
//        chargingStationDAO.createChargingStation(station6);
//        chargingStationDAO.createChargingStation(station7);
//        chargingStationDAO.createChargingStation(station8);
//        chargingStationDAO.createChargingStation(station9);
//        chargingStationDAO.createChargingStation(station10);
//
//        // Adding Charging Stations to providers
//        provider1.getListOfStations().add(station1.getProviderName());
//        provider1.getListOfStations().add(station2.getProviderName());
//        provider2.getListOfStations().add(station3.getProviderName());
//        provider2.getListOfStations().add(station4.getProviderName());
//        provider3.getListOfStations().add(station5.getProviderName());
//        provider3.getListOfStations().add(station6.getProviderName());
//        provider4.getListOfStations().add(station7.getProviderName());
//        provider4.getListOfStations().add(station8.getProviderName());
//        provider5.getListOfStations().add(station9.getProviderName());
//        provider5.getListOfStations().add(station10.getProviderName());
//
//        // Printing all providers
//        System.out.println("\n📌 Providers:");
//        providerDAO.getProviders().forEach(provider ->
//                System.out.println("🏢 " + provider.getProviderName() + " | Active Region: " + provider.getActiveRegion())
//        );
//
//        // Printing all charging stations
//        System.out.println("\n🔋 Charging Stations:");
//        chargingStationDAO.getChargingStations().forEach(station ->
//                System.out.println("📍 Location: " + station.getLocation() +
//                        " | Provider: " + station.getProviderName() +
//                        " | Connector: " + station.getConnector() +
//                        " | Available: " + (station.isAvailable() ? "Yes" : "No"))
//        );
//
//        // Updating Provider
//        System.out.println("\n🔄 Updating Provider 'Elektro Maribor' to 'Elektro Ljubljana'...");
//        Provider_VAO updatedProvider = new Provider_VAO("Elektro Ljubljana", Regions_VAO.EUROPE);
//        providerDAO.updateProvider(updatedProvider);
//
//        // Updating Charging Station Availability
//        System.out.println("\n🔄 Updating 'Maribor - Center' station availability to false...");
//        station1.setAvailable(false);
//        chargingStationDAO.updateChargingStation(station1);
//
//        // Fetching a Single Provider
//        System.out.println("\n🔍 Fetching Provider by Name: 'Elektro Maribor'");
//        providerDAO.getProviderByName("Elektro Maribor").ifPresent(System.out::println);
//
//        // Fetching a Single Charging Station
//        System.out.println("\n🔍 Fetching Charging Station by Location: 'Maribor - Center'");
//        chargingStationDAO.getChargingStationByLocation("Maribor - Center").ifPresent(System.out::println);
//
//        // Deleting a Provider
//        System.out.println("\n❌ Deleting Provider 'Elektro Ljubljana'...");
//        providerDAO.deleteProvider("Elektro Ljubljana");
//
//        // Deleting a Charging Station
//        System.out.println("\n❌ Deleting Charging Station 'Celje - South'...");
//        chargingStationDAO.deleteChargingStation("Celje - South");
//
//        // Printing all providers
//        System.out.println("\n📌 Providers:");
//        providerDAO.getProviders().forEach(provider ->
//                System.out.println("🏢 " + provider.getProviderName() + " | Active Region: " + provider.getActiveRegion())
//        );
//
//        // Printing all charging stations
//        System.out.println("\n🔋 Charging Stations:");
//        chargingStationDAO.getChargingStations().forEach(station ->
//                System.out.println("📍 Location: " + station.getLocation() +
//                        " | Provider: " + station.getProviderName() +
//                        " | Connector: " + station.getConnector() +
//                        " | Available: " + (station.isAvailable() ? "Yes" : "No"))
//        );
//    }
//}
//
