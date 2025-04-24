import si.feri.vao.vao_Station;
import si.feri.enums.enum_Connector;
import si.feri.vao.vao_Provider;
import si.feri.enums.enum_Region;
import si.feri.service.service_Provider;
import si.feri.service.service_Station;

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
        //  Creating Providers
        vao_Provider provider1 = new vao_Provider("Elektro Maribor", enum_Region.EUROPE);
        vao_Provider provider2 = new vao_Provider("Petrol Slovenia", enum_Region.EUROPE);
        vao_Provider provider3 = new vao_Provider("Tesla Superchargers", enum_Region.AMERICA);
        vao_Provider provider4 = new vao_Provider("Shell Recharge", enum_Region.ASIA);
        vao_Provider provider5 = new vao_Provider("Green Energy", enum_Region.EUROPE);
        provider.addProvider(provider1);
        provider.addProvider(provider2);
        provider.addProvider(provider3);
        provider.addProvider(provider4);
        provider.addProvider(provider5);

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
    private static void listProviders() {
        System.out.println("\nAvailable Providers:");
        try {
            provider.getProviders().forEach(provider -> System.out.println("- " + provider.getProviderName()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void listStations() {
        System.out.println("\nAvailable Stations:");
        try {
            station.getChargingStations().forEach(provider -> System.out.println("- " + provider.getLocation()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  View Providers and Stations
    private static void viewProviders() {
        System.out.println("\n📌 Providers:");

        try {
            provider.getProviders().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void viewStation() {
        System.out.println("\n🔋 Stations:");

        try {
            station.getChargingStations().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  Find Provider and Station
    private static void findProvider() {
        listProviders();
        System.out.print("Enter Provider Name: ");
        String input = scanner.nextLine();

        try {
            System.out.println(provider.getProviderByName(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void findStation() {
        listStations();
        System.out.print("Enter Station Location: ");
        String input = scanner.nextLine();

        try {
            System.out.println(station.getChargingStationByLocation(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //  Add Provider and Station
    private static void addProvider() {
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
    private static void addStation() {
        System.out.print("Enter Station Location: ");
        String locationInput = scanner.nextLine();

        try {
            System.out.print("Enter Connector Type " + Arrays.toString(enum_Connector.values()) + ": ");
            enum_Connector connectorInput = enum_Connector.valueOf(scanner.nextLine().toUpperCase());

            try {
                listProviders();
                System.out.print("\nEnter Provider Name: ");
                Optional<vao_Provider> providerInput = provider.getProviderByName(scanner.nextLine());

                if (providerInput.isEmpty()) {
                    throw new IllegalArgumentException("❌ Provider not found!");
                }
                vao_Station newStation = new vao_Station(providerInput.get(), connectorInput, locationInput, false);

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
        listProviders();
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

            listStations();
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
        listStations();
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

            listProviders();
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
        listProviders();
        System.out.print("Enter Provider Name to Delete: ");
        String input = scanner.nextLine();

        try {
            provider.deleteProvider(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void deleteStation() {
        listStations();
        System.out.print("Enter Station Location to Delete: ");
        String input = scanner.nextLine();

        try {
            station.deleteChargingStation(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}