package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventario;
import lombok.Builder;

import java.util.List;

@Builder
public class RebeldeInventarioBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private List<RebeldeItemInventario> itemsInventario = List.of(
            RebeldeItemInventarioBuilder.builder().build().toRebeldeItemInventario());

    @Builder.Default
    private Boolean acessivel = Boolean.TRUE;

    public RebeldeInventario toRebeldeInventario() {
        return RebeldeInventario.builder().
                id(id).
                itemsInventario(itemsInventario).
                acessivel(acessivel).
                build();
    }
}

