package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.ItemInventario;
import br.com.marcia.starwars.repository.ItemInventarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemInventarioService {

    private final ItemInventarioRepository itemInventarioRepository;

    private final ObjectMapper objectMapper;

    public List<ItemInventario> listar() {
        return itemInventarioRepository.findAll()
                .stream()
                .map(itemInventario -> objectMapper.convertValue(itemInventario, ItemInventario.class))
                .collect(Collectors.toList());
    }
}
