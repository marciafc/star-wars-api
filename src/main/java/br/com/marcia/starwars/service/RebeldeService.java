package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.entity.RebeldeEntity;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
import br.com.marcia.starwars.repository.RebeldeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class RebeldeService {

    private final int NUMERO_MAXIMO_REPORTES_TRAICAO = 3;

    private final RebeldeInventarioService rebeldeInventarioService;

    private final RebeldeRepository rebeldeRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public Rebelde cadastrar(Rebelde rebelde) {
        if(rebelde.getId() != null) {
            throw new IllegalArgumentException("Este rebelde já possui id. Impossível cadastrar.");
        }

        rebelde.setTraidor(Boolean.FALSE);

        // TODO salvar itens
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
