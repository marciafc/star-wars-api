package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventarioNegociar;
import br.com.marcia.starwars.exception.RebeldeInventarioNaoAcessivelException;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
import br.com.marcia.starwars.exception.TrocaInvalidaItemRebeldeException;
import br.com.marcia.starwars.exception.ValorInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RebeldeItemInventarioNegociacaoService {

    private final RebeldeService rebeldeService;

    public Rebelde negociarItens(Long rebeldeIdOrigem, RebeldeItemInventarioNegociar itensNegociar) {

        Rebelde rebeldeOrigem = rebeldeService.buscar(rebeldeIdOrigem);

        Rebelde rebeldeDestino;
        try {
            rebeldeDestino = rebeldeService.buscar(itensNegociar.getRebeldeIdDestino());
        } catch (RebeldeNaoEncontradoException exception) {
            throw new ValorInvalidoException( String.format("Não existe rebelde com id %d", itensNegociar.getRebeldeIdDestino()));
        }

        RebeldeInventario inventarioOrigem = rebeldeOrigem.getRebeldeInventario();
        RebeldeInventario inventarioDestino = rebeldeDestino.getRebeldeInventario();

        validarInventariosSeAcessiveis(inventarioOrigem, inventarioDestino, rebeldeOrigem.getId(), rebeldeDestino.getId());
        validarTrocaItens(itensNegociar, rebeldeOrigem, rebeldeDestino);

        return new Rebelde();
    }

    private void validarTrocaItens(RebeldeItemInventarioNegociar itensNegociar,
                                   Rebelde rebeldeOrigem,
                                   Rebelde rebeldeDestino) {

        Map<Long, Long> itensNegociarOrigem = itensNegociar.getRebeldeOrigemItems().stream().
                collect(Collectors.toMap(RebeldeItemInventario::getId, RebeldeItemInventario::getQuantidade));

        Map<Long, Long> itensNegociarDestino = itensNegociar.getRebeldeDestinoItems().stream().
                collect(Collectors.toMap(RebeldeItemInventario::getId, RebeldeItemInventario::getQuantidade));

        Map<Long, Long> pontuacaoPorItemIdOrigem = validarItemQuantidade(itensNegociarOrigem,  rebeldeOrigem);
        Map<Long, Long> pontuacaoPorItemIdDestino = validarItemQuantidade(itensNegociarDestino,  rebeldeDestino);

        validarPontuacao(rebeldeOrigem.getId(), rebeldeDestino.getId(), pontuacaoPorItemIdOrigem, pontuacaoPorItemIdDestino);
    }

    /**
     * Validando se pontuação: ambos os lados deverão oferecer a mesma quantidade de pontos. Exemplo:
     *
     *  1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3)
     *
     * @param pontuacaoPorItemIdOrigem
     * @param pontuacaoPorItemIdDestino
     */
    private void validarPontuacao(Long rebeldeIdOrigem,
                                  Long rebeldeIdDestino,
                                  Map<Long, Long> pontuacaoPorItemIdOrigem,
                                  Map<Long, Long> pontuacaoPorItemIdDestino) {

        Long totalPontosRebeldeOrigem = pontuacaoPorItemIdOrigem.values().stream()
                .collect(Collectors.summingLong(Long::longValue));

        Long totalPontosRebeldeDestino = pontuacaoPorItemIdDestino.values().stream()
                .collect(Collectors.summingLong(Long::longValue));

        if(totalPontosRebeldeOrigem < totalPontosRebeldeDestino || totalPontosRebeldeDestino < totalPontosRebeldeOrigem) {
            throw new TrocaInvalidaItemRebeldeException(
                    String.format("A troca não é válida. Ambos os rebeldes deverão oferecer a mesma quantidade em pontos. " +
                    "O rebelde %d está oferecendo %d pontos e o rebelde %d o total de %d pontos", rebeldeIdOrigem, totalPontosRebeldeOrigem, rebeldeIdDestino, totalPontosRebeldeDestino));
        }
    }

    /**
     * Para cada item da lista da negociação, validar se tem o item com aquele id e possui a quantidade solicitada para troca.
     *
     * @param itensNegociar lista da negociação
     * @param rebelde
     * @return um Map contendo como key, o id do item e no value quantos pontos gastará na troca do mesmo
     *
     */
    private Map validarItemQuantidade(Map<Long, Long> itensNegociar, Rebelde rebelde) {
        Map pontuacaoPorItemId = new HashMap();

        List<RebeldeItemInventario> itemsInventario = rebelde.getRebeldeInventario().getItemsInventario();

        itensNegociar.forEach((id, quantidade) -> {

            Optional<RebeldeItemInventario> item = itemsInventario.stream().
                    filter(i -> i.getItemInventario().getId() == id && i.getQuantidade() >= quantidade).findFirst();

            if(!item.isPresent()) {
                throw new TrocaInvalidaItemRebeldeException(
                        String.format("O rebelde %d não possui %d item(ns) do item com id %d", rebelde.getId(), quantidade, id));
            }

            Long pontuacaoPorItem = item.get().getItemInventario().getPontuacao() * quantidade;

            pontuacaoPorItemId.put(item.get().getItemInventario().getId(), pontuacaoPorItem);
        });

        return pontuacaoPorItemId;
    }

    /**
     * A troca só é permitida se ambos os rebeldes estiverem com os itens acessíveis.
     */
    private void validarInventariosSeAcessiveis(RebeldeInventario rebeldeInventarioOrigem,
                                                RebeldeInventario rebeldeInventarioDestino,
                                                Long rebeldeIdOrigem,
                                                Long rebeldeIdDestino) {

        if(!rebeldeInventarioOrigem.getAcessivel()) {
            throw new RebeldeInventarioNaoAcessivelException(
                    String.format("Os itens de inventário do rebelde origem %d estão inacessíveis para troca", rebeldeIdOrigem));
        }
        if(!rebeldeInventarioDestino.getAcessivel()) {
            throw new RebeldeInventarioNaoAcessivelException(
                    String.format("Os itens de inventário do rebelde destino %d estão inacessíveis para troca", rebeldeIdDestino));
        }
    }
}
