package br.com.marcia.starwars.api.v1.response;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.enumeration.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class RebeldeResponse {

    private Long id;

    private String nome;

    private Long idade;

    private GeneroEnum genero;

    private Double latitude;

    private Double longitude;

    @JsonProperty("nome_base_galaxia")
    private String nomeBaseGalaxia;

    private Boolean traidor;

    @JsonProperty("rebelde_inventario")
    private RebeldeInventarioResponse rebeldeInventario;

    @JsonProperty("reporte_traicoes")
    private Set<Rebelde> reporteTraicoes;
}
