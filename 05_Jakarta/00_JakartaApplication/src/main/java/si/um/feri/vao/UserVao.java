package si.um.feri.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.um.feri.enums.carTypeENUM;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Data
public class UserVao {
    @Id
    private String email;

    @Enumerated(EnumType.STRING)
    private carTypeENUM carType;

    private String name;
    private double balance;

    public UserVao(String name, String email, double balance, carTypeENUM carType) {
        this();
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "📍 UserVao: " + "\n" +
                "\t" + "Name: " + getName() + "\n" +
                "\t" + "Email: " + getEmail() + "\n" +
                "\t" + "Balance: $" + getBalance() + "\n" +
                "\t" + "CarType: " + getCarType() + "\n";
    }
}
