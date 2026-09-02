CREATE TABLE plantilla (
                           id BIGSERIAL PRIMARY KEY,
                           tipo VARCHAR(30) NOT NULL CHECK (tipo IN ('recordatorio', 'seguimiento', 'cumple')),
                           nombre_meta VARCHAR(150) NOT NULL UNIQUE
);