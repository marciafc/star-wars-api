package br.com.marcia.starwars.api.v1.request;

import br.com.marcia.starwars.enumeration.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RebeldeCriarRequest {

    @ApiModelProperty(example = "Mariana", required = true)
    private String nome;

    @ApiModelProperty(example = "40", required = true)
    private Long idade;

    @ApiModelProperty(example = "FEMININO", required = true)
    private GeneroEnum genero;

    @ApiModelProperty(example = "-29.795456", required = true)
    private Double latitude;

    @ApiModelProperty(example = "-51.152127", required = true)
    private Double longitude;

    @ApiModelProperty(example = "República Galáctica", required = true)
    private String nomeBaseGalaxia;

    @JsonProperty("itens_inventario")
    private List<RebeldeItemInventarioRequest> itensInventario;
}
