package br.com.marcia.starwars.api.v1.controller;

import br.com.marcia.starwars.api.v1.controller.interfaces.IRelatorioControllerDocs;
import br.com.marcia.starwars.api.v1.response.MediaRecursoPorRebeldeResponse;
import br.com.marcia.starwars.api.v1.response.ResultadoResponse;
import br.com.marcia.starwars.service.RelatorioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/relatorios")
@AllArgsConstructor
public class RelatorioController implements IRelatorioControllerDocs {

    private final RelatorioService relatorioService;

    private final ObjectMapper objectMapper;

    @GetMapping("/porcentagem-traidores")
    public ResponseEntity<ResultadoResponse> porcentagemTraidores() {

        ResultadoResponse resultado = ResultadoResponse.builder().
                resultado(relatorioService.porcentagemTraidores()).build();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/porcentagem-rebeldes")
    public ResponseEntity<ResultadoResponse> porcentagemRebeldes() {

        ResultadoResponse resultado = ResultadoResponse.builder().
                resultado(relatorioService.porcentagemRebeldes()).build();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/quantidade-media-recursos-por-rebelde")
    public ResponseEntity<List<MediaRecursoPorRebeldeResponse>> quantidadeMediaRecursoPorRebelde() {

        List<MediaRecursoPorRebeldeResponse> resultado = relatorioService.calcularMediaRecursoPorRebelde().stream()
                .map(item -> objectMapper.convertValue(item, MediaRecursoPorRebeldeResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/pontos-perdidos-por-traidores")
    public ResponseEntity<ResultadoResponse> contabilizarPontosPerdidosPorTraidores() {

        ResultadoResponse resultado = ResultadoResponse.builder().
                resultado(relatorioService.contabilizarPontosPerdidosPorTraidores()).build();

        return ResponseEntity.ok(resultado);
    }
}
