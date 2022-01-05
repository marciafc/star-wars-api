package br.com.marcia.starwars.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RebeldeLocalizacaoAtualizarRequest {

    @ApiModelProperty(example = "29.925597", required = true)
    private Double latitude;

    @ApiModelProperty(example = "-51.078866", required = true)
    private Double longitude;

    @ApiModelProperty(example = "Nova República", required = true)
    private String nomeBaseGalaxia;
}
