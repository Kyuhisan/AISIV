package si.um.feri.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.service.UserService;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.vao.ChargingStationVao;

@Path( "/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationResource {
    @EJB
    private ChargingStationIService stationService;

    @GET
    @Path("/")
    public String getAllStations() {
        return stationService.getChargingStations().toString();
    }

    @GET
    @Path("/{location}")
    public String getStationByLocation(@PathParam("location") String location) {
        return stationService.getChargingStationByLocation(location).toString();
    }

    @POST
    @Path("/")
    public void addStation(ChargingStationVao station) {
        stationService.addChargingStation(station);
    }
}
