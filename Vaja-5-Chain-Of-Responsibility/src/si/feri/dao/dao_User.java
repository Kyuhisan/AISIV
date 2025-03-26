package si.feri.dao;

import si.feri.dao.Interfaces.i_dao_User;
import si.feri.vao.vao_User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class dao_User implements i_dao_User {
    private final List<vao_User> listOfUsers = Collections.synchronizedList(new ArrayList<>());
    private static volatile  dao_User instance;

    private dao_User() {}

    public static dao_User getInstance() {
        if (instance == null) {
            synchronized (dao_User.class) {
                if (instance == null) {
                    instance = new dao_User();
                }
            }
        }
        return instance;
    }

    //  create
    @Override
    public void addUser(vao_User user) {
        synchronized (listOfUsers) {
            listOfUsers.add(user);
        }
    }

    //  read
    @Override
    public List<vao_User> getUsers() {
        synchronized (listOfUsers) {
            return listOfUsers;
        }
    }
    @Override
    public Optional<vao_User> getUserByEmail(String email) {
        synchronized (listOfUsers) {
            return listOfUsers.stream().filter(user -> user.getEmail().equals(email)).findFirst();
        }
    }

    @Override
    public void updateUser(vao_User user) {
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
