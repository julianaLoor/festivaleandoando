
-- Script SQL actualizado para la base de datos "festivaleandoando" con mejoras implementadas

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS festivaleandoando;
USE festivaleandoando;

-- Tabla Tipos
CREATE TABLE Tipos (
    idTipo INT AUTO_INCREMENT PRIMARY KEY,
    nombreTipo VARCHAR(50) NOT NULL
);

-- Tabla Usuarios
CREATE TABLE Usuarios (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL CHECK (CHAR_LENGTH(contraseña) BETWEEN 6 AND 20 AND contraseña REGEXP '[0-9]' AND contraseña REGEXP '[!@#$%^&*(),.?":{}|<>]'),
    fechaNacimiento DATE NOT NULL CHECK (TIMESTAMPDIFF(YEAR, fechaNacimiento, CURDATE()) >= 18),
    idTipo INT NOT NULL,
    FOREIGN KEY (idTipo) REFERENCES Tipos(idTipo)
);

-- Tabla Recintos
CREATE TABLE Recintos (
    idRecinto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    capacidad INT NOT NULL
);

-- Tabla Categoria_Fest
CREATE TABLE Categoria_Fest (
    idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Eventos
CREATE TABLE Eventos (
    idEvento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    fechaHora DATETIME NOT NULL,
    entradasDisponibles INT NOT NULL,
    idRecinto INT NOT NULL,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idRecinto) REFERENCES Recintos(idRecinto) ON DELETE CASCADE,
    FOREIGN KEY (idCategoria) REFERENCES Categoria_Fest(idCategoria) ON DELETE CASCADE
);

-- Tabla Acciones_Eventos
CREATE TABLE Acciones_Eventos (
    idAccion INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    idEvento INT NOT NULL,
    tipoAccion ENUM('selecciona', 'organiza', 'edita') NOT NULL,
    fechaAccion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES Usuarios(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento) ON DELETE CASCADE
);

-- Tabla Entradas
CREATE TABLE Entradas (
    idEntrada INT AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(10, 2) NOT NULL,
    estado ENUM('disponible', 'vendida') DEFAULT 'disponible',
    idEvento INT NOT NULL,
    FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento) ON DELETE CASCADE
);

-- Tabla Compras
CREATE TABLE Compras (
    idCompra INT AUTO_INCREMENT PRIMARY KEY,
    fechaCompra DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    estado ENUM('pendiente', 'pagado') DEFAULT 'pendiente',
    idUser INT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES Usuarios(idUser) ON DELETE CASCADE
);

-- Tabla Detalle_Compra
CREATE TABLE Detalle_Compra (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idCompra INT NOT NULL,
    idEntrada INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad <= 5),
    precioUnitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idCompra) REFERENCES Compras(idCompra) ON DELETE CASCADE,
    FOREIGN KEY (idEntrada) REFERENCES Entradas(idEntrada) ON DELETE CASCADE
);

-- Tabla Carrito
CREATE TABLE Carrito (
    idCarrito INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    Estado ENUM('activo', 'finalizado') DEFAULT 'activo',
    FOREIGN KEY (idUser) REFERENCES Usuarios(idUser) ON DELETE CASCADE
);

-- Tabla intermedia Carrito_Entradas
CREATE TABLE Carrito_Entradas (
    idCarrEntrada INT AUTO_INCREMENT PRIMARY KEY,
    idCarrito INT NOT NULL,
    idEntrada INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad <= 5),
    FOREIGN KEY (idCarrito) REFERENCES Carrito(idCarrito) ON DELETE CASCADE,
    FOREIGN KEY (idEntrada) REFERENCES Entradas(idEntrada) ON DELETE CASCADE
);

-- Trigger para actualizar entradas disponibles en Eventos al confirmar compra
DELIMITER $$
CREATE TRIGGER actualizar_entradas_disponibles
AFTER INSERT ON Detalle_Compra
FOR EACH ROW
BEGIN
    UPDATE Eventos
    SET entradasDisponibles = entradasDisponibles - NEW.cantidad
    WHERE idEvento = (SELECT idEvento FROM Entradas WHERE idEntrada = NEW.idEntrada);
END$$
DELIMITER ;
