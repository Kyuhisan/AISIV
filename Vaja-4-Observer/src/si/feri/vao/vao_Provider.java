package si.feri.vao;

import si.feri.enums.enum_Region;
import si.feri.observers.observer_Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class vao_Provider {
    //  variables
    private String providerName;
    private List<vao_Station> listOfStations;
    private enum_Region activeRegion;
    private final List<observer_Provider> providerObservers = new ArrayList<>();

    //  constructors
    public vao_Provider(String providerName, enum_Region activeRegion) {
        this.providerName = providerName;
        this.listOfStations = new ArrayList<>();
        this.activeRegion = activeRegion;
    }

    //  getters
    public String getProviderName() {
        return providerName; }
    public List<vao_Station> getListOfStations() {
        return listOfStations; }
    public enum_Region getActiveRegion() {
        return activeRegion; }

    //  setters
    public void setProviderName(String providerName) {
        this.providerName = providerName; }
    public void setActiveRegion(enum_Region activeRegion) {
        this.activeRegion = activeRegion; }
    public void setListOfStations(List<vao_Station> listOfStations) {
        this.listOfStations = listOfStations; }

    //  adding / removing methods
    public void addStation(vao_Station station) {
        listOfStations.add(station);
        notifyObservers(station, "added");
    }

    public void removeStation(vao_Station station) {
        listOfStations.remove(station);
        notifyObservers(station, "removed");
    }

    //  observers
    public void addObserver(observer_Provider observer) {
        providerObservers.add(observer); }
    public void removeObserver(observer_Provider observer) {
        providerObservers.remove(observer); }
    public void notifyObservers(vao_Station station, String action) {
        for (observer_Provider observer : providerObservers) {
            observer.update(this, station, action);
        }
    }

    //  toString
    @Override
    public String toString() {
        return "🏢 Provider: " + "\n" +
                "\t" + "Provider Name: " + getProviderName() + "\n" +
                "\t" + "Active Region: " + getActiveRegion() + "\n" +
                "\t" + "Stations: " + "\n" + getListOfStations().stream().map(vao_Station::toString).collect(Collectors.joining());
    }
}