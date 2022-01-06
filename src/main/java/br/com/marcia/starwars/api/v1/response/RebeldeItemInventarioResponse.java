package br.com.marcia.starwars.api.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RebeldeItemInventarioResponse {

    private Long id;

    @JsonProperty("item")
    private ItemInventarioResponse itemInventario;

    private Long quantidade;
}
