package si.um.feri.beans;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.UserVao;
import java.io.Serializable;
import java.util.List;

@Named("showUsers")
@ViewScoped
public class ShowUsersBean implements Serializable {
    public ShowUsersBean() {
        selectedUser = new UserVao();
    }

    @EJB
    private UserIService userService;

    private String selectedEmail;
    private UserVao selectedUser;

    public String getSelectedEmail() {
        return selectedEmail;
    }
    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }
    public UserVao getSelectedUser() {
        return selectedUser;
    }
    public void setSelectedUser(UserVao selectedUser) {
        this.selectedUser = selectedUser;
    }
    public void setSelectedUser(String selectedEmail) {
        this.selectedUser = userService.getUserByEmail(selectedEmail).orElse(null);
    }

    //  CRUD
    public List<UserVao> getUsers() {
        return userService.getUsers();
    }

    public void updateUser() {
        if (selectedUser == null || selectedUser.getEmail() == null || selectedUser.getEmail().isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User", "User email is required."));
            return;
        }

        userService.updateUser(selectedUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Updated"));
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        PrimeFaces.current().ajax().update("mainForm:datatable-users");
    }

    public void removeUser() {
        userService.deleteUser(selectedEmail);
    }
}