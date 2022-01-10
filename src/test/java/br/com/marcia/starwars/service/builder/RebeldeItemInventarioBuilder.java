package br.com.marcia.starwars.service.builder;

import br.com.marcia.starwars.domain.ItemInventario;
import br.com.marcia.starwars.domain.RebeldeItemInventario;
import lombok.Builder;

@Builder
public class RebeldeItemInventarioBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private ItemInventario itemInventario = ItemInventarioBuilder.builder().build().toItemInventario();

    @Builder.Default
    private Long quantidade = 10L;

    public RebeldeItemInventario toRebeldeItemInventario() {
        return RebeldeItemInventario.builder().
                id(id).
                itemInventario(itemInventario).
                quantidade(quantidade).
                build();
    }
}
