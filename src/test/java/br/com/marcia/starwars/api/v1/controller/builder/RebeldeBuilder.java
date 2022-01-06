package br.com.marcia.starwars.api.v1.controller.builder;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeInventario;
import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
public class RebeldeBuilder {

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
    private RebeldeInventario rebeldeInventario = RebeldeInventarioBuilder.builder().build().toRebeldeInventario();

    @Builder.Default
    private Set<Rebelde> reporteTraicoes = new HashSet<>();

    public Rebelde toRebelde() {
        return new Rebelde(id,
                nome,
                idade,
                genero,
                latitude,
                longitude,
                nomeBaseGalaxia,
                traidor,
                rebeldeInventario,
                reporteTraicoes);
    }
}
