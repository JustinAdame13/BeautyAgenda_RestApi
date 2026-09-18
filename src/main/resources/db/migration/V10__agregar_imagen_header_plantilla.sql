-- Agregar columna para URL de imagen del header en plantillas de WhatsApp
-- NOT NULL con valor por defecto para plantillas existentes que aún no tienen imagen
ALTER TABLE plantilla ADD COLUMN header_image_url VARCHAR(500) NOT NULL DEFAULT '';
