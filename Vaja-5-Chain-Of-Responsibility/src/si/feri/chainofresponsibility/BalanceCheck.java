package si.feri.chainofresponsibility;

import si.feri.vao.vao_User;

public class BalanceCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(vao_User user) {
        if (user.getBalance() >= 0) {
            System.out.println("User's balance is sufficient!");
            return;
        }
        if (next != null) {
            next.handleRequest(user);
        }
    }
}
