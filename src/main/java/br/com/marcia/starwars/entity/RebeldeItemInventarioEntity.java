package br.com.marcia.starwars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "rebelde_item_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebeldeItemInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_inventario_id")
    private ItemInventarioEntity itemInventario;

    @ManyToOne
    @JoinColumn(name = "rebelde_inventario_id")
    @JsonBackReference
    private RebeldeInventarioEntity rebeldeInventario;

    @Column(nullable = false)
    private Long quantidade;
}
