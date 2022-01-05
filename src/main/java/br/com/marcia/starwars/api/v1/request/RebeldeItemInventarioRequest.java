package br.com.marcia.starwars.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RebeldeItemInventarioRequest {

    @ApiModelProperty(example = "1", required = true)
    private Long id;

    @ApiModelProperty(example = "10", required = true)
    private Long quantidade;
}
