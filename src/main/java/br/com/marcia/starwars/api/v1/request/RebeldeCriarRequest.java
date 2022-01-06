package br.com.marcia.starwars.api.v1.request;

import br.com.marcia.starwars.enumeration.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RebeldeCriarRequest {

    @ApiModelProperty(example = "Mariana", required = true)
    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @ApiModelProperty(example = "40", required = true)
    @NotNull(message = "A idade é obrigatória")
    private Long idade;

    @ApiModelProperty(example = "FEMININO", required = true)
    @NotNull(message = "O gênero é obrigatório")
    private GeneroEnum genero;

    @ApiModelProperty(example = "-29.795456", required = true)
    @NotNull(message = "A latitude é obrigatória")
    private Double latitude;

    @ApiModelProperty(example = "-51.152127", required = true)
    @NotNull(message = "A longitude é obrigatória")
    private Double longitude;

    @ApiModelProperty(example = "República Galáctica", required = true)
    @NotNull(message = "O nome da base da galáxia é obrigatória")
    @JsonAlias("nome_base_galaxia")
    private String nomeBaseGalaxia;

    @NotEmpty(message = "A lista de itens de inventário é obrigatória")
    @JsonProperty("itens_inventario")
    private List<RebeldeItemInventarioRequest> itensInventario;
}
