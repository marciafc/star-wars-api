package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioRequest;
import lombok.Builder;

@Builder
public class RebeldeItemInventarioRequestBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private Long quantidade = 10L;

    public RebeldeItemInventarioRequest toRebeldeItemInventarioRequest() {
        return RebeldeItemInventarioRequest.builder().
                id(id).
                quantidade(quantidade).
                build();
    }
}
