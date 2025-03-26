package si.feri.chainofresponsibility;

import si.feri.vao.vao_Station;

public class StationAvailabilityCheck implements StationHandler {
    private StationHandler next;
    @Override
    public void setNextHandler(StationHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(vao_Station station) {
        if (station.isAvailable()) {
            System.out.println("Station is available for charging!");
            return;
        }
        if (next != null) {
            next.handleRequest(station);
        }
    }
}
