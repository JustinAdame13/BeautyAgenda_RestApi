package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.TipoServicio;
import org.Marias.BeautyAgenda.entity.enums.TipoServicioConverter;

import java.math.BigDecimal;

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


}
