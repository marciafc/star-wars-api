package br.com.marcia.starwars.service;

import br.com.marcia.starwars.repository.RebeldeInventarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RebeldeInventarioService {

    private final RebeldeInventarioRepository rebeldeInventarioRepository;

    /*
    private final ObjectMapper objectMapper;

    public RebeldeInventario salvar(RebeldeInventario rebeldeInventario) {

        RebeldeInventarioEntity rebeldeInventarioEntity = objectMapper.convertValue(
                rebeldeInventario, RebeldeInventarioEntity.class);

        rebeldeInventarioEntity = rebeldeInventarioRepository.save(rebeldeInventarioEntity);

        return objectMapper.convertValue(rebeldeInventarioEntity, RebeldeInventario.class);
    }

    public RebeldeInventario buscarPorRebeldeId(Long rebeldeId) {
        RebeldeInventarioEntity rebeldeInventarioEntity = rebeldeInventarioRepository.findByRebeldeId(rebeldeId);*//*.
                orElseThrow(() -> new RebeldeNaoEncontradoException(//TODO outra exception
                        String.format("Não existe rebelde com id %d", rebeldeId)));*//*

        return objectMapper.convertValue(rebeldeInventarioEntity, RebeldeInventario.class);
    }*/
}
