package si.um.feri.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.UserVao;

@Path( "/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @EJB
    private UserIService userService;

    @GET
    @Path("/")
    public String getAllUsers() {
        return userService.getUsers().toString();
    }

    @GET
    @Path("/{email}")
    public String getUserByEmail(@PathParam("email") String email) {
        return userService.getUserByEmail(email).toString();
    }

    @POST
    @Path("/")
    public void addUser(UserVao user) {
        userService.addUser(user);
    }
}
