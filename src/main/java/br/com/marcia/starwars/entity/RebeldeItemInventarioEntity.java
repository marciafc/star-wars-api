package br.com.marcia.starwars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "rebelde_item_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RebeldeItemInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_inventario_id")
    private ItemInventarioEntity itemInventario;

    @ManyToOne
    @JoinColumn(name = "rebelde_inventario_id")
    @JsonBackReference
    @EqualsAndHashCode.Include
    private RebeldeInventarioEntity rebeldeInventario;

    @Column(nullable = false)
    private Long quantidade;
}
