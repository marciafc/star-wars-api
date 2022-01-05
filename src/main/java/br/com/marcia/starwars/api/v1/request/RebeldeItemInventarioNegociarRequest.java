package br.com.marcia.starwars.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RebeldeItemInventarioNegociarRequest {

    @JsonProperty("rebelde_origem_items")
    private List<RebeldeItemInventarioRequest> rebeldeOrigemItems;

    @JsonProperty("rebelde_destino_items")
    private List<RebeldeItemInventarioRequest> rebeldeDestinoItems;

    @ApiModelProperty(example = "2", required = true)
    private Long rebeldeIdDestino;
}
