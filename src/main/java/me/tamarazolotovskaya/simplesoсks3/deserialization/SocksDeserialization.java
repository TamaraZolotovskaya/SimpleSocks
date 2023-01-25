package me.tamarazolotovskaya.simplesoсks3.deserialization;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import me.tamarazolotovskaya.simplesoсks3.model.Color;
import me.tamarazolotovskaya.simplesoсks3.model.Size;
import me.tamarazolotovskaya.simplesoсks3.model.Socks;
import java.io.IOException;


public class SocksDeserialization extends StdDeserializer<Socks> {


    protected SocksDeserialization(Class<?> vc) {
        super(vc);
    }

    @Override
    public Socks deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int cottonPart = (Integer) ((IntNode) node.get("cottonPart")).numberValue();
        int sizeNumber = (Integer) ((IntNode) node.get("sizeNumber")).numberValue();
        String colorName = node.get("colorName").asText();
        Color color = null;
        Size size = null;
        for (Color c : Color.values()) {
            if (c.getColorName().equals(colorName)) {
                color = c;
                break;
            }
        }
        for (Size s : Size.values()) {
            if (s.getSizeNumber() == sizeNumber) {
                size = s;
                break;
            }
        }
        return new Socks(color, size, cottonPart);
    }



}