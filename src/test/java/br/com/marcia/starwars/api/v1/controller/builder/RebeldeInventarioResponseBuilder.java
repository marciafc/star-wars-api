package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.response.RebeldeInventarioResponse;
import br.com.marcia.starwars.api.v1.response.RebeldeItemInventarioResponse;

import lombok.Builder;

import java.util.List;

@Builder
public class RebeldeInventarioResponseBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private List<RebeldeItemInventarioResponse> itemsInventario = List.of(
            RebeldeItemInventarioResponseBuilder.builder().build().toRebeldeItemInventarioResponse());

    @Builder.Default
    private Boolean acessivel = Boolean.TRUE;

    public RebeldeInventarioResponse toRebeldeInventarioResponse() {
        return RebeldeInventarioResponse.builder().
                id(id).
                itemsInventario(itemsInventario).
                acessivel(acessivel).
                build();
    }
}
