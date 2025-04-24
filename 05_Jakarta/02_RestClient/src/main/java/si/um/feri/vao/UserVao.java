package si.um.feri.vao;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.um.feri.enums.carTypeENUM;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserVao {
    private String email;
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
}
