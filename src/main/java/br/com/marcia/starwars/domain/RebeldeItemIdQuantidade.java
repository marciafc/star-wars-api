package br.com.marcia.starwars.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RebeldeItemIdQuantidade {

    private Long id;

    private Long quantidade;
}
