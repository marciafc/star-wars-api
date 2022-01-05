package br.com.marcia.starwars.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RebeldeItemInventario {

    private Long id;

    private ItemInventario itemInventario;

    private RebeldeInventario rebeldeInventario;

    private Long quantidade;
}
