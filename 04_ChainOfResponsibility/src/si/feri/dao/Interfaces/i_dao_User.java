package si.feri.dao.Interfaces;

import si.feri.vao.vao_User;

import java.util.List;
import java.util.Optional;

public interface i_dao_User {
    //  create
    void addUser(vao_User user);

    //  read
    List<vao_User> getUsers();
    Optional<vao_User> getUserByEmail(String email);

    //  update
    void updateUser(vao_User user);

    //  delete
    void deleteUserByEmail(String email);
}
