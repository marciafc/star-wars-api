package br.com.marcia.starwars.domain;

import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rebelde {

    private Long id;

    private String nome;

    private Long idade;

    private GeneroEnum genero;

    private Double latitude;

    private Double longitude;

    private String nomeBaseGalaxia;

    private Boolean traidor;

    private RebeldeInventario rebeldeInventario;

    private Set<Rebelde> reporteTraicoes;
}
