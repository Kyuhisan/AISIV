package si.um.feri.vao;

import lombok.Getter;
import lombok.Setter;
import si.um.feri.enums.carTypeENUM;

@Getter
@Setter
public class UserVao {
    private String name;
    private String email;
    private double balance;
    private carTypeENUM carType;

    //  constructors
    public UserVao() {

    }
    public UserVao(String name, String email, double balance, carTypeENUM carType) {
        this();
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.carType = carType;
    }

    //  toString
    @Override
    public String toString() {
        return "📍 UserVao: " + "\n" +
                "\t" + "Name: " + getName() + "\n" +
                "\t" + "Email: " + getEmail() + "\n" +
                "\t" + "Balance: $" + getBalance() + "\n" +
                "\t" + "CarType: " + getCarType() + "\n";
    }
}
