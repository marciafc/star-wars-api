package br.com.marcia.starwars.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RebeldeItemInventarioNegociarRequest {

    @NotNull(message = "A lista com os itens do rebelde origem é obrigatória")
    private List<RebeldeItemInventarioRequest> rebeldeOrigemItems;

    @NotNull(message = "A lista com os itens do rebelde destino é obrigatória")
    private List<RebeldeItemInventarioRequest> rebeldeDestinoItems;

    @ApiModelProperty(example = "2", required = true)
    @NotNull(message = "O id do rebelde destino é obrigatório")
    private Long rebeldeIdDestino;
}
