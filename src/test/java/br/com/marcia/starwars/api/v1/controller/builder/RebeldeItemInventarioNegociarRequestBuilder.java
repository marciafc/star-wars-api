package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioNegociarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioRequest;
import lombok.Builder;

import java.util.List;

@Builder
public class RebeldeItemInventarioNegociarRequestBuilder {

    @Builder.Default
    private List<RebeldeItemInventarioRequest> rebeldeOrigemItems = List.of(RebeldeItemInventarioRequestBuilder.builder().
            build().toRebeldeItemInventarioRequest());

    @Builder.Default
    private List<RebeldeItemInventarioRequest> rebeldeDestinoItems = List.of(RebeldeItemInventarioRequestBuilder.builder().
            build().toRebeldeItemInventarioRequest());

    @Builder.Default
    private Long rebeldeIdDestino = 1L;

    public RebeldeItemInventarioNegociarRequest toRebeldeItemInventarioNegociarRequest() {
        return RebeldeItemInventarioNegociarRequest.builder().
                rebeldeOrigemItems(rebeldeOrigemItems).
                rebeldeDestinoItems(rebeldeDestinoItems).
                rebeldeIdDestino(rebeldeIdDestino).
                build();
    }
}
