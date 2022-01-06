package br.com.marcia.starwars.repository;

import br.com.marcia.starwars.entity.RebeldeInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeInventarioRepository extends JpaRepository<RebeldeInventarioEntity, Long> {

    RebeldeInventarioEntity findByRebeldeId(Long rebeldeId);
}