package si.um.feri.service.interfaces;

import si.um.feri.vao.UserVao;

import java.util.List;
import java.util.Optional;

public interface UserIService {
    void addUser(UserVao user);

    List<UserVao> getUsers();

    Optional<UserVao> getUserByEmail(String email);

    void updateUser(UserVao user);

    void deleteUser(String email);
}
