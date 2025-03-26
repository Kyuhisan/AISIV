package si.feri.vao;

import si.feri.enums.enum_Connector;
import si.feri.observers.observer_Provider;
import si.feri.observers.observer_Station;

import java.util.ArrayList;
import java.util.List;

public class vao_Station {
    //  variables
    private vao_Provider provider;
    private enum_Connector connector;
    private String location;
    private boolean isAvailable;
    private double chargingSpeed;
    private String currentUserEmail;
    private List<observer_Station> stationObservers = new ArrayList<>();

    //  constructors
    public vao_Station(vao_Provider provider, enum_Connector connector, String location, boolean isAvailable, double chargingSpeed) {
        this.provider = provider;
        this.connector = connector;
        this.location = location;
        this.isAvailable = isAvailable;
        this.chargingSpeed = chargingSpeed;
        this.currentUserEmail = "";
    }

    //  setters
    public void setProvider(vao_Provider provider) {
        this.provider = provider; }
    public void setConnector(enum_Connector connector) {
        this.connector = connector; }
    public void setLocation(String location) {
        this.location = location; }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable; }
    public void setChargingSpeed(double chargingSpeed) {
        this.chargingSpeed = chargingSpeed; }
    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail; }

    //  getters
    public vao_Provider getProvider() {
        return provider; }
    public enum_Connector getConnector() {
        return connector; }
    public String getLocation() {
        return location; }
    public boolean isAvailable() {
        return isAvailable; }
    public double getChargingSpeed() {
        return chargingSpeed; }
    public String getCurrentUserEmail() {
        return currentUserEmail; }

    //  observers
    public void addObserver(observer_Station observer) {
        stationObservers.add(observer);
    }
    public void removeObserver(observer_Station observer) {
        stationObservers.remove(observer);
    }
    public void notifyObservers(vao_Station station, String action) {
        for (observer_Station observer : stationObservers) {
            observer.update(provider, station, action);
        }
    }

    //  observer linked functions
    public void getStationsAvailabilityStatus(vao_Station station) {
        notifyObservers(station, "status");
    }
    public void startCharging(vao_Station station, String userEmail) {
        setCurrentUserEmail(userEmail);
        setAvailable(false);
        notifyObservers(station, "charging");
    }
    public void stopCharging(vao_Station station) {
        setCurrentUserEmail(null);
        setAvailable(true);
        notifyObservers(station, "stopped");
    }

    //  toString
    @Override
    public String toString() {
        return "📍 Station: " + "\n" +
                "\t" + "Location: " + getLocation() + "\n" +
                "\t" + "Connector: " + getConnector() + "\n" +
                "\t" + "Available: " + isAvailable() + "\n" +
                "\t" + "Provider: " + getProvider().getProviderName() + "\n" +
                "\t" + "Charging Speed: " + getChargingSpeed() + "kW." + "\n" +
                "\t" + "Active User: " + getCurrentUserEmail() + "\n";
    }
}
