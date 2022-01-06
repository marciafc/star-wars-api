package br.com.marcia.starwars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "rebelde_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebeldeInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "rebeldeInventario", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<RebeldeItemInventarioEntity> itemsInventario;

    @Column(nullable = false)
    private Boolean acessivel;

    @OneToOne
    @JoinColumn(name = "rebelde_id", referencedColumnName = "id")
    @JsonBackReference
    private RebeldeEntity rebelde;
}
