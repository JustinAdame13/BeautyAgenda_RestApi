-- Agregamos dias_offset a plantilla, solo aplica para plantillas tipo 'seguimiento'
ALTER TABLE plantilla ADD COLUMN dias_offset INTEGER;

ALTER TABLE plantilla ADD CONSTRAINT chk_dias_offset_solo_seguimiento
    CHECK (
        (tipo = 'seguimiento' AND dias_offset IS NOT NULL)
            OR
        (tipo != 'seguimiento' AND dias_offset IS NULL)
        );

-- Tabla de mensajes programados
CREATE TABLE mensaje (
                         id BIGSERIAL PRIMARY KEY,
                         id_clienta BIGINT NOT NULL,
                         id_cita BIGINT, -- nullable: cumpleaños no tiene cita
                         id_plantilla BIGINT NOT NULL,
                         fecha_programada DATE NOT NULL, -- el día que debe enviarse (siempre a las 8pm)
                         estado VARCHAR(20) NOT NULL DEFAULT 'programado' CHECK (estado IN ('programado', 'enviado', 'fallido')),
                         intentos INTEGER NOT NULL DEFAULT 0,
                         parametros JSONB,
                         fecha_envio TIMESTAMP, -- se llena cuando realmente se envía
                         FOREIGN KEY (id_clienta) REFERENCES clienta(id),
                         FOREIGN KEY (id_cita) REFERENCES cita(id),
                         FOREIGN KEY (id_plantilla) REFERENCES plantilla(id)
);

-- Índice para el job programado que consulta mensajes pendientes por fecha
CREATE INDEX idx_mensaje_estado_fecha ON mensaje (estado, fecha_programada);