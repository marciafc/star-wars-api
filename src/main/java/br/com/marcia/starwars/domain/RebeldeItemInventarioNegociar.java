package br.com.marcia.starwars.domain;

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

    private List<RebeldeItemIdQuantidade> rebeldeOrigemItems;

    private List<RebeldeItemIdQuantidade> rebeldeDestinoItems;

    private Long rebeldeIdDestino;
}
