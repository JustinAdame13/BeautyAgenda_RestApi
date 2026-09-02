package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantillaConverter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plantilla")
@AllArgsConstructor
@NoArgsConstructor

public class PlantillaMensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TipoPlantillaConverter.class)
    @Column(name = "tipo", nullable = false)
    private TipoPlantilla tipo;

    @Column(name = "nombre_meta", nullable = false, length = 150, unique = true)
    private String nombreMeta;

    //relacion muchos a muchos con servicio
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "plantillas", fetch = FetchType.LAZY)
    private Set<Servicio> servicios = new HashSet<>();
}
