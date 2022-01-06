package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.response.ItemInventarioResponse;
import br.com.marcia.starwars.api.v1.response.RebeldeItemInventarioResponse;
import lombok.Builder;

@Builder
public class RebeldeItemInventarioResponseBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private ItemInventarioResponse itemInventario = ItemInventarioResponseBuilder.builder().build().toItemInventarioResponse();

    @Builder.Default
    private Long quantidade = 10L;

    public RebeldeItemInventarioResponse toRebeldeItemInventarioResponse() {
        return RebeldeItemInventarioResponse.builder().
                id(id).
                itemInventario(itemInventario).
                quantidade(quantidade).
                build();
    }
}
