package br.com.marcia.starwars.service.builder;

import br.com.marcia.starwars.domain.RebeldeItemIdQuantidade;
import lombok.Builder;

@Builder
public class RebeldeItemIdQuantidadeBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private Long quantidade = 10L;

    public RebeldeItemIdQuantidade toRebeldeItemIdQuantidade() {
        return RebeldeItemIdQuantidade.builder().
               id(id).
                quantidade(quantidade).
                build();
    }

}
