package si.um.feri.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.vao.UserVao;
import java.util.List;
import java.util.Optional;

@Local
public interface UserIDao {
    //  create
    void addUser(UserVao user);

    //  read
    List<UserVao> getUsers();
    Optional<UserVao> getUserByEmail(String email);

    //  update
    void updateUser(UserVao user);

    //  delete
    void deleteUserByEmail(String email);
}
