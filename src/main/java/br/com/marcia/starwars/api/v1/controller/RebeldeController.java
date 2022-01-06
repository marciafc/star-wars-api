package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.interfaces.IRebeldeControllerDocs;
import br.com.marcia.starwars.api.v1.request.*;
import br.com.marcia.starwars.api.v1.response.RebeldeResponse;
import br.com.marcia.starwars.domain.*;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
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
    public ResponseEntity<Rebelde> atualizarLocalizacao(@PathVariable Long id,
                                                        @RequestBody RebeldeLocalizacaoAtualizarRequest request) {

        try {
            Rebelde rebelde = rebeldeService.buscar(id);

            rebelde.setLatitude(request.getLatitude());
            rebelde.setLongitude(request.getLongitude());
            rebelde.setNomeBaseGalaxia(request.getNomeBaseGalaxia());

            return ResponseEntity.ok(rebeldeService.atualizar(rebelde));

        } catch (RebeldeNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/reporte-traidor")
    public ResponseEntity<Rebelde> reportarTraicao(@PathVariable Long id, @RequestBody RebeldeIdRequest request) {
        return ResponseEntity.ok(rebeldeService.reportarTraicao(id, request.getRebeldeId()));
    }

    @PatchMapping("/{id}/negociar-itens")
    public ResponseEntity<Rebelde> negociarItens(@PathVariable Long id,
                                                 @RequestBody RebeldeItemInventarioNegociarRequest request) {

        return ResponseEntity.notFound().build();
    }
}
