package si.feri.chainofresponsibility;

import si.feri.vao.vao_User;

public interface UserHandler {
    void setNextHandler(UserHandler next);
    boolean handleRequest(vao_User user);
}
