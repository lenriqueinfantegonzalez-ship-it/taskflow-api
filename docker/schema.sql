-- ---------------------------------------------------
-- 1. LIMPIEZA: Eliminar tablas en orden inverso de dependencia (para desarrollo)
-- ---------------------------------------------------

DROP TABLE IF EXISTS FICHAJE;
DROP TABLE IF EXISTS COMENTARIO;
DROP TABLE IF EXISTS USUARIO_TAREA;
DROP TABLE IF EXISTS USUARIO_PROYECTO;
DROP TABLE IF EXISTS TAREA;
DROP TABLE IF EXISTS PROYECTO;
DROP TABLE IF EXISTS USUARIO;
DROP TABLE IF EXISTS ROL;

-- ---------------------------------------------------
-- 2. Base de datos: TaskFlow Solutions - CREACIÓN DE TABLAS
-- ---------------------------------------------------

-- ROL
CREATE TABLE ROL (
  id_rol SERIAL PRIMARY KEY,
  nombre_rol VARCHAR(50),
  descripcion TEXT
);

-- USUARIO
CREATE TABLE USUARIO (
  id_usuario SERIAL PRIMARY KEY,
  nombre VARCHAR(100),
  apellido VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  telefono VARCHAR(20),
  contrasena_hash VARCHAR(255),
  activo BOOLEAN,
  id_rol INT REFERENCES ROL(id_rol)
);

-- PROYECTO
CREATE TABLE PROYECTO (
  id_proyecto SERIAL PRIMARY KEY,
  nombre VARCHAR(150),
  descripcion TEXT,
  fecha_inicio DATE,
  fecha_fin DATE,
  estado VARCHAR(20),
  id_responsable INT REFERENCES USUARIO(id_usuario)
);

-- TAREA
CREATE TABLE TAREA (
  id_tarea SERIAL PRIMARY KEY,
  id_proyecto INT REFERENCES PROYECTO(id_proyecto),
  titulo VARCHAR(150),
  descripcion TEXT,
  prioridad VARCHAR(20),
  estado VARCHAR(20),
  fecha_creacion TIMESTAMP,
  fecha_limite DATE
);

-- USUARIO_PROYECTO (Tabla de unión Many-to-Many)
CREATE TABLE USUARIO_PROYECTO (
  id_usuario INT REFERENCES USUARIO(id_usuario),
  id_proyecto INT REFERENCES PROYECTO(id_proyecto),
  PRIMARY KEY (id_usuario, id_proyecto)
);

-- USUARIO_TAREA (Tabla de unión Many-to-Many)
CREATE TABLE USUARIO_TAREA (
  id_usuario INT REFERENCES USUARIO(id_usuario),
  id_tarea INT REFERENCES TAREA(id_tarea),
  PRIMARY KEY (id_usuario, id_tarea)
);

-- COMENTARIO
CREATE TABLE COMENTARIO (
  id_comentario SERIAL PRIMARY KEY,
  id_tarea INT REFERENCES TAREA(id_tarea),
  id_usuario INT REFERENCES USUARIO(id_usuario),
  texto TEXT,
  fecha_hora TIMESTAMP
);

-- FICHAJE
CREATE TABLE FICHAJE (
  id_fichaje SERIAL PRIMARY KEY,
  id_usuario INT REFERENCES USUARIO(id_usuario),
  fecha DATE,
  hora_entrada TIME,
  hora_salida TIME,
  horas_totales DECIMAL(5,2)
);

-- ---------------------------------------------------
-- 3. DATOS INICIALES (Opcional, pero útil para pruebas)
-- ---------------------------------------------------

INSERT INTO ROL (nombre_rol, descripcion) VALUES
('ADMIN', 'Administrador del sistema'),
('MANAGER', 'Gestor de proyectos'),
('DEVELOPER', 'Desarrollador asignado a tareas');

INSERT INTO USUARIO (nombre, apellido, email, telefono, contrasena_hash, activo, id_rol) VALUES
('Luis', 'Enrique', 'luis.enrique@test.com', '123456789', 'hash123', TRUE, 1),
('Maria', 'Lopez', 'maria.lopez@test.com', '987654321', 'hash456', TRUE, 2);