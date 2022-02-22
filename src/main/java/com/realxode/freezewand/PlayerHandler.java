package com.realxode.freezewand;

import java.util.*;

public class PlayerHandler {

    private final Map<UUID, Integer> frozeneds;
    private final List<UUID> uuidFrozed = new ArrayList<>();

    public PlayerHandler() {
        frozeneds = new HashMap<>();
    }

    public Map<UUID, Integer> getFrozeneds() {
        return frozeneds;
    }

    public List<UUID> getUuidFrozed() {
        return uuidFrozed;
    }
}
