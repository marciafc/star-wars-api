package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.ErrorHandler;
import br.com.marcia.starwars.api.v1.controller.builder.*;
import br.com.marcia.starwars.api.v1.request.RebeldeCriarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeIdRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeLocalizacaoAtualizarRequest;
import br.com.marcia.starwars.api.v1.response.RebeldeIdResponse;
import br.com.marcia.starwars.api.v1.response.RebeldeResponse;
import br.com.marcia.starwars.domain.Rebelde;
import br.com.marcia.starwars.exception.ValorInvalidoException;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
import br.com.marcia.starwars.service.RebeldeService;
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

import java.util.Map;
import java.util.Set;

import static br.com.marcia.starwars.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class RebeldeControllerTest {

    private static final String REBELDES_API_URL_PATH = "/v1/rebeldes";
    private static final String REBELDE_API_SUBPATH_LOCALIZACAO_URL = "/localizacao";
    private static final String REBELDE_API_SUBPATH_REPORTE_TRAICAO_URL = "/reporte-traidor";
    private static final Long REBELDE_ID_VALIDO = 1L;

    @Mock
    private RebeldeService rebeldeService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private RebeldeController rebeldeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rebeldeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new ErrorHandler())
                .build();
    }

    @Test
    void whenPOSTForChamadoThenUmRebeldeSeraCriado() throws Exception {

        RebeldeCriarRequest rebeldeCriarRequest = RebeldeCriarRequestBuilder.builder().build().toRebeldeCriarRequest();

        RebeldeResponse rebeldeResponse = RebeldeResponseBuilder.builder().build().toRebeldeResponse();
        Rebelde rebelde = RebeldeBuilder.builder().build().toRebelde();

        when(rebeldeService.cadastrar(any(Rebelde.class), any(Map.class))).thenReturn(rebelde);
        when(objectMapper.convertValue(any(RebeldeCriarRequest.class), eq(Rebelde.class))).thenReturn(rebelde);
        when(objectMapper.convertValue(any(Rebelde.class), eq(RebeldeResponse.class))).thenReturn(rebeldeResponse);

        mockMvc.perform(post(REBELDES_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeCriarRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(rebeldeResponse.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(rebeldeResponse.getNome())))
                .andExpect(jsonPath("$.rebelde_inventario.items_inventario[0].item.id", is(rebeldeResponse.getRebeldeInventario().
                        getItemsInventario().get(0).getItemInventario().getId().intValue())))
                .andExpect(jsonPath("$.rebelde_inventario.items_inventario[0].item.descricao", is(rebeldeResponse.getRebeldeInventario().
                        getItemsInventario().get(0).getItemInventario().getDescricao())));
    }

    @Test
    void whenPOSTForChamadoComIdItemInventarioInvalidoThenUmErroSeraRetornado() throws Exception {

        RebeldeCriarRequest rebeldeCriarRequest = RebeldeCriarRequestBuilder.builder().build().toRebeldeCriarRequest();
        Rebelde rebelde = RebeldeBuilder.builder().build().toRebelde();

        when(rebeldeService.cadastrar(any(Rebelde.class), any(Map.class))).thenThrow(ValorInvalidoException.class);
        when(objectMapper.convertValue(any(RebeldeCriarRequest.class), eq(Rebelde.class))).thenReturn(rebelde);

        mockMvc.perform(post(REBELDES_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeCriarRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenPATCHAtualizarLocalizacaoForChamadoThenAtualizarComSucesso() throws Exception {

        RebeldeLocalizacaoAtualizarRequest rebeldeLocalizacaoRequest = RebeldeLocalizacaoAtualizarRequestBuilder.
                builder().build().toRebeldeLocalizacao();

        RebeldeResponse rebeldeResponse = RebeldeResponseBuilder.builder().build().toRebeldeResponse();
        Rebelde rebelde = RebeldeBuilder.builder().build().toRebelde();

        when(rebeldeService.atualizarLocalizacao(any(Rebelde.class))).thenReturn(rebelde);
        when(objectMapper.convertValue(any(Rebelde.class), eq(RebeldeResponse.class))).thenReturn(rebeldeResponse);

        mockMvc.perform(patch(REBELDES_API_URL_PATH + "/" + REBELDE_ID_VALIDO + "/" + REBELDE_API_SUBPATH_LOCALIZACAO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeLocalizacaoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude", is(rebeldeResponse.getLatitude())))
                .andExpect(jsonPath("$.longitude", is(rebeldeResponse.getLongitude())))
                .andExpect(jsonPath("$.nome_base_galaxia", is(rebeldeResponse.getNomeBaseGalaxia())));
    }

    @Test
    void whenPATCHAtualizarLocalizacaoForChamadoComRebeldeInexistenteThenUmErroSeraRetornado() throws Exception {

        RebeldeLocalizacaoAtualizarRequest rebeldeLocalizacaoRequest = RebeldeLocalizacaoAtualizarRequestBuilder.
                builder().build().toRebeldeLocalizacao();

        when(rebeldeService.atualizarLocalizacao(any(Rebelde.class))).thenThrow(RebeldeNaoEncontradoException.class);

        mockMvc.perform(patch(REBELDES_API_URL_PATH + "/" + REBELDE_ID_VALIDO + "/" + REBELDE_API_SUBPATH_LOCALIZACAO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeLocalizacaoRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPATCHReportarTraicaoForChamadoThenSalvarReporteComSucesso() throws Exception {

        RebeldeIdRequest rebeldeIdRequest = RebeldeIdRequestBuilder.
                builder().build().toRebeldeIdRequest();

        RebeldeResponse rebeldeResponse = RebeldeResponseBuilder.builder().build().toRebeldeResponse();
        rebeldeResponse.setReporteTraicoes(Set.of(RebeldeIdResponse.builder().id(1L).build()));
        Rebelde rebelde = RebeldeBuilder.builder().build().toRebelde();

        when(rebeldeService.reportarTraicao(any(Long.class), any(Long.class))).thenReturn(rebelde);
        when(objectMapper.convertValue(any(Rebelde.class), eq(RebeldeResponse.class))).thenReturn(rebeldeResponse);

        mockMvc.perform(patch(REBELDES_API_URL_PATH + "/" + REBELDE_ID_VALIDO + "/" + REBELDE_API_SUBPATH_REPORTE_TRAICAO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeIdRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reporte_traicoes[0].id", is(rebeldeResponse.getReporteTraicoes().stream().
                        filter(r -> r.getId() == rebelde.getId()).findFirst().get().getId().intValue())));
    }

    @Test
    void whenPATCHReportarTraicaoForChamadoComRebeldeInexistenteThenUmErroSeraRetornado() throws Exception {

        RebeldeIdRequest rebeldeIdRequest = RebeldeIdRequestBuilder.
                builder().build().toRebeldeIdRequest();

        when(rebeldeService.reportarTraicao(any(Long.class), any(Long.class))).thenThrow(RebeldeNaoEncontradoException.class);

        mockMvc.perform(patch(REBELDES_API_URL_PATH + "/" + REBELDE_ID_VALIDO + "/" + REBELDE_API_SUBPATH_REPORTE_TRAICAO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(rebeldeIdRequest)))
                .andExpect(status().isNotFound());
    }
}