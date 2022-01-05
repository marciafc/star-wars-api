package br.com.marcia.starwars.service;

import br.com.marcia.starwars.domain.ItemInventario;
import br.com.marcia.starwars.entity.ItemInventarioEntity;
import br.com.marcia.starwars.repository.ItemInventarioRepository;
import br.com.marcia.starwars.service.builder.ItemInventarioBuilder;
import br.com.marcia.starwars.service.builder.ItemInventarioEntityBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ItemInventarioServiceTest {

    @Mock
    private ItemInventarioRepository itemInventarioRepository;

    @Mock
    private ObjectMapper objectMapper;;

    @InjectMocks
    private ItemInventarioService itemInventarioService;

    @Test
    void whenListarItensInventarioForChamadoThenReturnarUmaListaDeItensInventario() {

        ItemInventarioEntity itemInventarioEntity = ItemInventarioEntityBuilder.builder()
                .build().toItemInventarioEntity();

        ItemInventario itemInventario = ItemInventarioBuilder.builder()
                .build().toItemInventario();

        when(itemInventarioRepository.findAll()).thenReturn(Collections.singletonList(itemInventarioEntity));

        when(objectMapper.convertValue(any(ItemInventarioEntity.class), eq(ItemInventario.class)))
                .thenReturn(itemInventario);

        List<ItemInventario> itemInventarios = itemInventarioService.listar();

        assertThat(itemInventarios, is(not(empty())));
        assertThat(itemInventarios.get(0), is(equalTo(itemInventario)));
    }

    @Test
    void whenListarItensInventarioForChamadoThenReturnarUmaListaVaziaDeItensInventario() {

        when(itemInventarioRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<ItemInventario> itemInventarios = itemInventarioService.listar();

        assertThat(itemInventarios, is(empty()));
    }
}
