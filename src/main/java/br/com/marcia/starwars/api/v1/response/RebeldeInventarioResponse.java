package br.com.marcia.starwars.api.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RebeldeInventarioResponse {

    private Long id;

    @JsonProperty("items_inventario")
    private List<RebeldeItemInventarioResponse> itemsInventario;

    private Boolean acessivel;
}
