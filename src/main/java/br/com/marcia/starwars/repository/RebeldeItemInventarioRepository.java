package br.com.marcia.starwars.repository;

import br.com.marcia.starwars.entity.RebeldeItemInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebeldeItemInventarioRepository extends JpaRepository<RebeldeItemInventarioEntity, Long> {

    Double contabilizarPontosPerdidosPorTraidores();

    List<Object> calcularMediaRecursoPorRebelde();

}
