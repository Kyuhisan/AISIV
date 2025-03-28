package si.um.feri;

import si.um.feri.enums.carTypeENUM;
import si.um.feri.enums.connectorENUM;
import si.um.feri.enums.regionENUM;
import si.um.feri.iterators.*;
import si.um.feri.observers.ProviderStationStatus_Notifier;
import si.um.feri.observers.StationsAvailabilityStatus_Notifier;
import si.um.feri.observers.UserCharging_Notifier;
import si.um.feri.service.ChargingService;
import si.um.feri.service.ChargingStationService;
import si.um.feri.service.ProviderService;
import si.um.feri.service.UserService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import si.um.feri.vao.UserVao;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static ProviderService provider = new ProviderService();
    static ChargingStationService station = new ChargingStationService();
    static UserService user = new UserService();
    static ChargingService chargingService = new ChargingService();

    public static void main(String[] args) {
        initializeData();
        while (true) {
            System.out.println("\n🔌 EV Charging Station System");
            System.out.println("1. Show - All Providers");
            System.out.println("2. Show - All Stations");
            System.out.println("3. Show - All Users");
            System.out.println();
            System.out.println("4. Show - Specific Provider");
            System.out.println("5. Show - Specific Station");
            System.out.println("6. Show - Specific User");
            System.out.println();
            System.out.println("7. Add - Provider");
            System.out.println("8. Add - Station");
            System.out.println("9. Add - User");
            System.out.println();
            System.out.println("10. Update - Provider");
            System.out.println("11. Update - Station");
            System.out.println("12. Update - User");
            System.out.println();
            System.out.println("13. Delete - Provider");
            System.out.println("14. Delete - Station");
            System.out.println("15. Delete - User");
            System.out.println();
            System.out.println("16. Show - Stations of Specific Provider");
            System.out.println("17. Show - Stations Based on Connector Type");
            System.out.println("18. Show - Users Based on Car Type");
            System.out.println();
            System.out.println("19. Show - Active Stations");
            System.out.println("20. Show - Stations With Charging Speed Above");
            System.out.println("21. Show - Users With Positive Balance");
            System.out.println();
            System.out.println("22. Show - Stations In a Region");
            System.out.println("23. Show - All Stations Ordered");
            System.out.println("24. Show - All Users Ordered");
            System.out.println();
            System.out.println("25. Start Charging");
            System.out.println("26. Stop Charging");
            System.out.println("27. Show Charging Station Availability");

            System.out.println("0️⃣ Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> listAllProviders();
                case 2 -> listAllStations();
                case 3 -> listAllUsers();

                case 4 -> findProvider();
                case 5 -> findStation();
                case 6 -> findUser();

                case 7 -> addProvider();
                case 8 -> addStation();
                case 9 -> addUser();

                case 10 -> updateProvider();
                case 11 -> updateStation();
                case 12 -> updateUser();

                case 13 -> deleteProvider();
                case 14 -> deleteStation();
                case 15 -> deleteUser();

                case 16 -> listStationNamesOfProvider();
                case 17 -> listStationNamesOfProviderByType();
                case 18 -> listUsersByCarType();

                case 19 -> listActiveStationsOfProvider();
                case 20 -> listStationNamesBySpeedOfProvider();
                case 21 -> listUsersWithPositiveBalance();

                case 22 -> listStationNamesByRegion();
                case 23 -> listAllStationsInOrder();
                case 24 -> listUsersInOrder();

                case 25 -> startCharging();
                case 26 -> stopCharging();
                case 27 -> showAllStationsAvailability();
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

        UserVao user1 = new UserVao("Matic Kuhar", "positiveNonElectric@gmail.com", 125.22, carTypeENUM.GAS);
        UserVao user2 = new UserVao("Peter Kotnik", "negativeElectric@gmail.com", -25.2, carTypeENUM.ELECTRIC);
        UserVao user3 = new UserVao("Janez Pleče", "zeroElectric@gmail.com", 0, carTypeENUM.ELECTRIC);
        UserVao user4 = new UserVao("Aleš Kokot", "zeroNonElectric@gmail.com", 0, carTypeENUM.DIESEL);
        UserVao user5 = new UserVao("Nejc Petrič", "positiveElectric@gmail.com", 241.22, carTypeENUM.ELECTRIC);
        user.addUser(user1);
        user.addUser(user2);
        user.addUser(user3);
        user.addUser(user4);
        user.addUser(user5);
    }
    private static void listProviderNames() {
        System.out.println("\nAvailable Providers:");
        try {
            ProviderIterator allProviders = new ProviderIterator(provider.getProviders());

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
            ChargingStationsAllIterator allStations = new ChargingStationsAllIterator(provider.getProviders());

            while (allStations.hasNext()) {
                System.out.println(allStations.next().getLocation());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAllProviders() {
        ProviderIterator allProviders = new ProviderIterator(provider.getProviders());

        while (allProviders.hasNext()) {
            System.out.println(allProviders.next());
        }
    }
    private static void listAllStations() {
        ChargingStationsAllIterator allStations = new ChargingStationsAllIterator(provider.getProviders());

        while (allStations.hasNext()) {
            System.out.println(allStations.next());
        }
    }
    private static void listAllUsers() {
        UsersAllIterator allUsers = new UsersAllIterator(user.getUsers());

        while (allUsers.hasNext()) {
            System.out.println(allUsers.next());
        }
    }

    private static void listStationNamesOfProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String input = scanner.nextLine();

        provider.getProviderByName(input).ifPresent(providerName -> {
            ChargingStationIterator stationIterator = new ChargingStationIterator(providerName.getListOfStations());

            while (stationIterator.hasNext()) {
                System.out.println(stationIterator.next());
            }
        });
    }
    private static void listStationNamesOfProviderByType() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();

        System.out.println("Enter Connector Type: " + Arrays.toString(connectorENUM.values()));
        connectorENUM connectorTypeInput = connectorENUM.valueOf(scanner.nextLine().toUpperCase());

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            ChargingStationByConnectorTypeIterator connectorType = new ChargingStationByConnectorTypeIterator(providerName.getListOfStations(), connectorTypeInput);

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
            ChargingStationsActiveIterator isActive = new ChargingStationsActiveIterator(providerName.getListOfStations(), true);

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
            ChargingStationsBySpeedIterator bySpeed = new ChargingStationsBySpeedIterator(providerName.getListOfStations(), speedInput);

            while (bySpeed.hasNext()) {
                System.out.println(bySpeed.next());
            }
        });
    }
    private static void listStationNamesByRegion() {
        listProviderNames();
        System.out.print("Enter Provider Name: ");
        String providerInput = scanner.nextLine();


        System.out.print("Enter Active Region " + Arrays.toString(regionENUM.values()) + ": ");
        regionENUM regionInput = regionENUM.valueOf(scanner.nextLine());

        provider.getProviderByName(providerInput).ifPresent(providerName -> {
            ChargingStationsByRegionIterator byRegion = new ChargingStationsByRegionIterator(providerName.getListOfStations(), regionInput);

            while (byRegion.hasNext()) {
                System.out.println(byRegion.next());
            }
        });
    }
    private static void listAllStationsInOrder() {
        ChargingStationsAllOrderedIterator allStations = new ChargingStationsAllOrderedIterator(provider.getProviders());

        while (allStations.hasNext()) {
            System.out.println(allStations.next());
        }
    }
    private static void listUsersByCarType() {
        System.out.print("Enter Car Type: " + Arrays.toString(carTypeENUM.values()) + ": ");
        carTypeENUM carTypeInput = carTypeENUM.valueOf(scanner.nextLine());

        UsersByCarTypeIterator byCarType = new UsersByCarTypeIterator(user.getUsers(), carTypeInput);

        while (byCarType.hasNext()) {
            System.out.println(byCarType.next());
        }
    }
    private static void listUsersWithPositiveBalance() {
        UsersWithBalanceIterator byBalance = new UsersWithBalanceIterator(user.getUsers());

        while (byBalance.hasNext()) {
            System.out.println(byBalance.next());
        }
    }
    private static void listUsersInOrder() {
        UsersAllOrderedIterator allUsers = new UsersAllOrderedIterator(user.getUsers());

        while (allUsers.hasNext()) {
            System.out.println(allUsers.next());
        }
    }

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
    private static void findUser() {
        System.out.print("Enter User's Email: ");
        String input = scanner.nextLine();

        try {
            System.out.println(user.getUserByEmail(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addProvider() {
        System.out.print("Enter Provider Name: ");
        String nameInput = scanner.nextLine();

        try {
            System.out.print("Enter Active Region " + Arrays.toString(regionENUM.values()) + ": ");
            regionENUM regionInput = regionENUM.valueOf(scanner.nextLine().toUpperCase());
            ProviderVao newProvider = new ProviderVao(nameInput, regionInput);

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
            System.out.print("Enter Connector Type " + Arrays.toString(connectorENUM.values()) + ": ");
            connectorENUM connectorInput = connectorENUM.valueOf(scanner.nextLine().toUpperCase());

            try {
                listProviderNames();
                System.out.print("\nEnter Provider Name: ");
                Optional<ProviderVao> providerInput = provider.getProviderByName(scanner.nextLine());
                System.out.print("\nEnter Charging Speed: ");
                double chargingSpeed = Double.parseDouble(scanner.nextLine());

                if (providerInput.isEmpty()) {
                    throw new IllegalArgumentException("❌ Provider not found!");
                }
                ChargingStationVao newStation = new ChargingStationVao(providerInput.get(), connectorInput, locationInput, false, chargingSpeed);

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
    private static void addUser() {
        System.out.print("Enter User Name: ");
        String nameInput = scanner.nextLine();
        System.out.print("Enter Email: ");
        String emailInput = scanner.nextLine();
        System.out.print("Enter Balance: ");
        double balanceInput = Double.parseDouble(scanner.nextLine());

        try {
            System.out.print("Enter Car Type " + Arrays.toString(carTypeENUM.values()) + ": ");
            carTypeENUM carTypeInput = carTypeENUM.valueOf(scanner.nextLine().toUpperCase());
            UserVao newUser = new UserVao(nameInput, emailInput, balanceInput, carTypeInput);


            try {
                user.addUser(newUser);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid car type! Please enter a valid option.");
        }
    }

    private static void updateProvider() {
        listProviderNames();
        System.out.print("Enter Provider Name to Update: ");
        String nameInput = scanner.nextLine();
        Optional<ProviderVao> optionalProvider = provider.getProviderByName(nameInput);

        if (optionalProvider.isPresent()) {
            ProviderVao newProvider = optionalProvider.get();

            System.out.print("Enter New Provider Name: ");
            newProvider.setProviderName(scanner.nextLine());

            regionENUM region = null;
            while (region == null) {
                System.out.print("Enter Active Region " + Arrays.toString(regionENUM.values()) + ": ");
                String regionInput = scanner.nextLine().toUpperCase();
                try {
                    region = regionENUM.valueOf(regionInput);
                    newProvider.setActiveRegion(region);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ Invalid region! Please enter a valid option.");
                }
            }

            listStationNames();
            System.out.print("Enter Station to Add (or press Enter to skip): ");
            String stationLocation = scanner.nextLine();
            if (!stationLocation.isEmpty()) {
                Optional<ChargingStationVao> newStation = station.getChargingStationByLocation(stationLocation);

                if (newStation.isPresent()) {
                    List<ChargingStationVao> updatedList = newProvider.getListOfStations();
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
                Optional<ChargingStationVao> station = newProvider.getListOfStations().stream()
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
        Optional<ChargingStationVao> optionalStation = station.getChargingStationByLocation(location);

        if (optionalStation.isPresent()) {
            ChargingStationVao newStation = optionalStation.get();
            ProviderVao oldProvider = newStation.getProviderVao();

            connectorENUM connector = null;
            while (connector == null) {
                System.out.print("Enter New Connector Type " + Arrays.toString(connectorENUM.values()) + ": ");
                String connectorInput = scanner.nextLine().toUpperCase();
                try {
                    connector = connectorENUM.valueOf(connectorInput);
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
                Optional<ProviderVao> newProvider = provider.getProviderByName(newProviderName);

                if (newProvider.isPresent()) {
                    ProviderVao newProviderObj = newProvider.get();

                    if (!newProviderObj.equals(oldProvider)) {
                        oldProvider.getListOfStations().remove(newStation);
                        newProviderObj.getListOfStations().add(newStation);
                        newStation.setProviderVao(newProviderObj);
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
            provider.updateProvider(newStation.getProviderVao());

            System.out.println("✅ Charging Station updated!");
        } else {
            System.out.println("❌ Charging Station not found!");
        }
    }
    private static void updateUser() {

    }

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
    private static void deleteUser() {
        System.out.print("Enter User's Email to Delete: ");
        String input = scanner.nextLine();

        try {
            user.deleteUser(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showAllStationsAvailability() {
        ChargingStationsAllIterator allStations = new ChargingStationsAllIterator(provider.getProviders());
        ChargingStationVao currentStation;

        while (allStations.hasNext()) {
            currentStation = allStations.next();
            currentStation.addObserver(new StationsAvailabilityStatus_Notifier());
            currentStation.getStationsAvailabilityStatus(currentStation);
        }
    }
    private static void startCharging() {
        listStationNames();
        System.out.print("Enter Station Location: ");
        String location = scanner.nextLine();

        Optional<ChargingStationVao> optionalStation = station.getChargingStationByLocation(location);

        if (optionalStation.isPresent()) {
            ChargingStationVao currentStation = optionalStation.get();

            System.out.print("Enter User Email: ");
            String userEmail = scanner.nextLine();

            Optional<UserVao> optionalUser = user.getUserByEmail(userEmail);

            if (optionalUser.isPresent()) {
                UserVao currentUser = optionalUser.get();

                currentStation.setCurrentUserEmail(userEmail);
                currentStation.addObserver(new UserCharging_Notifier());
                currentStation.addObserver(new ProviderStationStatus_Notifier());

                chargingService.startCharging(currentUser, currentStation);
            } else {
                System.out.println("❌ User not found!");
            }
        } else {
            System.out.println("❌ Charging Station not found!");
        }
    }

    private static void stopCharging() {
        System.out.print("Enter User Email: ");
        String userEmail = scanner.nextLine();

        for (ProviderVao provider : provider.getProviders()) {
            ChargingStationByEmailIterator stationIterator = new ChargingStationByEmailIterator(provider.getListOfStations(), userEmail);

            if (stationIterator.hasNext()) {
                ChargingStationVao myStation = stationIterator.next();
                Optional<UserVao> optionalUser = user.getUserByEmail(userEmail);

                if (optionalUser.isPresent()) {
                    UserVao currentUser = optionalUser.get();

                    myStation.addObserver(new UserCharging_Notifier());
                    myStation.addObserver(new ProviderStationStatus_Notifier());

                    chargingService.stopCharging(currentUser, myStation);
                    myStation.setCurrentUserEmail(null);
                    return;
                } else {
                    System.out.println("❌ User not found!");
                    return;
                }
            }
        }
        System.out.println("❌ No active charging session found for " + userEmail);
    }
}