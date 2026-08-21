
CREATE TABLE clienta (
                         id BIGSERIAL PRIMARY KEY,
                         nombre VARCHAR(150) NOT NULL,
                         telefono VARCHAR(20) NOT NULL UNIQUE,
                         fecha_nacimiento DATE,
                         opt_in_recordatorios BOOLEAN NOT NULL DEFAULT true,
                         opt_in_marketing BOOLEAN NOT NULL DEFAULT false,
                         notas TEXT,
                         fecha_registro TIMESTAMP NOT NULL DEFAULT now()
);