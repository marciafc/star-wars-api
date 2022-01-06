package br.com.marcia.starwars.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RebeldeItemInventarioRequest {

    @ApiModelProperty(example = "1", required = true)
    @NotNull(message = "O id é obrigatório")
    private Long id;

    @ApiModelProperty(example = "10", required = true)
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "O valor deve ser maior ou igual a 1")
    private Long quantidade;
}
