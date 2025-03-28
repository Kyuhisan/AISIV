package si.um.feri.dao;

import si.um.feri.dao.interfaces.UserIDao;
import si.um.feri.vao.UserVao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao implements UserIDao {
    private final List<UserVao> listOfUsers = Collections.synchronizedList(new ArrayList<>());
    private static volatile UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addUser(UserVao user) {
        synchronized (listOfUsers) {
            listOfUsers.add(user);
        }
    }

    //  read
    @Override
    public List<UserVao> getUsers() {
        synchronized (listOfUsers) {
            return listOfUsers;
        }
    }
    @Override
    public Optional<UserVao> getUserByEmail(String email) {
        synchronized (listOfUsers) {
            return listOfUsers.stream().filter(user -> user.getEmail().equals(email)).findFirst();
        }
    }

    @Override
    public void updateUser(UserVao user) {
        synchronized (listOfUsers) {
            getUserByEmail(user.getEmail()).ifPresentOrElse(
                    userToUpdate -> listOfUsers.set(listOfUsers.indexOf(userToUpdate), user),
                    () -> System.out.println("❌ User not found!")
            );
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        synchronized (listOfUsers) {
            listOfUsers.removeIf(user -> user.getEmail().equals(email));
        }
    }
}
