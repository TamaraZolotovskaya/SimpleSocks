package me.tamarazolotovskaya.simplesocks3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.tamarazolotovskaya.simplesocks3.model.SosksDto;
import me.tamarazolotovskaya.simplesocks3.services.FileService;
import me.tamarazolotovskaya.simplesocks3.services.SocksServise;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/socks")
@Tag(name = "Учет товаров на складе интернет-магазина носков", description = "CRUD-методы")

public class SocksController {
    private final SocksServise socksServise;
    private final FileService fileService;

    public SocksController(SocksServise socksServise, FileService fileService) {
        this.socksServise = socksServise;
        this.fileService = fileService;
    }

    @PostMapping
    @Operation(
            summary = "Приход товара на склад"
    )
    public ResponseEntity<?> addSocks(@RequestBody SosksDto sosksDto) {
        socksServise.addSocks(sosksDto);
        return ResponseEntity.ok(sosksDto);
    }

    @GetMapping
    @Operation(
            summary = "Возвращает количество носков на складе, соответствующих переданным в параметрах критериям"
    )
    public ResponseEntity<String> getSocks(@RequestParam String color,
                                           @RequestParam int size,
                                           @RequestParam(required = false, defaultValue = "0") int cottonMin,
                                           @RequestParam(required = false, defaultValue = "100") int cottonMax) {
        int quantity = socksServise.getSocks(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(Integer.toString(quantity));
    }

    @PutMapping
    @Operation(
            summary = " Отпуск носков со склада"
    )
    public ResponseEntity<?> giveSocks(@RequestBody SosksDto sosksDto) {
        socksServise.giveSocks(sosksDto);
        return ResponseEntity.ok(sosksDto);
    }

    @DeleteMapping
    @Operation(
            summary = "Списание бракованных носков"
    )
    public ResponseEntity<?> removeSocks(@RequestBody SosksDto sosksDto) {
        socksServise.giveSocks(sosksDto);
        return ResponseEntity.ok(sosksDto);
    }
}
