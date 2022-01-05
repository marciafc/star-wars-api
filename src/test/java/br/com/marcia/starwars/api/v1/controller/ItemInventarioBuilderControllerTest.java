package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.builder.ItemInventarioBuilder;
import br.com.marcia.starwars.api.v1.controller.builder.ItemInventarioResponseBuilder;
import br.com.marcia.starwars.api.v1.response.ItemInventarioResponse;
import br.com.marcia.starwars.domain.ItemInventario;
import br.com.marcia.starwars.service.ItemInventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ItemInventarioBuilderControllerTest {

    private static final String ITEM_INVENTARIO_API_URL_PATH = "/v1/itens-inventario";

    private MockMvc mockMvc;

    @Mock
    private ItemInventarioService itemInventarioService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ItemInventarioController itemInventarioController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemInventarioController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETListarComItensInventarioForChamadoThenRetornarOkStatus() throws Exception {

        ItemInventario itemInventario = ItemInventarioBuilder.builder().build().toItemInventario();
        ItemInventarioResponse itemInventarioResponse = ItemInventarioResponseBuilder.builder().build().toItemInventarioResponse();

        when(itemInventarioService.listar()).thenReturn(Collections.singletonList(itemInventario));
        when(objectMapper.convertValue(any(ItemInventario.class), eq(ItemInventarioResponse.class))).thenReturn(itemInventarioResponse);

        mockMvc.perform(get(ITEM_INVENTARIO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(itemInventarioResponse.getId().intValue())))
                .andExpect(jsonPath("$[0].descricao", is(itemInventarioResponse.getDescricao())))
                .andExpect(jsonPath("$[0].pontuacao", is(itemInventarioResponse.getPontuacao().intValue())));
    }

    @Test
    void whenGETListarSemItensInventarioForChamadoThenRetornarOkStatus() throws Exception {

        when(itemInventarioService.listar()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get(ITEM_INVENTARIO_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
