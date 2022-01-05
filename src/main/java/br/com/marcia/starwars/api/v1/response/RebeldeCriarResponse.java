package br.com.marcia.starwars.api.v1.response;

import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RebeldeCriarResponse {

    private Long id;

    private String nome;

    private Long idade;

    private GeneroEnum genero;

    private Long latitude;

    private Long longitude;

    private String nomeBaseGalaxia;

    private Boolean traidor;
}
