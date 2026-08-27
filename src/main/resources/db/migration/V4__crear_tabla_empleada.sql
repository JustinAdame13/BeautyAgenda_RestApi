CREATE TABLE empleada (
                          id BIGSERIAL PRIMARY KEY,
                          id_usuario BIGINT NOT NULL UNIQUE,
                          nombre VARCHAR(50) NOT NULL UNIQUE,
                          activo BOOLEAN NOT NULL DEFAULT true,
                          FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);