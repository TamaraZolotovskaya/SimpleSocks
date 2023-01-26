package me.tamarazolotovskaya.simplesoсks3.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.tamarazolotovskaya.simplesoсks3.exeption.IncorrectParamExeption;
import me.tamarazolotovskaya.simplesoсks3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServise {
    @Value("${name.of.socks.data.file}")
    private String socksDataFileName;
    private final FileService fileService;

    public SocksServise(FileService fileService) {
        this.fileService = fileService;
    }

    private static Map<Socks, Integer> socksMap = new HashMap<>();


   /* @PostConstruct
    private void init() {
        String json = fileService.readFromFile(socksDataFileName);
        try {
            socksMap =
                    new ObjectMapper().readValue(json, new TypeReference<Map<Socks, Integer>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }*/


    private boolean ValidateSocks(Socks socks, Integer quantity) {
        if (quantity < 0 || socks.getCottonPart() < 0 || socks.getCottonPart() > 100)
            return false;
        else return true;
    }

    public void addSocks(SosksDto sosksDto) {
        Socks socks = sosksDto.getSocks();
        Integer quantity = sosksDto.getQuantity();
        if (ValidateSocks(socks, quantity)) {
            if (socksMap.containsKey(socks)) {
                socksMap.replace(socks, socksMap.get(socks) + quantity);
            } else {
                socksMap.put(socks, quantity);
            }
            fileService.saveToJsonFile(socksMap, socksDataFileName);
        } else
            throw new IncorrectParamExeption();
    }

    public void giveSocks(SosksDto sosksDto) {
        Socks socks = sosksDto.getSocks();
        Integer quantity = sosksDto.getQuantity();
        if (ValidateSocks(socks, quantity) &&
                socksMap.containsKey(socks) &&
                socksMap.get(socks) >= quantity) {
            socksMap.replace(socks, socksMap.get(socks) - quantity);
            fileService.saveToJsonFile(socksMap, socksDataFileName);
        } else throw new IncorrectParamExeption();
    }

    public int getSocks(String colorName, int sizeNumber, int cottonMin, int cottonMax) {
        Color color = Color.parse(colorName);
        Size size = Size.parse(sizeNumber);
        if (color != null && size != null && cottonMin >= 0 && cottonMax <= 100 && cottonMin < cottonMax) {
            int quantity = 0;
            for (Socks socks :
                    socksMap.keySet()) {
                if (socks.getColor().equals(color) &&
                        socks.getSize().equals(size) &&
                        socks.getCottonPart() >= cottonMin &&
                        socks.getCottonPart() <= cottonMax) {
                    quantity = quantity + socksMap.get(socks);
                }
            }
            return quantity;
        } else throw new IncorrectParamExeption();
    }
}



