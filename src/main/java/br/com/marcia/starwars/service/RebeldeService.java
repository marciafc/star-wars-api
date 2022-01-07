package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.*;
import br.com.marcia.starwars.entity.RebeldeEntity;
import br.com.marcia.starwars.exception.ValorInvalidoException;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
import br.com.marcia.starwars.repository.RebeldeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class RebeldeService {

    private final int NUMERO_MAXIMO_REPORTE_TRAICAO = 3;

    private final ItemInventarioService itemInventarioService;

    private final RebeldeRepository rebeldeRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public Rebelde cadastrar(Rebelde rebelde, Map<Long, Long> idQuantidadeItens) {

        if(rebelde.getId() != null) {
            throw new IllegalArgumentException("Este rebelde já possui id. Impossível cadastrar.");
        }

        // Todos os tipos de itens e respectivas pontuações
        List<ItemInventario> items = itemInventarioService.listar();

        // Inventário e itens do rebelde
        validarRebeldeItemInventario(items, idQuantidadeItens);
        rebelde = preencherRebeldeInventario(items, idQuantidadeItens, rebelde);

        rebelde.setTraidor(Boolean.FALSE);

        RebeldeEntity rebeldeEntity = objectMapper.convertValue(rebelde, RebeldeEntity.class);
        rebelde = objectMapper.convertValue(rebeldeRepository.save(rebeldeEntity), Rebelde.class);

        return rebelde;
    }

    public Rebelde buscar(Long id) {
         RebeldeEntity rebeldeEntity = rebeldeRepository.findById(id).
                 orElseThrow(() -> new RebeldeNaoEncontradoException(
                        String.format("Não existe rebelde com id %d", id)));

        return objectMapper.convertValue(rebeldeEntity, Rebelde.class);
    }

    public Rebelde atualizarLocalizacao(Rebelde rebelde) {

        Rebelde rebeldeExistente = buscar(rebelde.getId());

        rebeldeExistente.setLatitude(rebelde.getLatitude());
        rebeldeExistente.setLongitude(rebelde.getLongitude());
        rebeldeExistente.setNomeBaseGalaxia(rebelde.getNomeBaseGalaxia());

        RebeldeEntity rebeldeAtualizado = rebeldeRepository.save(
                objectMapper.convertValue(rebeldeExistente, RebeldeEntity.class));

        return objectMapper.convertValue(rebeldeAtualizado, Rebelde.class);
    }

    public Long getQuantidadeRebeldesPorTraidor(Boolean traidor) {
        return rebeldeRepository.countByTraidor(traidor);
    }

    public Long getQuantidadeTotalRebeldes() {
        return rebeldeRepository.count();
    }

    public Rebelde reportarTraicao(Long rebeldeTraidorId, Long rebeldeRelatorId) {

        Rebelde rebeldeTraidor = buscar(rebeldeTraidorId);

        Rebelde rebeldeRelator;
        try {
            rebeldeRelator = buscar(rebeldeRelatorId);
        } catch (RebeldeNaoEncontradoException exception) {
            throw new ValorInvalidoException( String.format("Não existe rebelde com id %d", rebeldeRelatorId));
        }

        if(rebeldeTraidor.getId() == rebeldeRelator.getId()) {
            throw new ValorInvalidoException("Os ids são iguais, impossível realizar o reporte da traição");
        }

        RebeldeEntity rebeldeTraidorEntity = salvarReporteTraicao(rebeldeTraidor, rebeldeRelator);
        rebeldeTraidorEntity = atualizarRebeldeComoTraidor(rebeldeTraidorEntity);

        return objectMapper.convertValue(rebeldeTraidorEntity, Rebelde.class);
    }

    private RebeldeEntity salvarReporteTraicao(Rebelde rebeldeTraidor, Rebelde rebeldeRelator) {
        rebeldeTraidor.getReporteTraicoes().add(rebeldeRelator);
        return rebeldeRepository.save(
                objectMapper.convertValue(rebeldeTraidor, RebeldeEntity.class));

    }

    /**
     * Atualiza o rebelde como traidor, se alcançou o número máximo de reporte de traições;
     * Ao ser caracterizado como traidor, seus itens de inventário se tornam inacessíveis.
     *
     * @param rebeldeTraidorEntity
     */
    private RebeldeEntity atualizarRebeldeComoTraidor(RebeldeEntity rebeldeTraidorEntity) {

        if(rebeldeTraidorEntity.getReporteTraicoes().size() == NUMERO_MAXIMO_REPORTE_TRAICAO) {

            rebeldeTraidorEntity.getRebeldeInventario().setAcessivel(Boolean.FALSE);
            rebeldeTraidorEntity.setTraidor(Boolean.TRUE);

            return rebeldeRepository.save(rebeldeTraidorEntity);
        }
        return rebeldeTraidorEntity;
    }

    private void validarRebeldeItemInventario(List<ItemInventario> items, Map<Long, Long> idQuantidadeItens) {
        idQuantidadeItens.forEach((id, quantidade) -> {

            if(!items.stream().
                    filter(i -> i.getId() == id).findFirst().isPresent()) {

                log.error(String.format("Não existe item de inventário com id %d", id));

                throw new ValorInvalidoException(
                        String.format("Não existe item de inventário com id %d", id));
            }
        });
    }

    private Rebelde preencherRebeldeInventario(List<ItemInventario> items, Map<Long, Long> idQuantidadeItens, Rebelde rebelde) {
        RebeldeInventario rebeldeInventario = RebeldeInventario.builder().
                acessivel(Boolean.TRUE).
                itemsInventario(criarListaRebeldeItem(items, idQuantidadeItens)).
                build();
        rebelde.setRebeldeInventario(rebeldeInventario);

        return rebelde;
    }

    private List<RebeldeItemInventario> criarListaRebeldeItem(List<ItemInventario> items, Map<Long, Long> idQuantidadeItens) {
        List<RebeldeItemInventario> itensRebelde = new ArrayList<>();

        items.forEach((item) -> {

            Long quantidade = idQuantidadeItens.get(item.getId());

            RebeldeItemInventario rebeldeItem = RebeldeItemInventario.builder().
                    itemInventario(item).
                    quantidade(quantidade != null ? quantidade : 0)
                    .build();

            itensRebelde.add(rebeldeItem);
        });

        return itensRebelde;
    }
}
