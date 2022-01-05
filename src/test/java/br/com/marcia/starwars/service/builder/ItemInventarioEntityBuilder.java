package br.com.marcia.starwars.service.builder;

import br.com.marcia.starwars.entity.ItemInventarioEntity;
import lombok.Builder;

@Builder
public class ItemInventarioEntityBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String descricao = "Arma";

    @Builder.Default
    private Long pontuacao = 4L;

    public ItemInventarioEntity toItemInventarioEntity() {
        return new ItemInventarioEntity(id,
                descricao,
                pontuacao);
    }
}
