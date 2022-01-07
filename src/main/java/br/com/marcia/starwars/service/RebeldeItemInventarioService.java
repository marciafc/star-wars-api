package br.com.marcia.starwars.service;

import br.com.marcia.starwars.repository.RebeldeItemInventarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RebeldeItemInventarioService {

    private final RebeldeItemInventarioRepository itemInventarioRepository;

    private final RebeldeService rebeldeService;

    public Double contabilizarPontosPerdidosPorTraidores() {
        return itemInventarioRepository.contabilizarPontosPerdidosPorTraidores();
    }

    public List<Object> calcularMediaRecursoPorRebelde() {
        return itemInventarioRepository.calcularMediaRecursoPorRebelde();
    }


}
