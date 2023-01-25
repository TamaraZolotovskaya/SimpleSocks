package me.tamarazolotovskaya.simplesoсks3.deserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.tamarazolotovskaya.simplesoсks3.model.Socks;

import java.util.Map;

public class ClassWithAMap {

    @JsonProperty("map")
    @JsonDeserialize(keyUsing = MySocksDeserializer.class)
    private Map<Socks, Integer> map;

    @JsonCreator
    public ClassWithAMap(Map<Socks, Integer> map) {
        this.map = map;
    }

    public Map<Socks, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Socks, Integer> map) {
        this.map = map;
    }
}

