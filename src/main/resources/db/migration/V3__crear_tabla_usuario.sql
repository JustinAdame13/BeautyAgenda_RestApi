CREATE TABLE usuario (
                         id BIGSERIAL PRIMARY KEY,
                         username VARCHAR(150) NOT NULL UNIQUE,
                         password_hash VARCHAR(100) NOT NULL,
                         rol VARCHAR(30) NOT NULL CHECK (rol IN ('admin', 'jefa', 'empleada')),
                         activo BOOLEAN NOT NULL DEFAULT true
);