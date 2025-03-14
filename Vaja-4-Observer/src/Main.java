import si.feri.observers.observer_ProviderAddStation;
import si.feri.observers.observer_ProviderRemoveStation;
import si.feri.vao.vao_Station;
import si.feri.enums.enum_Connector;
import si.feri.vao.vao_Provider;
import si.feri.enums.enum_Region;
import si.feri.service.service_Provider;
import si.feri.service.service_Station;
import si.feri.iterators.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static service_Provider provider = new service_Provider();
    static service_Station station = new service_Station();

    public static void main(String[] args) {
        initializeData();
        while (true) {
            System.out.println("\n🔌 EV Charging Station System");
            System.out.println("1. Show - All Providers");
            System.out.println("2. Show - All Stations");
            System.out.println();
            System.out.println("3. Show - Specific Provider");
            System.out.println("4. Show - Specific Station");
            System.out.println();
            System.out.println("5. Create - Provider");
            System.out.println("6. Create - Station");
            System.out.println();
            System.out.println("7. Update - Provider");
            System.out.println("8. Update - Station");
            System.out.println();
            System.out.println("9. Delete - Provider");
            System.out.println("10. Delete - Station");
            System.out.println("Iterators");
            System.out.println("11. Show - Stations of Specific Provider");
            System.out.println("12. Show - Stations Based on Connector Type");
            System.out.println("13. Show - Active Stations");
            System.out.println("14. Show - Stations With Charging Speed Above");
            System.out.println("15. Show - Stations In a Region");
            System.out.println("16. Show - All Stations Ordered");
            System.out.println("Observers");
            System.out.println("17. Add - Station to Provider");
            System.out.println("18. Remove - Station from Provider");
            System.out.println("19. Start Charging");
            System.out.println("20. Stop Charging");
            System.out.println();
            System.out.println("0️⃣ Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> listAllProviders();
                case 2 -> listAllStations();
                case 3 -> findProvider();
                case 4 -> findStation();
                case 5 -> createProvider();
                case 6 -> createStation();
                case 7 -> updateProvider();
                case 8 -> updateStation();
                case 9 -> deleteProvider();
                case 10 -> deleteStation();
                case 11 -> listStationNamesOfProvider();
                case 12 -> listStationNamesOfProviderByType();
                case 13 -> listActiveStationsOfProvider();
                case 14 -> listStationNamesBySpeedOfProvider();
                case 15 -> listStationNamesByRegion();
                case 16 -> listAllStationsInOrder();
                case 17 -> addStation();
                case 18 -> removeStation();
                case 19 -> startCharging();
                case 20 -> stopCharging();
                case 0 -> {
                    System.out.println("🚪 Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    //  Initial Setup
    private static void initializeData() {
        //  Creating Providers
        vao_Provider provider1 = new vao_Provider("Elektro Maribor", enum_Region.EUROPE);
        vao_Provider provider2 = new vao_Provider("Petrol Slovenia", enum_Region.EUROPE);
        vao_Provider provider3 = new vao_Provider("Tesla Superchargers", enum_Region.AMERICA);
        vao_Provider provider4 = new vao_Provider("Shell Recharge", enum_Region.ASIA);
        vao_Provider provider5 = new vao_Provider("Green Energy", enum_Region.EUROPE);

        //  Initialize observers
        provider1.addObserver(new observer_ProviderAddStation());
        provider1.addObserver(new observer_ProviderRemoveStation());
        provider2.addObserver(new observer_ProviderAddStation());
        provider2.addObserver(new observer_ProviderRemoveStation());
        provider3.addObserver(new observer_ProviderAddStation());
        provider3.addObserver(new observer_ProviderRemoveStation());
        provider4.addObserver(new observer_ProviderAddStation());
        provider4.addObserver(new observer_ProviderRemoveStation());
        provider5.addObserver(new observer_ProviderAddStation());
        provider5.addObserver(new observer_ProviderRemoveStation());

        provider.addProvider(provider1);
        provider.addProvider(provider2);
        provider.addProvider(provider3);
        provider.addProvider(provider4);
        provider.addProvider(provider5);

        //  Creating Charging Stations
        vao_Station station1 = new vao_Station(provider1, enum_Connector.TYPE2, "Maribor - Center", true, 50.2);
        vao_Station station2 = new vao_Station(provider1, enum_Connector.CCS, "Ljubljana - BTC", false, 22.5);
        vao_Station station3 = new vao_Station(provider2, enum_Connector.CHADEMO, "Kranj - Main Road", true, 15.0);
        vao_Station station4 = new vao_Station(provider2, enum_Connector.TYPE1, "Celje - South", true, 77.0);
        vao_Station station5 = new vao_Station(provider3, enum_Connector.TESLA, "San Francisco - Market St.", true, 12.5);
        vao_Station station6 = new vao_Station(provider3, enum_Connector.CCS, "Los Angeles - Hollywood Blvd.", false, 22.5);
        vao_Station station7 = new vao_Station(provider4, enum_Connector.DOMESTIC, "Tokyo - Shibuya", true, 34.44);
        vao_Station station8 = new vao_Station(provider4, enum_Connector.TYPE2, "Shanghai - Pudong", false, 50.2);
        vao_Station station9 = new vao_Station(provider5, enum_Connector.TYPE1, "Vienna - City Center", true, 77.0);
        vao_Station station10 = new vao_Station(provider5, enum_Connector.CCS, "Berlin - Alexanderplatz", true, 22.5);

        station.addChargingStation(station1);
        station.addChargingStation(station2);
        station.addChargingStation(station3);
        station.addChargingStation(station4);
        station.addChargingStation(station5);
        station.addChargingStation(station6);
        station.addChargingStation(station7);
        station.addChargingStation(station8);
        station.addChargingStation(station9);
        station.addChargingStation(station10);

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
    private static void listProviderNames() {
        System.out.println("\nAvailable Providers:");
        try {
            iterator_Provider allProviders = new iterator_Provider(provider.getProviders());

            while (allProviders.hasNext()) {
                System.out.println(allProviders.next().getProviderName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void listStationNames() {
        System.out.println("\nAvailable Stations:");
        try {
            iterator_StationsAll allStations = new iterator_StationsAll(provider.getProviders());

            while (allStations.hasNext()) {
                System.out.println(allStations.next().getLocation());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  View Providers and Stations
    private static void listAllProviders() {
        iterator_Provider allProviders = new iterator_Provider(provider.getProviders());

        while (allProviders.hasNext()) {
            System.out.println(allProviders.next());
        }
    }
    private static void listAllStations() {
        iterator_StationsAll allStations = new iterator_StationsAll(provider.getProviders());

        while (allStations.hasNext()) {
            System.out.println(allStations.next());
        }
    }

    //  Iterators
    private static void listStationNamesOfProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String input = scanner.nextLine();

        provider.getProviderByName(input).ifPresent(providerName -> {
            iterator_Station stationIterator = new iterator_Station(providerName.getListOfStations());

            while (stationIterator.hasNext()) {
                System.out.println(stationIterator.next());
            }
        });
    }
    private static void listStationNamesOfProviderByType() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();

        System.out.println("Enter Connector Type: " + Arrays.toString(enum_Connector.values()));
        enum_Connector connectorTypeInput = enum_Connector.valueOf(scanner.nextLine().toUpperCase());

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            iterator_StationByConnectorType connectorType = new iterator_StationByConnectorType(providerName.getListOfStations(), connectorTypeInput);

            while (connectorType.hasNext()) {
                System.out.println(connectorType.next());
            }
        });
    }
    private static void listActiveStationsOfProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            iterator_StationsActive isActive = new iterator_StationsActive(providerName.getListOfStations(), true);

            while (isActive.hasNext()) {
                System.out.println(isActive.next());
            }
        });
    }
    private static void listStationNamesBySpeedOfProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();

        System.out.print("Enter Charging Speed: ");
        double speedInput = Double.parseDouble(scanner.nextLine());

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            iterator_StationsBySpeed bySpeed = new iterator_StationsBySpeed(providerName.getListOfStations(), speedInput);

            while (bySpeed.hasNext()) {
                System.out.println(bySpeed.next());
            }
        });
    }
    private static void listStationNamesByRegion() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();


        System.out.print("Enter Active Region " + Arrays.toString(enum_Region.values()) + ": ");
        enum_Region regionInput = enum_Region.valueOf(scanner.nextLine());

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            iterator_StationsByRegion byRegion = new iterator_StationsByRegion(providerName.getListOfStations(), regionInput);

            while (byRegion.hasNext()) {
                System.out.println(byRegion.next());
            }
        });
    }
    private static void listAllStationsInOrder() {
        iterator_StationsAllOrdered allStations = new iterator_StationsAllOrdered(provider.getProviders());

        while (allStations.hasNext()) {
            System.out.println(allStations.next());
        }
    }

    //  Find Provider and Station
    private static void findProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String input = scanner.nextLine();

        try {
            System.out.println(provider.getProviderByName(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void findStation() {
        listStationNames();
        System.out.print("Enter Station Location: ");
        String input = scanner.nextLine();

        try {
            System.out.println(station.getChargingStationByLocation(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  Add Provider and Station
    private static void createProvider() {
        System.out.print("Enter Provider Name: ");
        String nameInput = scanner.nextLine();

        try {
            System.out.print("Enter Active Region " + Arrays.toString(enum_Region.values()) + ": ");
            enum_Region regionInput = enum_Region.valueOf(scanner.nextLine().toUpperCase());
            vao_Provider newProvider = new vao_Provider(nameInput, regionInput);

            try {
                provider.addProvider(newProvider);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid region! Please enter a valid option.");
        }
    }
    private static void createStation() {
        System.out.print("Enter Station Location: ");
        String locationInput = scanner.nextLine();

        try {
            System.out.print("Enter Connector Type " + Arrays.toString(enum_Connector.values()) + ": ");
            enum_Connector connectorInput = enum_Connector.valueOf(scanner.nextLine().toUpperCase());

            try {
                listProviderNames();
                System.out.print("\nEnter Provider Name: ");
                Optional<vao_Provider> providerInput = provider.getProviderByName(scanner.nextLine());
                System.out.print("\nEnter Charging Speed: ");
                double chargingSpeed = Double.parseDouble(scanner.nextLine());

                if (providerInput.isEmpty()) {
                    throw new IllegalArgumentException("❌ Provider not found!");
                }
                vao_Station newStation = new vao_Station(providerInput.get(), connectorInput, locationInput, false, chargingSpeed);

                try {
                    station.addChargingStation(newStation);
                    providerInput.get().getListOfStations().add(newStation);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Provider not valid! Please enter a valid provider from the list.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid connector type! Please enter a valid option.");
        }
    }

    //  Update Provider and Station
    private static void updateProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name to Update: ");
        String nameInput = scanner.nextLine();
        Optional<vao_Provider> optionalProvider = provider.getProviderByName(nameInput);

        if (optionalProvider.isPresent()) {
            vao_Provider newProvider = optionalProvider.get();

            System.out.print("Enter New Provider Name: ");
            newProvider.setProviderName(scanner.nextLine());

            enum_Region region = null;
            while (region == null) {
                System.out.print("Enter Active Region " + Arrays.toString(enum_Region.values()) + ": ");
                String regionInput = scanner.nextLine().toUpperCase();
                try {
                    region = enum_Region.valueOf(regionInput);
                    newProvider.setActiveRegion(region);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid region! Please enter a valid option.");
                }
            }

            listStationNames();
            System.out.print("Enter Station to Add (or press Enter to skip): ");
            String stationLocation = scanner.nextLine();
            if (!stationLocation.isEmpty()) {
                Optional<vao_Station> newStation = station.getChargingStationByLocation(stationLocation);

                if (newStation.isPresent()) {
                    List<vao_Station> updatedList = newProvider.getListOfStations();
                    updatedList.add(newStation.get());
                    newProvider.setListOfStations(updatedList);
                    System.out.println("✅ Charging Station added to provider!");
                } else {
                    System.out.println("❌ Charging Station not found!");
                }
            }

            System.out.println("\nAvailable Stations:");
            newProvider.getListOfStations().forEach(provider -> System.out.println("- " + provider.getLocation()));
            System.out.print("Enter Station to Remove (or press Enter to skip): ");
            stationLocation = scanner.nextLine();

            if (!stationLocation.isEmpty()) {
                String finalStationLocation = stationLocation;
                Optional<vao_Station> station = newProvider.getListOfStations().stream()
                        .filter(s -> s.getLocation().equalsIgnoreCase(finalStationLocation))
                        .findFirst();

                if (station.isPresent()) {
                    newProvider.getListOfStations().remove(station.get());
                    System.out.println("✅ Charging Station removed from provider!");
                } else {
                    System.out.println("❌ Charging Station not found in this provider's list!");
                }
            }

            try {
                provider.updateProvider(newProvider);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("❌ Provider not found!");
        }
    }
    private static void updateStation() {
        listStationNames();
        System.out.print("Enter Station Location to Update: ");
        String location = scanner.nextLine();
        Optional<vao_Station> optionalStation = station.getChargingStationByLocation(location);

        if (optionalStation.isPresent()) {
            vao_Station newStation = optionalStation.get();
            vao_Provider oldProvider = newStation.getProvider();

            enum_Connector connector = null;
            while (connector == null) {
                System.out.print("Enter New Connector Type " + Arrays.toString(enum_Connector.values()) + ": ");
                String connectorInput = scanner.nextLine().toUpperCase();
                try {
                    connector = enum_Connector.valueOf(connectorInput);
                    newStation.setConnector(connector);
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
                    newStation.setAvailable(isAvailable);
                } else {
                    System.out.println("❌ Invalid input! Please enter 'true' or 'false'.");
                }
            }

            listProviderNames();
            System.out.print("Enter New Provider Name (or press Enter to keep current): ");
            String newProviderName = scanner.nextLine();
            if (!newProviderName.isEmpty()) {
                Optional<vao_Provider> newProvider = provider.getProviderByName(newProviderName);

                if (newProvider.isPresent()) {
                    vao_Provider newProviderObj = newProvider.get();

                    if (!newProviderObj.equals(oldProvider)) {
                        oldProvider.getListOfStations().remove(newStation);
                        newProviderObj.getListOfStations().add(newStation);
                        newStation.setProvider(newProviderObj);
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
                newStation.setLocation(newLocation);
                System.out.println("✅ Location updated!");
            }
            station.updateChargingStation(newStation);
            provider.updateProvider(oldProvider);
            provider.updateProvider(newStation.getProvider());

            System.out.println("✅ Charging Station updated!");
        } else {
            System.out.println("❌ Charging Station not found!");
        }
    }

    //  Delete Provider and Station
    private static void deleteProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name to Delete: ");
        String input = scanner.nextLine();

        try {
            provider.deleteProvider(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void deleteStation() {
        listStationNames();
        System.out.print("Enter Station Location to Delete: ");
        String input = scanner.nextLine();

        try {
            station.deleteChargingStation(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  Adding and removing stations to Provider's list of stations
    private static void addStation() {
        try {
            listProviderNames();
            System.out.print("\nEnter Provider Name: ");
            Optional<vao_Provider> providerInput = provider.getProviderByName(scanner.nextLine());

            System.out.println("\nAvailable Stations:");
            try {
                iterator_StationsAll allStations = new iterator_StationsAll(provider.getProviders());
                while (allStations.hasNext()) {
                    System.out.println(allStations.next().getLocation());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            Optional<vao_Station> stationInput = station.getChargingStationByLocation(scanner.nextLine());
            providerInput.ifPresent(providerName -> {
                stationInput.ifPresent(providerName::addStation);
            });
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Provider not valid! Please enter a valid provider from the list.");
        }
    }
    private static void removeStation() {
        try {
            listProviderNames();
            System.out.print("\nEnter Provider Name: ");
            Optional<vao_Provider> providerInput = provider.getProviderByName(scanner.nextLine());

            System.out.println("\nAvailable Stations:");
            providerInput.ifPresent(providerName -> {
                providerName.getListOfStations().forEach(provider -> System.out.println("- " + provider.getLocation()));
                Optional<vao_Station> stationInput = station.getChargingStationByLocation(scanner.nextLine());

                stationInput.ifPresent(providerName::removeStation);
            });
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Provider not valid! Please enter a valid provider from the list.");
        }
    }

    private static void startCharging() {
        listAllStations();
        System.out.print("\nEnter Charging Station: ");
        Optional<vao_Station> stationInput = station.getChargingStationByLocation(scanner.nextLine());

        stationInput.ifPresent(station -> {
            stationInput.get().setAvailable(false);
            stationInput.get().setCurrentUserEmail("currentUser1@gmail.com");
        });
    }
    private static void stopCharging() {
        listAllStations();
        System.out.print("\nEnter Charging Station: ");
        Optional<vao_Station> stationInput = station.getChargingStationByLocation(scanner.nextLine());

        stationInput.ifPresent(station -> {
            if (!station.isAvailable()) {
                stationInput.get().setAvailable(true);
                stationInput.get().setCurrentUserEmail(null);
            }
        });
    }
}