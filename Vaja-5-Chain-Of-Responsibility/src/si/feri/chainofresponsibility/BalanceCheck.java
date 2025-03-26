package si.feri.chainofresponsibility;

import si.feri.vao.vao_User;

public class BalanceCheck implements UserHandler {
    private UserHandler next;

    @Override
    public void setNextHandler(UserHandler next) {
        this.next = next;
    }

    @Override
    public boolean handleRequest(vao_User user) {
        if (user.getBalance() < 0) {
            System.out.println("❌ User does not have sufficient balance.");
            return false;
        }
        System.out.println("✅ Balance check passed.");
        return next == null || next.handleRequest(user);
    }
}

