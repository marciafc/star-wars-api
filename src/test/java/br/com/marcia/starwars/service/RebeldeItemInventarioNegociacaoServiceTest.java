package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.domain.RebeldeItemInventarioNegociar;
import br.com.marcia.starwars.exception.RebeldeInventarioNaoAcessivelException;
import br.com.marcia.starwars.exception.TrocaInvalidaItemRebeldeException;
import br.com.marcia.starwars.exception.ValorInvalidoException;
import br.com.marcia.starwars.service.builder.RebeldeBuilder;
import br.com.marcia.starwars.service.builder.RebeldeItemInventarioNegociarBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class RebeldeItemInventarioNegociacaoServiceTest {

    @Mock
    private RebeldeService rebeldeService;

    @InjectMocks
    private RebeldeItemInventarioNegociacaoService rebeldeItemNegociarService;

    @Test
    void whenNegociarItensAtendendoTodasAsRegrasThenRealizarTrocaComSucesso() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();
        Rebelde rebeldeDestino = RebeldeBuilder.builder().build().toRebelde();
        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);

        when(rebeldeService.buscar(any(Long.class))).thenReturn(rebeldeOrigem, rebeldeDestino);
        when(rebeldeService.salvar(any(Rebelde.class))).thenReturn(rebeldeOrigem, rebeldeDestino);

        List<Rebelde> rebeldes = rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar);

        assertThat(rebeldes.size(), is(2));
        assertThat(rebeldes.get(0).getRebeldeInventario().getItemsInventario().get(0),
                                    is(equalTo(rebeldeOrigem.getRebeldeInventario().getItemsInventario().get(0))));
        assertThat(rebeldes.get(1).getRebeldeInventario().getItemsInventario().get(0),
                                    is(equalTo(rebeldeDestino.getRebeldeInventario().getItemsInventario().get(0))));
    }

    @Test
    void whenNegociarItensComRebeldeOrigemFornecedendoMenosPontosQueRebeldeDestinoThenRetornarErro() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();
        Rebelde rebeldeDestino = RebeldeBuilder.builder().build().toRebelde();
        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);
        itensNegociar.getRebeldeOrigemItems().get(0).setQuantidade(5L);

        when(rebeldeService.buscar(any(Long.class))).thenReturn(rebeldeOrigem, rebeldeDestino);

        assertThrows(TrocaInvalidaItemRebeldeException.class, () -> rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar));
    }

    @Test
    void whenNegociarItensComRebeldeOrigemSolicitandoTrocaSemTerQuantidadeOfertadaThenRetornarErro() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();
        Rebelde rebeldeDestino = RebeldeBuilder.builder().build().toRebelde();
        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);
        itensNegociar.getRebeldeOrigemItems().get(0).setQuantidade(100L);

        when(rebeldeService.buscar(any(Long.class))).thenReturn(rebeldeOrigem, rebeldeDestino);

        assertThrows(TrocaInvalidaItemRebeldeException.class, () -> rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar));
    }

    @Test
    void whenNegociarItensComRebeldeOrigemComInventarioInacessivelThenRetornarErro() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();
        rebeldeOrigem.getRebeldeInventario().setAcessivel(Boolean.FALSE);

        Rebelde rebeldeDestino = RebeldeBuilder.builder().build().toRebelde();

        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);

        when(rebeldeService.buscar(any(Long.class))).thenReturn(rebeldeOrigem, rebeldeDestino);

        assertThrows(RebeldeInventarioNaoAcessivelException.class, () -> rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar));
    }

    @Test
    void whenNegociarItensComRebeldeDestinoComInventarioInacessivelThenRetornarErro() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();

        Rebelde rebeldeDestino = RebeldeBuilder.builder().build().toRebelde();
        rebeldeDestino.getRebeldeInventario().setAcessivel(Boolean.FALSE);

        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);

        when(rebeldeService.buscar(any(Long.class))).thenReturn(rebeldeOrigem, rebeldeDestino);

        assertThrows(RebeldeInventarioNaoAcessivelException.class, () -> rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar));
    }

    @Test
    void whenNegociarItensComRebeldeDestinoInexistenteThenReturnarUmErro() {

        Rebelde rebeldeOrigem = RebeldeBuilder.builder().build().toRebelde();
        RebeldeItemInventarioNegociar itensNegociar = RebeldeItemInventarioNegociarBuilder.builder().build().toRebeldeItemInventarioNegociar();
        itensNegociar.setRebeldeIdDestino(2L);

        when(rebeldeService.buscar(any(Long.class))).thenAnswer(
                invocation -> {
                    Object argumento = invocation.getArguments()[0];
                    if (argumento.equals(rebeldeOrigem.getId())) {
                        return rebeldeOrigem;
                    }
                    throw new ValorInvalidoException(
                            String.format("Não existe rebelde com id %d", 2L)
                    );
                }
        );

        assertThrows(ValorInvalidoException.class, () -> rebeldeItemNegociarService.negociarItens(rebeldeOrigem.getId(), itensNegociar));
    }
}
