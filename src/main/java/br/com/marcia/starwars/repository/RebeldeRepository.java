package br.com.marcia.starwars.repository;

import br.com.marcia.starwars.entity.RebeldeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<RebeldeEntity, Long> {

    Long countByTraidor(boolean traidor);
}
