package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.api.v1.request.RebeldeCriarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioRequest;
import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.Builder;

import java.util.List;

@Builder
public class RebeldeCriarRequestBuilder {

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
    private List<RebeldeItemInventarioRequest> itensInventario = List.of(
            RebeldeItemInventarioRequestBuilder.builder().build().toRebeldeItemInventarioRequest());

    public RebeldeCriarRequest toRebeldeCriarRequest() {
        return new RebeldeCriarRequest(
                nome,
                idade,
                genero,
                latitude,
                longitude,
                nomeBaseGalaxia,
                itensInventario);
    }
}
