package me.tamarazolotovskaya.simplesoсks3.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import me.tamarazolotovskaya.simplesoсks3.model.Socks;
import java.io.IOException;

public class MySocksDeserializer extends KeyDeserializer {

        @Override
        public Socks deserializeKey(
                String key,
                DeserializationContext ctxt) throws IOException,
                JsonProcessingException {

            return new Socks(key);
        }
    }



