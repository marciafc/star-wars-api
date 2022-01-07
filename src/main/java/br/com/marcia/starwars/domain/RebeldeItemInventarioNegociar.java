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

    private List<RebeldeItemInventario> rebeldeOrigemItems;

    private List<RebeldeItemInventario> rebeldeDestinoItems;

    private Long rebeldeIdDestino;
}
