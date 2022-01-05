package br.com.marcia.starwars.domain;

import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RebeldeItemInventarioNegociar {

    private List<RebeldeItemInventarioRequest> rebeldeOrigemItems;

    private List<RebeldeItemInventarioRequest> rebeldeDestinoItems;

    private Long rebeldeIdDestino;
}
