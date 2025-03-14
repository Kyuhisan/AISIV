package si.feri.observers;

import si.feri.vao.vao_Provider;
import si.feri.vao.vao_Station;

public interface observer_Station {
    void update(vao_Provider provider, vao_Station station, String action);
}
