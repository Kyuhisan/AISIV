package si.feri.service;

import si.feri.dao.Interfaces.i_dao_User;
import si.feri.dao.dao_User;
import si.feri.vao.vao_User;

import java.util.List;
import java.util.Optional;

public class service_User {
    private final i_dao_User dao_user = dao_User.getInstance();

    //  create
    public void addUser(vao_User user) {
        if (dao_user.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("❌ User already exists!");
        } else {
            System.out.println("✅ User added!");
            dao_user.addUser(user);
        }
    }

    //  read
    public List<vao_User> getUsers() {
        if (dao_user.getUsers().isEmpty()) {
            throw new IllegalArgumentException("❌ No users found!");
        } else {
            System.out.println("✅ Users found!");
            return dao_user.getUsers();
        }
    }
    public Optional<vao_User> getUserByEmail(String email) {
        if (dao_user.getUserByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        } else {
            System.out.println("✅ User found!");
            return dao_user.getUserByEmail(email);
        }
    }

    //  update
    public void updateUser(vao_User user) {
        if (dao_user.getUserByEmail(user.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        } else {
            System.out.println("✅ Station updated!");
            dao_user.updateUser(user);
        }
    }

    //  delete
    public void deleteUser(String email) {
        if (dao_user.getUserByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        }
        Optional<vao_User> user = dao_user.getUserByEmail(email);

        if (user.isPresent()) {
            dao_user.deleteUserByEmail(email);
            System.out.println("✅ User deleted!");
        } else {
            throw new IllegalArgumentException("❌ User not found!");
        }
    }
}
