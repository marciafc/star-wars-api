package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.response.RebeldeIdResponse;
import br.com.marcia.starwars.api.v1.response.RebeldeInventarioResponse;
import br.com.marcia.starwars.api.v1.response.RebeldeResponse;
import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
public class RebeldeResponseBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Marcia";

    @Builder.Default
    private Long idade = 39L;

    @Builder.Default
    private GeneroEnum genero = GeneroEnum.FEMININO;

    @Builder.Default
    private Double latitude = -29.795456;

    @Builder.Default
    private Double longitude = -51.152127;

    @Builder.Default
    private String nomeBaseGalaxia = "República Galáctica";

    @Builder.Default
    private Boolean traidor = Boolean.FALSE;

    @Builder.Default
    private RebeldeInventarioResponse rebeldeInventarioResponse = RebeldeInventarioResponseBuilder.builder().build().toRebeldeInventarioResponse();

    @Builder.Default
    private Set<RebeldeIdResponse> reporteTraicoes = new HashSet<>();

    public RebeldeResponse toRebeldeResponse() {
        return new RebeldeResponse(id,
                nome,
                idade,
                genero,
                latitude,
                longitude,
                nomeBaseGalaxia,
                traidor,
                rebeldeInventarioResponse,
                reporteTraicoes);
    }
}
