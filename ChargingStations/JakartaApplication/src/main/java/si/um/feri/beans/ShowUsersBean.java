package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.UserVao;
import java.util.List;

@Named("showUsers")
@RequestScoped
public class ShowUsersBean {
    @EJB
    private UserIService userService;

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

    public void removeUser() {
        userService.deleteUser(selectedEmail);
    }

    public void onCellEdit(CellEditEvent event) {
        UserVao edited = (UserVao) event.getRowData();

        try {
            userService.updateUser(edited);
            System.out.println("✅ User updated: " + edited.getEmail());
        } catch (Exception e) {
            System.err.println("❌ Error updating user: " + e.getMessage());
        }
    }
}