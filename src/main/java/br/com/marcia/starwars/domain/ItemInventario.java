package br.com.marcia.starwars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemInventario {

    private Long id;

    private String descricao;

    private Long pontuacao;
}
