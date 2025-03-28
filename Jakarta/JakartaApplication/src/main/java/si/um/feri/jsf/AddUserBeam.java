package si.um.feri.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.carTypeENUM;
import si.um.feri.service.UserService;
import si.um.feri.vao.UserVao;
import java.io.Serializable;

@Named("addUser")
@SessionScoped
@Getter
@Setter
public class AddUserBeam implements Serializable {
    private UserVao user = new UserVao();

    @Inject
    private UserService userService;

    public void addUser() {
        userService.addUser(user);
        System.out.println("✅ User added: " + user);
        user = new UserVao(); // Reset form
    }

    public carTypeENUM[] getCarTypes() {
        return carTypeENUM.values();
    }
}
