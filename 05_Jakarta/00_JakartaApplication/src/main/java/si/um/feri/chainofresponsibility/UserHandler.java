package si.um.feri.chainofresponsibility;

import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;

public interface UserHandler {
    void setNextHandler(UserHandler next);
    boolean handleRequest(UserVao user, ChargingStationVao station);
}
