package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantilla;
import org.Marias.BeautyAgenda.entity.enums.TipoPlantillaConverter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
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

    @Column(name = "dias_offset")
    private Integer diasOffset;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "orden_parametros", columnDefinition = "jsonb")
    private List<String> ordenParametros;

    @Column(name = "header_image_url", nullable = false, length = 500)
    private String headerImageUrl;

}
