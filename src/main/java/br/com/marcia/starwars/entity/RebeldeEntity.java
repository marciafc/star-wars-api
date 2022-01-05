package br.com.marcia.starwars.entity;

import br.com.marcia.starwars.enumeration.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "rebelde")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebeldeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long idade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroEnum genero;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, name = "nome_base_galaxia")
    private String nomeBaseGalaxia;

    @Column(nullable = false)
    private Boolean traidor;

    @ManyToMany
    @JoinTable(name = "reporte_rebelde_traidor",
            joinColumns = {@JoinColumn(name = "rebelde_traidor_id")},
            inverseJoinColumns = {@JoinColumn(name = "rebelde_relator_id")}
    )
    private Set<RebeldeEntity> reporteTraicoes = new HashSet<>();
}
