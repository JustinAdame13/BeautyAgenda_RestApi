
CREATE TABLE servicio (
                          id BIGSERIAL PRIMARY KEY,
                          nombre VARCHAR(150) NOT NULL UNIQUE,
                          duracion_min INTEGER NOT NULL,
                          precio NUMERIC(10,2) NOT NULL,
                          tipo VARCHAR(30) NOT NULL CHECK (tipo IN ('corte', 'color', 'peinado_tratamiento', 'cejas_pestanas', 'unas_manos', 'unas_pies')),
                          descripcion TEXT
);

-- Cortes
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Corte de Dama', 45, 200.00, 'corte', 'Corte personalizado según la forma de tu rostro, textura y estilo de vida. Incluye lavado y peinado básico'),
                                                                           ('Corte de Caballero', 30, 150.00, 'corte', 'Corte clásico o moderno con acabado preciso en máquina y tijera'),
                                                                           ('Corte de Niño', 30, 100.00, 'corte', 'Corte cómodo y rápido, ideal para los más pequeños de la casa');

-- Color & Mechas
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Aplicación de Tinte (tinte propio)', 60, 100.00, 'color', 'Aplicación profesional usando el tinte que tú traigas. Solo mano de obra, no incluye producto ni estilizado'),
                                                                           ('Tinte Global (c. corto)', 105, 400.00, 'color', 'Color uniforme de raíz a puntas. Precio aumenta según largo y volumen. Incluye producto, lavado y secado'),
                                                                           ('Retoque de Raíz (hasta 1.5 cm)', 60, 250.00, 'color', 'Cubre el crecimiento natural manteniendo el tono uniforme. Incluye producto y lavado'),
                                                                           ('Mechas y Rayos (c. corto)', 135, 600.00, 'color', 'Iluminación parcial con papel o gorro. Precio aumenta según largo y volumen. Incluye matizado, lavado y secado'),
                                                                           ('Balayage y Babylight', 210, 900.00, 'color', 'Técnica de iluminación a mano alzada. Precio aumenta según largo y volumen. Incluye matizado, tratamiento post-color, lavado y secado');

-- Tratamientos & Peinados
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Tratamiento Extrahidratación', 53, 500.00, 'peinado_tratamiento', 'Restaura la hidratación y brillo del cabello dañado o reseco. Precio puede variar. Incluye producto sellado con calor'),
                                                                           ('Estilizado de Cabello', 35, 150.00, 'peinado_tratamiento', 'Peinado con secadora y cepillo para un acabado liso y con movimiento'),
                                                                           ('Planchado', 38, 200.00, 'peinado_tratamiento', 'Alisado temporal con plancha. Incluye protector térmico'),
                                                                           ('Ondas o Rulos', 45, 300.00, 'peinado_tratamiento', 'Peinado con ondas suaves o rizos definidos. Incluye fijador y protector térmico'),
                                                                           ('Peinado Elaborado', 75, 400.00, 'peinado_tratamiento', 'Peinado de evento con recogido y detalles personalizados'),
                                                                           ('Maquillaje Social + Peinado', 120, 1000.00, 'peinado_tratamiento', 'Paquete completo de maquillaje de evento y peinado a juego. Incluye pestañas postizas y sellador de maquillaje'),
                                                                           ('Maquillaje Social', 75, 800.00, 'peinado_tratamiento', 'Maquillaje profesional para fiestas, eventos o sesiones fotográficas. Incluye pestañas postizas');

-- Cejas & Pestañas
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Laminado y Perfilación de Ceja', 40, 250.00, 'cejas_pestanas', 'Cejas peinadas con dirección para un efecto voluminoso y prolijo. Incluye depilación'),
                                                                           ('Henna de Ceja', 30, 200.00, 'cejas_pestanas', 'Henna semipermanente para definir y oscurecer la ceja. Dura entre 2-3 semanas'),
                                                                           ('Henna + Laminado + Perfilación', 60, 350.00, 'cejas_pestanas', 'Combo completo en una sola cita. Incluye depilación'),
                                                                           ('Retiro de Pestañas Postizas', 25, 100.00, 'cejas_pestanas', 'Remoción segura de extensiones sin dañar la pestaña natural. Incluye limpieza posterior'),
                                                                           ('Pestañas Clásicas (pelo a pelo)', 90, 380.00, 'cejas_pestanas', 'Una extensión por cada pestaña natural, efecto natural y elegante. Duración aprox. 3 semanas'),
                                                                           ('Pestañas Híbridas', 105, 400.00, 'cejas_pestanas', 'Mezcla de técnica clásica y volumen. Duración aprox. 3 semanas'),
                                                                           ('Pestañas Hawaianas', 105, 400.00, 'cejas_pestanas', 'Estilo de volumen con curvatura pronunciada. Duración aprox. 3 semanas'),
                                                                           ('Pestañas Griego', 105, 400.00, 'cejas_pestanas', 'Efecto de volumen natural con densidad en las esquinas. Duración aprox. 3 semanas'),
                                                                           ('Volumen Tecno 5D/6D', 135, 550.00, 'cejas_pestanas', 'Múltiples extensiones por pestaña natural para máximo volumen. Duración aprox. 3-4 semanas');

-- Uñas de Manos
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Acrílico', 90, 280.00, 'unas_manos', 'Uñas esculpidas con acrílico, resistentes y de acabado duradero. Incluye esmaltado semipermanente'),
                                                                           ('Largo #1', 90, 300.00, 'unas_manos', 'Extensión corta, ideal para un look discreto y funcional. Incluye esmaltado semipermanente'),
                                                                           ('Largo #2', 105, 350.00, 'unas_manos', 'Extensión media-corta. Incluye esmaltado semipermanente'),
                                                                           ('Largo #3', 120, 400.00, 'unas_manos', 'Extensión media, equilibrio entre estilo y comodidad diaria. Incluye esmaltado semipermanente'),
                                                                           ('Largo #4', 120, 450.00, 'unas_manos', 'Extensión media-larga para un look más llamativo. Incluye esmaltado semipermanente'),
                                                                           ('Largo #5', 135, 500.00, 'unas_manos', 'Extensión larga, ideal para diseños elaborados. Incluye esmaltado semipermanente'),
                                                                           ('Largo #6', 150, 550.00, 'unas_manos', 'Extensión extra larga para un estilo dramático. Incluye esmaltado semipermanente'),
                                                                           ('Gelish Semipermanente (1-2 colores)', 45, 150.00, 'unas_manos', 'Esmaltado de larga duración sobre uña natural, hasta 2 colores. Duración aprox. 2-3 semanas');

-- Uñas de Pies
INSERT INTO servicio (nombre, duracion_min, precio, tipo, descripcion) VALUES
                                                                           ('Pedicura con Esmaltado', 60, 400.00, 'unas_pies', 'Pedicura completa con exfoliación, corte y esmaltado tradicional. Incluye masaje relajante'),
                                                                           ('Esmalte Semipermanente', 30, 150.00, 'unas_pies', 'Esmaltado de larga duración solo, sin pedicura completa. Duración aprox. 3 semanas'),
                                                                           ('Acrílico en Pies', 60, 200.00, 'unas_pies', 'Uñas esculpidas en acrílico para pies, resistentes y parejas. Incluye esmaltado semipermanente'),
                                                                           ('Quitar Producto de Uñas', 25, 100.00, 'unas_pies', 'Retiro seguro de acrílico, polygel o gel sin dañar la uña natural. Incluye hidratación posterior');