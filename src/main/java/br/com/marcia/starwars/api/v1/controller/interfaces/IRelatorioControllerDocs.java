package br.com.marcia.starwars.api.v1.controller.interfaces;

import br.com.marcia.starwars.api.v1.response.MediaRecursoPorRebeldeResponse;
import br.com.marcia.starwars.api.v1.response.ResultadoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRelatorioControllerDocs {

    @ApiOperation(value = "Retornar o % de traidores")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Porcentagem de traidores"),
    })
    ResponseEntity<ResultadoResponse> porcentagemTraidores();

    @ApiOperation(value = "Retornar o % de rebeldes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Porcentagem de rebeldes"),
    })
    ResponseEntity<ResultadoResponse> porcentagemRebeldes();

    @ApiOperation(value = "Retornar a quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Quantidade média de cada tipo de recurso por rebelde"),
    })
    ResponseEntity<List<MediaRecursoPorRebeldeResponse>> quantidadeMediaRecursoPorRebelde();

    @ApiOperation(value = "Retornar a quantidade de pontos perdidos devido a traidores")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pontos perdidos devido a traidores"),
    })
    ResponseEntity<ResultadoResponse> contabilizarPontosPerdidosPorTraidores();
}