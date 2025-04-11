package repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private Map<Integer, Gate> gates;

    public GateRepository() {
        gates = new TreeMap<Integer, Gate>();
    }
    //checking for gate object by gate id
    public Optional<Gate> findById(int gateId) {
        if(gates.containsKey(gateId)) {
            return Optional.of(gates.get(gateId));
        }
        return Optional.empty();
    }

}
