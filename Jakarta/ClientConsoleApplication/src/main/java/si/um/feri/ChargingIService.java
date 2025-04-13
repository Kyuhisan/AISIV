package si.um.feri;

import jakarta.ejb.Remote;

@Remote
public interface ChargingIService {
    public String canCharge(String name, String location);
}
