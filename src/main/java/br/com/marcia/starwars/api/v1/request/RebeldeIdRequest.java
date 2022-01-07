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
public class RebeldeIdRequest {

    @ApiModelProperty(example = "2", required = true)
    @NotNull(message = "O id do rebelde é obrigatório")
    @JsonProperty("rebelde_id")
    private Long rebeldeId;
}
