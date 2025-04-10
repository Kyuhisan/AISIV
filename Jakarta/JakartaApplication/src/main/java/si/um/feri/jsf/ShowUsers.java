package si.um.feri.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.UserService;
import si.um.feri.vao.UserVao;
import java.util.List;

@Named("showUsers")
@RequestScoped
public class ShowUsers {
    @Inject
    UserService userService;

    public List<UserVao> getUsers() {
        return userService.getUsers();
    }
}


