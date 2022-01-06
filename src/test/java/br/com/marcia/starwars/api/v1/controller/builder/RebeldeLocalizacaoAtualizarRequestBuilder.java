package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.request.RebeldeLocalizacaoAtualizarRequest;
import lombok.Builder;

@Builder
public class RebeldeLocalizacaoAtualizarRequestBuilder {

    @Builder.Default
    private Double latitude = 29.925597;

    @Builder.Default
    private Double longitude = -51.078866;

    @Builder.Default
    private String nomeBaseGalaxia = "Nova República";

    public RebeldeLocalizacaoAtualizarRequest toRebeldeLocalizacao() {
        return new RebeldeLocalizacaoAtualizarRequest(latitude,
                longitude,
                nomeBaseGalaxia);
    }
}
