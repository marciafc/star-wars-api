package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.request.RebeldeIdRequest;
import lombok.Builder;

@Builder
public class RebeldeIdRequestBuilder {

    @Builder.Default
    private Long rebeldeId = 1L;

    public RebeldeIdRequest toRebeldeIdRequest() {
        return new RebeldeIdRequest(rebeldeId);
    }
}
