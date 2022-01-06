package br.com.marcia.starwars.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RebeldeLocalizacaoAtualizarRequest {

    @ApiModelProperty(example = "29.925597", required = true)
    @NotNull(message = "A latitude é obrigatória")
    private Double latitude;

    @ApiModelProperty(example = "-51.078866", required = true)
    @NotNull(message = "A longitude é obrigatória")
    private Double longitude;

    @ApiModelProperty(example = "Nova República", required = true)
    @NotNull(message = "O nome da base da galáxia é obrigatória")
    @JsonProperty("nome_base_galaxia")
    private String nomeBaseGalaxia;
}
