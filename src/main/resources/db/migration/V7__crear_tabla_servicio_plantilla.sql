CREATE TABLE servicio_plantilla (
                                    id_servicio BIGINT NOT NULL,
                                    id_plantilla BIGINT NOT NULL,
                                    PRIMARY KEY (id_servicio, id_plantilla),
                                    FOREIGN KEY (id_servicio) REFERENCES servicio(id),
                                    FOREIGN KEY (id_plantilla) REFERENCES plantilla(id)
);