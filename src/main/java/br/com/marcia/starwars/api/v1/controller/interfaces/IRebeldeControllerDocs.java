package br.com.marcia.starwars.api.v1.controller.interfaces;

import br.com.marcia.starwars.api.v1.request.RebeldeCriarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeIdRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeItemInventarioNegociarRequest;
import br.com.marcia.starwars.api.v1.request.RebeldeLocalizacaoAtualizarRequest;
import br.com.marcia.starwars.api.v1.response.RebeldeResponse;
import br.com.marcia.starwars.domain.Rebelde;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRebeldeControllerDocs {

    @ApiOperation(value = "Salvar um rebelde")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Rebelde criado com sucesso"),
            @ApiResponse(code = 400, message = "Campo obrigatório não preenchido ou inválido")
    })
    ResponseEntity<RebeldeResponse> adicionar(RebeldeCriarRequest request);

    @ApiOperation(value = "Atualizar a localização do rebelde")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Localização do rebelde atualizada com sucesso")
    })
    ResponseEntity<Rebelde> atualizarLocalizacao(@PathVariable Long id, @RequestBody RebeldeLocalizacaoAtualizarRequest request);

    @ApiOperation(value = "Reportar um rebelde como traidor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reporte da traição realizada com sucesso"),
            @ApiResponse(code = 404, message = "Rebelde não existente")
    })
    ResponseEntity<Rebelde> reportarTraicao(@PathVariable Long id, @RequestBody RebeldeIdRequest rebeldeIdRequest);

    @ApiOperation(value = "Negociar item entre os rebeldes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Negociação de itens realizada com sucesso"),
            @ApiResponse(code = 404, message = "Rebelde não existente")
    })
    ResponseEntity<Rebelde> negociarItens(@PathVariable Long id, @RequestBody RebeldeItemInventarioNegociarRequest request);
}
