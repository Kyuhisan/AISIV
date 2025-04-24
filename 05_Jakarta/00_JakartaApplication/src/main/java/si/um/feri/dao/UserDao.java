package si.um.feri.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.dao.interfaces.UserIDao;
import si.um.feri.vao.UserVao;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDao implements UserIDao {
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
        em.merge(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        getUserByEmail(email).ifPresent(user -> {
            em.remove(em.contains(user) ? user : em.merge(user));
        });
    }
}
