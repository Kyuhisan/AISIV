package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public interface observer_Provider {
    void update(vao_Provider provider, vao_Station station, String action);
}
