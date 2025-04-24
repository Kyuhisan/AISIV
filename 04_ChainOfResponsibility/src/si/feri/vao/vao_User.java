package si.feri.vao;

import si.feri.enums.enum_CarType;

public class vao_User {
    private String name;
    private String email;
    private double balance;
    private enum_CarType carType;

    //  constructors
    public vao_User(String name, String email, double balance, enum_CarType carType) {
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.carType = carType;
    }

    //  setters
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setCarType(enum_CarType carType) {
        this.carType = carType;
    }

    //  getters
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public double getBalance() {
        return balance;
    }
    public enum_CarType getCarType() {
        return carType;
    }

    //  toString

    @Override
    public String toString() {
        return "📍 User: " + "\n" +
                "\t" + "Name: " + getName() + "\n" +
                "\t" + "Email: " + getEmail() + "\n" +
                "\t" + "Balance: $" + getBalance() + "\n" +
                "\t" + "CarType: " + getCarType() + "\n";
    }
}
