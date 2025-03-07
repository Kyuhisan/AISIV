package si.feri.vao_model;

import java.util.ArrayList;
import java.util.List;

public class Provider_VAO {
    //variables
    private String providerName;
    private List<String> listOfStations;
    private Regions_VAO activeRegion;

    //constructors
    public Provider_VAO(String providerName, Regions_VAO activeRegion) {
        this.providerName = providerName;
        this.listOfStations = new ArrayList<>();
        this.activeRegion = activeRegion;
    }

    //getters
    public String getProviderName() { return providerName; }
    public List<String> getListOfStations() { return listOfStations; }
    public Regions_VAO getActiveRegion() { return activeRegion; }

    //setters
    public void setProviderName(String providerName) { this.providerName = providerName; }
    public void setActiveRegion(Regions_VAO activeRegion) { this.activeRegion = activeRegion; }
    public void setListOfStations(List<String> listOfStations) { this.listOfStations = listOfStations; }

    @Override
    public String toString() {
        return "Provider{" +
                "providerName='" + getProviderName() + '\'' +
                ", activeRegion=" + getActiveRegion() +
                ", listOfStations=" + getListOfStations() +
                '}';
    }

}