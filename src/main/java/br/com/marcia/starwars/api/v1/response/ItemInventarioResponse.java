package br.com.marcia.starwars.api.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemInventarioResponse {

    private Long id;

    private String descricao;

    private Long pontuacao;
}
