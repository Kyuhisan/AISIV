package si.feri.vao_model;

import java.util.UUID;

public class ChargingStation_VAO {
    // variables
    private String providerName;
    private Connector_VAO connectorVAO;
    private String location;
    private boolean isAvailable;

    // constructors
    public ChargingStation_VAO(String providerName, Connector_VAO connectorVAO, String location, boolean isAvailable) {
        this.providerName = providerName;
        this.connectorVAO = connectorVAO;
        this.location = location;
        this.isAvailable = isAvailable;
    }

    // setters
    public void setProvider(String providerName) { this.providerName = providerName; }
    public void setConnector(Connector_VAO connectorVAO) { this.connectorVAO = connectorVAO; }
    public void setLocation(String location) { this.location = location; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    // getters
    public String getProviderName() { return providerName; }
    public Connector_VAO getConnector() { return connectorVAO; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return isAvailable; }

    @Override
    public String toString() {
        return "ChargingStation{" +
                "location='" + location + '\'' +
                ", connector=" + connectorVAO +
                ", available=" + isAvailable +
                ", providerName=" + providerName +
                '}';
    }

}
