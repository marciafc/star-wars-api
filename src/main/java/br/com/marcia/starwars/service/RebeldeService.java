package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.ItemInventario;
import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventario;
import br.com.marcia.starwars.entity.RebeldeEntity;
import br.com.marcia.starwars.exception.IdItemInventarioNaoEncontradoException;
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

    private final int NUMERO_MAXIMO_REPORTES_TRAICAO = 3;

    private final RebeldeInventarioService rebeldeInventarioService;

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

    private void validarRebeldeItemInventario(List<ItemInventario> items, Map<Long, Long> idQuantidadeItens) {
        idQuantidadeItens.forEach((id, quantidade) -> {

            if(!items.stream().
                    filter(i -> i.getId() == id).findFirst().isPresent()) {

                log.error(String.format("Não existe item de inventário com id %d", id));

                throw new IdItemInventarioNaoEncontradoException(
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

    public Rebelde buscar(Long id) {
         RebeldeEntity rebeldeEntity = rebeldeRepository.findById(id).
                 orElseThrow(() -> new RebeldeNaoEncontradoException(
                        String.format("Não existe rebelde com id %d", id)));

        return objectMapper.convertValue(rebeldeEntity, Rebelde.class);
    }

    public Rebelde atualizar(Rebelde rebelde) {
        RebeldeEntity rebeldeAtualizado = rebeldeRepository.save(
                objectMapper.convertValue(rebelde, RebeldeEntity.class));

        return objectMapper.convertValue(rebeldeAtualizado, Rebelde.class);
    }

    public Long getQuantidadeRebeldesPorTraidor(Boolean traidor) {
        return rebeldeRepository.countByTraidor(traidor);
    }

    public Long getQuantidadeTotalRebeldes() {
        return rebeldeRepository.count();
    }

    public Rebelde reportarTraicao(Long rebeldeTraidorId, Long rebeldeRelatorId) {

        // TODO refatorar
        // Validar se ids são existentes e diferentes um do outro
        // Erro no terceiro reporte
        // Se for o terceiro report, acessivel = false e traidor = true
        try {
            Rebelde rebelde = buscar(rebeldeTraidorId);
            Rebelde rebeldeRelator = buscar(rebeldeRelatorId);

            rebelde.getReporteTraicoes().add(rebeldeRelator);

            Rebelde rebeldeAtualizado = atualizar(rebelde);

            if(rebelde.getReporteTraicoes().size() == NUMERO_MAXIMO_REPORTES_TRAICAO) {
                RebeldeInventario rebeldeInventario = rebeldeInventarioService.buscarPorRebeldeId(rebelde.getId());
                rebeldeInventario.setAcessivel(Boolean.FALSE);

                rebelde.setTraidor(Boolean.TRUE);
                rebeldeAtualizado = atualizar(rebelde);

                rebeldeInventarioService.salvar(rebeldeAtualizado.getRebeldeInventario());
            }
            return rebeldeAtualizado;

        } catch (RebeldeNaoEncontradoException rebeldeNaoEncontradoException) {
            throw rebeldeNaoEncontradoException;
        }
    }
}
