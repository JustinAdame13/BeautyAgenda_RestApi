package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Marias.BeautyAgenda.entity.enums.TipoServicio;
import org.Marias.BeautyAgenda.entity.enums.TipoServicioConverter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false, length = 150, unique = true)
    private String nombre;
    @Column(name = "duracion_min", nullable = false)
    private Integer duracion;
    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
    //valor especial
    @Convert(converter = TipoServicioConverter.class)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoServicio tipo;

    @Column(name = "descripcion")
    private String descripcion;

    //relacion muchos a muchos con plantilla
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "servicio_plantilla",
            joinColumns = @JoinColumn(name = "id_servicio"),
            inverseJoinColumns = @JoinColumn(name = "id_plantilla")
    )
    private Set<PlantillaMensaje> plantillas = new HashSet<>();


}
