CREATE TABLE cita (
                      id BIGSERIAL PRIMARY KEY,
                      id_clienta BIGINT NOT NULL,
                      id_empleada BIGINT NOT NULL,
                      inicio TIMESTAMP NOT NULL,
                      fin TIMESTAMP NOT NULL,
                      estado VARCHAR(30) NOT NULL DEFAULT 'confirmada' CHECK (estado IN ('confirmada', 'completada', 'cancelada')),
                      notas VARCHAR(150),
                      FOREIGN KEY (id_clienta) REFERENCES clienta(id),
                      FOREIGN KEY (id_empleada) REFERENCES empleada(id),
                      CHECK (fin > inicio)
);

CREATE TABLE cita_servicio (
                               id BIGSERIAL PRIMARY KEY,
                               id_cita BIGINT NOT NULL,
                               id_servicio BIGINT NOT NULL,
                               precio_cobrado NUMERIC(10,2) NOT NULL,
                               FOREIGN KEY (id_cita) REFERENCES cita(id) ON DELETE CASCADE,
                               FOREIGN KEY (id_servicio) REFERENCES servicio(id),
                               UNIQUE (id_cita, id_servicio)
);