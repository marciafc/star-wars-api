package br.com.marcia.starwars.service.builder;

import br.com.marcia.starwars.domain.ItemInventario;
import lombok.Builder;

@Builder
public class ItemInventarioBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String descricao = "Arma";

    @Builder.Default
    private Long pontuacao = 4L;

    public ItemInventario toItemInventario() {
        return new ItemInventario(id,
                descricao,
                pontuacao);
    }
}
