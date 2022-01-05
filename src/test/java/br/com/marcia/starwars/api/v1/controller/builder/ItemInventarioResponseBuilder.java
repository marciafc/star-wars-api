package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.response.ItemInventarioResponse;
import lombok.Builder;

@Builder
public class ItemInventarioResponseBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String descricao = "Arma";

    @Builder.Default
    private Long pontuacao = 4L;

    public ItemInventarioResponse toItemInventarioResponse() {
        return new ItemInventarioResponse(id,
                descricao,
                pontuacao);
    }
}
