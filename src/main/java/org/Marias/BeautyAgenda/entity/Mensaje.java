package org.Marias.BeautyAgenda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensajeConverter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "mensaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clienta", nullable = false)
    private Clienta clienta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", nullable = true)
    private Cita cita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plantilla", nullable = false)
    private PlantillaMensaje plantilla;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @Convert(converter = EstadoMensajeConverter.class)
    @Column(name = "estado", nullable = false)
    private EstadoMensaje estado;

    @Column(name = "intentos", nullable = false)
    private Integer intentos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "parametros", columnDefinition = "jsonb")
    private Map<String, String> parametros;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}
