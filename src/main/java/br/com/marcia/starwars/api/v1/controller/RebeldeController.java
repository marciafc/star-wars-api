package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.interfaces.IRebeldeControllerDocs;
import br.com.marcia.starwars.api.v1.request.RebeldeCriarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeIdRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioNegociarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeLocalizacaoAtualizarRequest;
import br.com.marcia.starwars.domain.*;
import br.com.marcia.starwars.exception.RebeldeNaoEncontradoException;
import br.com.marcia.starwars.service.ItemInventarioService;
import br.com.marcia.starwars.service.RebeldeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/rebeldes")
@AllArgsConstructor
public class RebeldeController implements IRebeldeControllerDocs {

    private final RebeldeService rebeldeService;

    private final ItemInventarioService itemInventarioService;

    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Rebelde> adicionar(@RequestBody RebeldeCriarRequest request) {

        Rebelde rebelde = objectMapper.convertValue(request, Rebelde.class);

        /*Map<Long, Long> map = request.getItensInventario().stream().
                collect(Collectors.toMap(RebeldeItemInventarioRequest::getId, RebeldeItemInventarioRequest::getQuantidade));*/

        Rebelde rebeldeSalvo = rebeldeService.cadastrar(rebelde);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rebeldeSalvo);
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

    private void prepararSalvarItens(Rebelde rebelde, Map<Long, Long> map) {

        List<ItemInventario> lista = itemInventarioService.listar();
        List<RebeldeItemInventario> rebeldeItem = new ArrayList<>();

        // TODO refatorar (e fazer para a lista toda)
        RebeldeItemInventario build = RebeldeItemInventario.builder().
                itemInventario(lista.get(0)).
                quantidade(map.get(lista.get(0).getId())).
                build();

        rebeldeItem.add(build);
        RebeldeInventario rebeldeInventario = RebeldeInventario.builder().build();
        rebelde.setRebeldeInventario(rebeldeInventario);
        rebelde.getRebeldeInventario().setItemsInventario(rebeldeItem);
    }
}
