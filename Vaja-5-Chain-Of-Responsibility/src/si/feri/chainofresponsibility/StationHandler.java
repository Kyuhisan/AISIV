package si.feri.chainofresponsibility;

import si.feri.vao.vao_Station;

public interface StationHandler {
    void setNextHandler(StationHandler next);
    boolean handleRequest(vao_Station station);
}

