package br.com.marcia.starwars.api.v1.controller.interfaces;

import br.com.marcia.starwars.api.v1.response.ItemInventarioResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IItemInventarioControllerDocs {

    @ApiOperation(value = "Retornar uma lista de itens de inventário com a respectiva pontuação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de itens de inventário com a respectiva pontuação"),
    })
    ResponseEntity<List<ItemInventarioResponse>> listar();
}
