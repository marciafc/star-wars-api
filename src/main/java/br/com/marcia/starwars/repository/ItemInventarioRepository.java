package br.com.marcia.starwars.repository;

import br.com.marcia.starwars.entity.ItemInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInventarioRepository extends JpaRepository<ItemInventarioEntity, Long> {
}