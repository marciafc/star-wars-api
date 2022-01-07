package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.interfaces.IRebeldeControllerDocs;
import br.com.marcia.starwars.api.v1.request.*;
import br.com.marcia.starwars.api.v1.response.RebeldeResponse;
import br.com.marcia.starwars.domain.*;
import br.com.marcia.starwars.service.RebeldeItemInventarioNegociacaoService;
import br.com.marcia.starwars.service.RebeldeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/rebeldes")
@AllArgsConstructor
@Slf4j
public class RebeldeController implements IRebeldeControllerDocs {

    private final RebeldeService rebeldeService;

    private final RebeldeItemInventarioNegociacaoService negociacaoService;

    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<RebeldeResponse> adicionar(@Valid @RequestBody RebeldeCriarRequest request) {

        Rebelde rebelde = objectMapper.convertValue(request, Rebelde.class);

        Map<Long, Long> idQuantidadeItens = request.getItensInventario().stream().
                collect(Collectors.toMap(RebeldeItemInventarioRequest::getId, RebeldeItemInventarioRequest::getQuantidade));

        Rebelde rebeldeSalvo = rebeldeService.cadastrar(rebelde, idQuantidadeItens);

        log.info("Salvou rebelde com id %d", rebelde.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapper.convertValue(rebeldeSalvo, RebeldeResponse.class));
    }

    @PatchMapping("/{id}/localizacao")
    public ResponseEntity<RebeldeResponse> atualizarLocalizacao(@PathVariable Long id,
                                                                @Valid @RequestBody RebeldeLocalizacaoAtualizarRequest request) {
        Rebelde rebelde = Rebelde.builder()
                .id(id)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .nomeBaseGalaxia(request.getNomeBaseGalaxia())
                .build();

        rebelde = rebeldeService.atualizarLocalizacao(rebelde);

        log.info("Atualizou localização do rebelde com id %d", rebelde.getId());

        return ResponseEntity.ok(objectMapper.convertValue(rebelde, RebeldeResponse.class));
    }

    @PatchMapping("/{id}/reporte-traidor")
    public ResponseEntity<RebeldeResponse> reportarTraicao(@PathVariable Long id,
                                                           @Valid @RequestBody RebeldeIdRequest request) {

        RebeldeResponse rebeldeResponse = objectMapper.convertValue(
                rebeldeService.reportarTraicao(id, request.getRebeldeId()), RebeldeResponse.class);

        log.info("Reportada traição do rebelde com id %d", id);

        return ResponseEntity.ok(rebeldeResponse);
    }

    @PatchMapping("/{id}/negociar-itens")
    public ResponseEntity<Rebelde> negociarItens(@PathVariable Long id,
                                                 @Valid @RequestBody RebeldeItemInventarioNegociarRequest request) {

        negociacaoService.negociarItens(id, objectMapper.convertValue(request, RebeldeItemInventarioNegociar.class));

        log.info("Negociação de itens realizada com sucesso entre os rebeldes com id %d e %d ",
                id, request.getRebeldeIdDestino());

        return ResponseEntity.ok().build();
    }
}
