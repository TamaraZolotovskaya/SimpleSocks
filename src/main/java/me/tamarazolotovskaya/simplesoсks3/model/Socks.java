package me.tamarazolotovskaya.simplesoсks3.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tamarazolotovskaya.simplesoсks3.deserialization.SocksDeserialization;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = SocksDeserialization.class)
public class Socks {
    private  Color color;
    private  Size size;
    private int cottonPart;

    public Socks(String key) {
        try {
            Socks socks =
                    new ObjectMapper().readValue(key, Socks.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String toString() {
        return "Socks:{" +
                "colorName: " + color.getColorName() +
                ", sizeNumber: " + size.getSizeNumber() +
                ", cottonPart: " + cottonPart +
                '}';
    }
}
