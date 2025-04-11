package si.um.feri.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.service.UserService;
import si.um.feri.vao.UserVao;
import java.util.List;
import java.util.Optional;

@Named("showUsers")
@RequestScoped
public class ShowUsersBean {
    @Inject
    UserService userService;

    private String selectedEmail;

    public String getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    //  CRUD
    public List<UserVao> getUsers() {
        return userService.getUsers();
    }

    public void updateUser() {
        Optional<UserVao> selectedUser = userService.getUserByEmail(selectedEmail);

        if (selectedUser.isPresent()) {
            UserVao user = selectedUser.get();
            userService.updateUser(user);
        } else {
            System.out.println("User not found with email: " + selectedEmail);
        }
    }

    public void removeUser() {
        userService.deleteUser(selectedEmail);
    }
}


