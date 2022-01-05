package br.com.marcia.starwars.service;

import br.com.marcia.starwars.utils.CalculoUtils;
import br.com.marcia.starwars.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelatorioService {

    private final RebeldeService rebeldeService;

    private final RebeldeItemInventarioService rebeldeItemInventarioService;

    public String porcentagemTraidores() {
        Long quantidadeTraidores = rebeldeService.getQuantidadeRebeldesPorTraidor(Boolean.TRUE);
        Long quantidadeTotal = rebeldeService.getQuantidadeTotalRebeldes();

        Double porcentagem = CalculoUtils.calcularPorcentagem(quantidadeTraidores.doubleValue(), quantidadeTotal.doubleValue());

        return StringUtils.formatarValor(porcentagem);
    }

    public String porcentagemRebeldes() {
        Long quantidadeRebeldes = rebeldeService.getQuantidadeRebeldesPorTraidor(Boolean.FALSE);
        Long quantidadeTotal = rebeldeService.getQuantidadeTotalRebeldes();

        Double porcentagem = CalculoUtils.calcularPorcentagem(quantidadeRebeldes.doubleValue(), quantidadeTotal.doubleValue());

        return StringUtils.formatarValor(porcentagem);
    }

    public String contabilizarPontosPerdidosPorTraidores() {
        return StringUtils.formatarValor(rebeldeItemInventarioService.contabilizarPontosPerdidosPorTraidores());
    }

    public List<Object> calcularMediaRecursoPorRebelde() {
        return rebeldeItemInventarioService.calcularMediaRecursoPorRebelde();
    }
}
