package si.um.feri.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.dao.interfaces.UserIDao;
import si.um.feri.vao.UserVao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDao implements UserIDao {
    private final List<UserVao> listOfUsers = Collections.synchronizedList(new ArrayList<>());

    @PersistenceContext
    EntityManager em;

    //  create
    @Override
    public void addUser(UserVao user) {
        em.persist(user);
    }

    //  read
    @Override
    public List<UserVao> getUsers() {
        return em.createQuery("SELECT u FROM UserVao u", UserVao.class).getResultList();
    }
    @Override
    public Optional<UserVao> getUserByEmail(String email) {
        return em.createQuery("SELECT u FROM UserVao u WHERE u.email = :email", UserVao.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void updateUser(UserVao user) {
//        synchronized (listOfUsers) {
//            getUserByEmail(user.getEmail()).ifPresentOrElse(
//                    userToUpdate -> listOfUsers.set(listOfUsers.indexOf(userToUpdate), user),
//                    () -> System.out.println("❌ User not found!")
//            );
//        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        getUserByEmail(email).ifPresent(user -> {
            em.remove(em.contains(user) ? user : em.merge(user));
        });
    }
}
