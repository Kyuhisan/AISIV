package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.UserIDao;
import si.um.feri.service.interfaces.ChargingStationIService;
import si.um.feri.service.interfaces.UserIService;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.UserVao;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserService implements Serializable, UserIService {
    @EJB
    private UserIDao dao_user;
    @EJB
    private ChargingStationIService chargingStationService;

    //  create
    public void addUser(UserVao user) {
        if (dao_user.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("❌ User already exists!");
        } else {
            System.out.println("✅ User added!");
            dao_user.addUser(user);
        }
    }

    //  read
    public List<UserVao> getUsers() {
        if (dao_user.getUsers().isEmpty()) {
            throw new IllegalArgumentException("❌ No users found!");
        } else {
            System.out.println("✅ Users found!");
            return dao_user.getUsers();
        }
    }
    public Optional<UserVao> getUserByEmail(String email) {
        if (dao_user.getUserByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        } else {
            System.out.println("✅ User found!");
            return dao_user.getUserByEmail(email);
        }
    }

    //  update
    public void updateUser(UserVao user) {
        if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("❌ Invalid user or missing email!");
        }

        if (dao_user.getUserByEmail(user.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        } else {
            System.out.println("✅ User updated!");
            dao_user.updateUser(user);
        }
    }


    //  delete
    public void deleteUser(String email) {
        if (dao_user.getUserByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("❌ User not found!");
        }
        Optional<UserVao> user = dao_user.getUserByEmail(email);

        if (user.isPresent()) {
            List<ChargingStationVao> stations = chargingStationService.getChargingStations();
            for (ChargingStationVao station : stations) {
                if (station.getCurrentUserEmail() != null &&
                        station.getCurrentUserEmail().equalsIgnoreCase(email)) {
                    station.setCurrentUserEmail(null);
                    chargingStationService.updateChargingStation(station);
                }
            }

            dao_user.deleteUserByEmail(email);
            System.out.println("✅ User deleted!");
        } else {
            throw new IllegalArgumentException("❌ User not found!");
        }
    }
}
