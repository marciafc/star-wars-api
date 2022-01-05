package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.interfaces.IItemInventarioControllerDocs;
import br.com.marcia.starwars.api.v1.response.ItemInventarioResponse;
import br.com.marcia.starwars.service.ItemInventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/itens-inventario")
@AllArgsConstructor
public class ItemInventarioController implements IItemInventarioControllerDocs {

    private final ItemInventarioService itemInventarioService;

    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<ItemInventarioResponse>> listar() {

        return ResponseEntity.ok().body(itemInventarioService.listar()
                .stream()
                .map(itemInventario -> objectMapper.convertValue(itemInventario, ItemInventarioResponse.class))
                .collect(Collectors.toList()));
    }
}
