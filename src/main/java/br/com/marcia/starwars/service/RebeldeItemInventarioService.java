package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventarioNegociar;
import br.com.marcia.starwars.repository.RebeldeItemInventarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RebeldeItemInventarioService {

    private final RebeldeItemInventarioRepository itemInventarioRepository;

    private final RebeldeInventarioService rebeldeInventarioService;

    public Double contabilizarPontosPerdidosPorTraidores() {
        return itemInventarioRepository.contabilizarPontosPerdidosPorTraidores();
    }

    public List<Object> calcularMediaRecursoPorRebelde() {
        return itemInventarioRepository.calcularMediaRecursoPorRebelde();
    }

    // verificar se o rebeldeOrigem tem os itens acessiveis
    // se a qtde que oferece para troca, se ele tem

    // verificar se o rebeldeDestino tem os itens acessiveis
    // se a qtde que oferece para troca, se ele tem

    // verificar se a pontuacao eh equivalente

    public Rebelde negociarItens(Long rebeldeIdOrigem, RebeldeItemInventarioNegociar itensNegociar) {

        RebeldeInventario rebeldeInventarioOrigem = rebeldeInventarioService.buscarPorRebeldeId(rebeldeIdOrigem);
        if(!rebeldeInventarioOrigem.getAcessivel()) {
            throw new IllegalArgumentException("");// criar exception de negócio
        }

        RebeldeInventario rebeldeInventarioDestino = rebeldeInventarioService.buscarPorRebeldeId(itensNegociar.getRebeldeIdDestino());
        if(!rebeldeInventarioDestino.getAcessivel()) {
            throw new IllegalArgumentException("");// criar exception de negócio
        }

        //rebeldeInventarioOrigem.getItemsInventario().stream()

        return new Rebelde();
    }

}
