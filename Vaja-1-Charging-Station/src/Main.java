import si.feri.dao.dao_Provider;
import si.feri.dao.dao_Station;
import si.feri.vao.vao_Station;
import si.feri.enums.enum_Connector;
import si.feri.vao.vao_Provider;
import si.feri.enums.enum_Region;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final dao_Station dao_station = new dao_Station();
    private static final dao_Provider dao_Provider = new dao_Provider();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        while (true) {
            System.out.println("\n🔌 EV Charging Station System");
            System.out.println("1️⃣ View Providers");
            System.out.println("2️⃣ View Stations");
            System.out.println();
            System.out.println("3️⃣ Find Provider");
            System.out.println("4️⃣ Find Station");
            System.out.println();
            System.out.println("5️⃣ Add Provider ");
            System.out.println("6️⃣ Add Station");
            System.out.println();
            System.out.println("7️⃣ Update Provider");
            System.out.println("8️⃣ Update Station");
            System.out.println();
            System.out.println("9️⃣ Delete Provider");
            System.out.println("🔟 Delete Station");
            System.out.println();
            System.out.println("0️⃣ Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewProviders();
                case 2 -> viewStation();
                case 3 -> findProvider();
                case 4 -> findStation();
                case 5 -> addProvider();
                case 6 -> addStation();
                case 7 -> updateProvider();
                case 8 -> updateStation();
                case 9 -> deleteProvider();
                case 10 -> deleteStation();
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
        vao_Provider provider1 = new vao_Provider("Elektro Maribor", enum_Region.EUROPE);
        vao_Provider provider2 = new vao_Provider("Petrol Slovenia", enum_Region.EUROPE);
        vao_Provider provider3 = new vao_Provider("Tesla Superchargers", enum_Region.AMERICA);
        vao_Provider provider4 = new vao_Provider("Shell Recharge", enum_Region.ASIA);
        vao_Provider provider5 = new vao_Provider("Green Energy", enum_Region.EUROPE);

        //  Adding Providers to DAO
        dao_Provider.addProvider(provider1);
        dao_Provider.addProvider(provider2);
        dao_Provider.addProvider(provider3);
        dao_Provider.addProvider(provider4);
        dao_Provider.addProvider(provider5);

        //  Creating Charging Stations
        vao_Station station1 = new vao_Station(provider1, enum_Connector.TYPE2, "Maribor - Center", true);
        vao_Station station2 = new vao_Station(provider1, enum_Connector.CCS, "Ljubljana - BTC", false);
        vao_Station station3 = new vao_Station(provider2, enum_Connector.CHADEMO, "Kranj - Main Road", true);
        vao_Station station4 = new vao_Station(provider2, enum_Connector.TYPE1, "Celje - South", true);
        vao_Station station5 = new vao_Station(provider3, enum_Connector.TESLA, "San Francisco - Market St.", true);
        vao_Station station6 = new vao_Station(provider3, enum_Connector.CCS, "Los Angeles - Hollywood Blvd.", false);
        vao_Station station7 = new vao_Station(provider4, enum_Connector.DOMESTIC, "Tokyo - Shibuya", true);
        vao_Station station8 = new vao_Station(provider4, enum_Connector.TYPE2, "Shanghai - Pudong", false);
        vao_Station station9 = new vao_Station(provider5, enum_Connector.TYPE1, "Vienna - City Center", true);
        vao_Station station10 = new vao_Station(provider5, enum_Connector.CCS, "Berlin - Alexanderplatz", true);

        //  Adding Charging Stations to DAO
        dao_station.addChargingStation(station1);
        dao_station.addChargingStation(station2);
        dao_station.addChargingStation(station3);
        dao_station.addChargingStation(station4);
        dao_station.addChargingStation(station5);
        dao_station.addChargingStation(station6);
        dao_station.addChargingStation(station7);
        dao_station.addChargingStation(station8);
        dao_station.addChargingStation(station9);
        dao_station.addChargingStation(station10);

        //  Adding Charging Stations to providers
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
    }
    private static void listProviders() {
        System.out.println("\nAvailable Providers:");
        dao_Provider.getProviders().forEach(provider -> System.out.println("- " + provider.getProviderName()));
    }
    private static void listStations() {
        System.out.println("\nAvailable Stations:");
        dao_station.getChargingStations().forEach(provider -> System.out.println("- " + provider.getLocation()));
    }

    private static void viewProviders() {
        System.out.println("\n📌 Providers:");
        dao_Provider.getProviders().forEach(System.out::println);
    }
    private static void viewStation() {
        System.out.println("\n🔋 Stations:");
        dao_station.getChargingStations().forEach(System.out::println);
    }

    private static void findProvider() {
        listProviders();
        System.out.print("Enter Provider Name: ");
        String providerName = scanner.nextLine();

        dao_Provider.getProviderByName(providerName).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("❌ Provider not found!")
        );
    }
    private static void findStation() {
        listStations();
        System.out.print("Enter Station Location: ");
        String stationLocation = scanner.nextLine();

        dao_station.getChargingStationByLocation(stationLocation).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("❌ Station not found!")
        );
    }

    private static void addProvider() {
        System.out.print("Enter Provider Name: ");
        String name = scanner.nextLine();

        enum_Region region = null;
        while (region == null) {
            System.out.print("Enter Active Region " + Arrays.toString(enum_Region.values()) + ": ");
            String regionInput = scanner.nextLine().toUpperCase();
            try {
                region = enum_Region.valueOf(regionInput);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid region! Please enter a valid option.");
            }
        }

        vao_Provider provider = new vao_Provider(name, region);
        dao_Provider.addProvider(provider);
        System.out.println("✅ Provider added!");
    }
    private static void addStation() {
        System.out.print("Enter Station Location: ");
        String location = scanner.nextLine();

        enum_Connector connector = null;
        while (connector == null) {
            System.out.print("Enter Connector Type " + Arrays.toString(enum_Connector.values()) + ": ");
            String connectorInput = scanner.nextLine().toUpperCase();

            try {
                connector = enum_Connector.valueOf(connectorInput);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid connector type! Please enter a valid option.");
            }
        }

        if (dao_Provider.getProviders().isEmpty()) {
            System.out.println("❌ No providers available. Please add a provider first.");
            return;
        }

        listProviders();
        Optional<vao_Provider> provider = Optional.empty();
        while (provider.isEmpty()) {
            System.out.print("\nEnter Provider Name: ");
            String providerName = scanner.nextLine();
            provider = dao_Provider.getProviderByName(providerName);

            if (provider.isEmpty()) {
                System.out.println("❌ Provider not found! Please enter a valid provider from the list.");
            }
        }

        vao_Station station = new vao_Station(provider.get(), connector, location, true);
        dao_station.addChargingStation(station);
        provider.get().getListOfStations().add(station);
        System.out.println("✅ Station added!");
    }

    private static void updateProvider() {
        listProviders();
        System.out.print("Enter Provider Name to Update: ");
        String name = scanner.nextLine();
        Optional<vao_Provider> optionalProvider = dao_Provider.getProviderByName(name);

        if (optionalProvider.isPresent()) {
            vao_Provider provider = optionalProvider.get();

            System.out.print("Enter New Provider Name: ");
            provider.setProviderName(scanner.nextLine());

            enum_Region region = null;
            while (region == null) {
                System.out.print("Enter Active Region " + Arrays.toString(enum_Region.values()) + ": ");
                String regionInput = scanner.nextLine().toUpperCase();
                try {
                    region = enum_Region.valueOf(regionInput);
                    provider.setActiveRegion(region);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid region! Please enter a valid option.");
                }
            }

            listStations();
            System.out.print("Enter Station to Remove (or press Enter to skip): ");
            String stationLocation = scanner.nextLine();

            if (!stationLocation.isEmpty()) {
                Optional<vao_Station> station = provider.getListOfStations().stream()
                        .filter(s -> s.getLocation().equalsIgnoreCase(stationLocation))
                        .findFirst();

                if (station.isPresent()) {
                    provider.getListOfStations().remove(station.get());
                    System.out.println("✅ Charging Station removed from provider!");
                } else {
                    System.out.println("❌ Charging Station not found in this provider's list!");
                }
            }

            dao_Provider.updateProvider(provider);
            System.out.println("✅ Provider updated!");
        } else {
            System.out.println("❌ Provider not found!");
        }
    }

    private static void updateStation() {
        listStations();
        System.out.print("Enter Station Location to Update: ");
        String location = scanner.nextLine();
        Optional<vao_Station> optionalStation = dao_station.getChargingStationByLocation(location);

        if (optionalStation.isPresent()) {
            vao_Station station = optionalStation.get();
            vao_Provider oldProvider = station.getProvider();

            enum_Connector connector = null;
            while (connector == null) {
                System.out.print("Enter New Connector Type " + Arrays.toString(enum_Connector.values()) + ": ");
                String connectorInput = scanner.nextLine().toUpperCase();
                try {
                    connector = enum_Connector.valueOf(connectorInput);
                    station.setConnector(connector);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid connector type! Please enter a valid option.");
                }
            }

            Boolean isAvailable = null;
            while (isAvailable == null) {
                System.out.print("Is Available? (true/false): ");
                String availabilityInput = scanner.nextLine().toLowerCase();
                if (availabilityInput.equals("true") || availabilityInput.equals("false")) {
                    isAvailable = Boolean.parseBoolean(availabilityInput);
                    station.setAvailable(isAvailable);
                } else {
                    System.out.println("❌ Invalid input! Please enter 'true' or 'false'.");
                }
            }

            listProviders();
            System.out.print("Enter New Provider Name (or press Enter to keep current): ");
            String newProviderName = scanner.nextLine();
            if (!newProviderName.isEmpty()) {
                Optional<vao_Provider> newProvider = dao_Provider.getProviderByName(newProviderName);

                if (newProvider.isPresent()) {
                    vao_Provider newProviderObj = newProvider.get();

                    if (!newProviderObj.equals(oldProvider)) {
                        oldProvider.getListOfStations().remove(station);
                        newProviderObj.getListOfStations().add(station);
                        station.setProvider(newProviderObj);
                        System.out.println("✅ Provider updated!");
                    } else {
                        System.out.println("⚠️ Provider is the same, no changes made.");
                    }
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
            dao_station.updateChargingStation(station);
            dao_Provider.updateProvider(oldProvider);
            dao_Provider.updateProvider(station.getProvider());

            System.out.println("✅ Charging Station updated!");
        } else {
            System.out.println("❌ Charging Station not found!");
        }
    }


    private static void deleteProvider() {
        listProviders();
        System.out.print("Enter Provider Name to Delete: ");
        String name = scanner.nextLine();

        Optional<vao_Provider> provider = dao_Provider.getProviderByName(name);
        if (provider.isPresent()) {
            dao_Provider.deleteProvider(name);
            System.out.println("✅ Provider deleted!");
        } else {
            System.out.println("❌ Provider not found!");
        }
    }
    private static void deleteStation() {
        listStations();
        System.out.print("Enter Station Location to Delete: ");
        String location = scanner.nextLine();

        Optional<vao_Station> station = dao_station.getChargingStationByLocation(location);
        if (station.isPresent()) {
            dao_station.deleteChargingStation(location);
            System.out.println("✅ Station deleted!");
        } else {
            System.out.println("❌ Station not found!");
        }
    }
}