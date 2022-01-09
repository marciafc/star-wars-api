package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.domain.RebeldeItemIdQuantidade;
import br.com.marcia.starwars.domain.RebeldeItemInventarioNegociar;
import lombok.Builder;

import java.util.List;

@Builder
public class RebeldeItemInventarioNegociarBuilder {

    @Builder.Default
    private List<RebeldeItemIdQuantidade> rebeldeOrigemItems = List.of(RebeldeItemIdQuantidadeBuilder.builder().build().toRebeldeItemIdQuantidade());

    @Builder.Default
    private List<RebeldeItemIdQuantidade> rebeldeDestinoItems = List.of(RebeldeItemIdQuantidadeBuilder.builder().build().toRebeldeItemIdQuantidade());

    @Builder.Default
    private Long rebeldeIdDestino = 1L;

    public RebeldeItemInventarioNegociar toRebeldeItemInventarioNegociar() {
        return RebeldeItemInventarioNegociar.builder().
                rebeldeOrigemItems(rebeldeOrigemItems).
                rebeldeDestinoItems(rebeldeDestinoItems).
                rebeldeIdDestino(rebeldeIdDestino).
                build();
    }

}
