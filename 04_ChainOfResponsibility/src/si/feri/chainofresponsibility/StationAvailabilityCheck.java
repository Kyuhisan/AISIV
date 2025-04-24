package si.feri.chainofresponsibility;

import si.feri.vao.vao_Station;

public class StationAvailabilityCheck implements StationHandler {
    private StationHandler next;

    @Override
    public void setNextHandler(StationHandler next) {
        this.next = next;
    }

    @Override
    public boolean handleRequest(vao_Station station) {
        if (!station.isAvailable()) {
            System.out.println("❌ Station is not available.");
            return false;
        }
        System.out.println("✅ Station availability check passed.");
        return next == null || next.handleRequest(station);
    }
}

