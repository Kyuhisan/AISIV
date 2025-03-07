package si.feri.vao;

import si.feri.enums.enum_Connector;

public class vao_Station {
    //  variables
    private vao_Provider provider;
    private enum_Connector connector;
    private String location;
    private boolean isAvailable;

    //  constructors
    public vao_Station(vao_Provider provider, enum_Connector connector, String location, boolean isAvailable) {
        this.provider = provider;
        this.connector = connector;
        this.location = location;
        this.isAvailable = isAvailable;
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

    //  getters
    public vao_Provider getProvider() {
        return provider; }
    public enum_Connector getConnector() {
        return connector; }
    public String getLocation() {
        return location; }
    public boolean isAvailable() {
        return isAvailable; }

    //  toString
    @Override
    public String toString() {
        return "📍 Station: " + "\n" +
                "\t" + "Location: " + getLocation() + "\n" +
                "\t" + "Connector: " + getConnector() + "\n" +
                "\t" + "Available: " + isAvailable() + "\n" +
                "\t" + "Provider: " + getProvider().getProviderName() + "\n";
    }
}
