package si.um.feri;

import jakarta.ejb.Remote;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

@Remote
public interface ChargingIService {
    boolean startCharging(UserVao user, ChargingStationVao station);
    void stopCharging(UserVao user, ChargingStationVao station);
    public String canCharge(String name, String location);
}
