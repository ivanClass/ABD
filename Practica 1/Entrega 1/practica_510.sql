-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-02-2016 a las 19:56:45
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `practica_510`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aficiones`
--

CREATE TABLE `aficiones` (
  `IdAficcion` int(11) NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `aficiones`
--

INSERT INTO `aficiones` (`IdAficcion`, `Descripcion`) VALUES
(3, 'Me gusta el fútbol'),
(4, 'Me gustan los animales'),
(5, 'Me gusta jugar a los videojuegos'),
(6, 'Me gustan el mundo del motor'),
(7, 'Me gusta salir');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conoce`
--

CREATE TABLE `conoce` (
  `CorreoUsu` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `CorreoAmistad` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `conoce`
--

INSERT INTO `conoce` (`CorreoUsu`, `CorreoAmistad`) VALUES
('Bibliokiller', 'daniel10@ucm.es'),
('daniel10@ucm.es', 'Bibliokiller'),
('daniel10@ucm.es', 'ivanag01@ucm.es'),
('ivanag01@ucm.es', 'daniel10@ucm.es'),
('ivanag01@ucm.es', 'tere324@gmail.es'),
('tere324@gmail.es', 'ivanag01@ucm.es');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contestan`
--

CREATE TABLE `contestan` (
  `Correo` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `IdRespuesta` int(11) NOT NULL,
  `Relevancia` enum('0','1','2','3','4','5','6','7','8','9','10') COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `contestan`
--

INSERT INTO `contestan` (`Correo`, `IdRespuesta`, `Relevancia`) VALUES
('daniel10@ucm.es', 5, '7');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envia`
--

CREATE TABLE `envia` (
  `correoUsuEmisor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `correoUsuReceptor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `idMensaje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `envia`
--

INSERT INTO `envia` (`correoUsuEmisor`, `correoUsuReceptor`, `idMensaje`) VALUES
('Bibliokiller', 'daniel10@ucm.es', 1),
('Bibliokiller', 'daniel10@ucm.es', 2),
('Bibliokiller', 'daniel10@ucm.es', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `IdMensaje` int(11) NOT NULL,
  `Leido` tinyint(1) NOT NULL,
  `FechaYHora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`IdMensaje`, `Leido`, `FechaYHora`) VALUES
(1, 0, '2016-02-27 18:32:17'),
(2, 0, '2016-02-27 18:35:50'),
(3, 0, '2016-02-27 18:39:39');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mtipopetamistad`
--

CREATE TABLE `mtipopetamistad` (
  `IdMensaje` int(11) NOT NULL,
  `Texto` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `Respuesta` enum('SI','NO') COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mtipopetamistad`
--

INSERT INTO `mtipopetamistad` (`IdMensaje`, `Texto`, `Respuesta`) VALUES
(2, 'Quieres ser mi amigo?', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mtipopregunta`
--

CREATE TABLE `mtipopregunta` (
  `IdMensaje` int(11) NOT NULL,
  `IdPregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mtipopregunta`
--

INSERT INTO `mtipopregunta` (`IdMensaje`, `IdPregunta`) VALUES
(3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mtipotexto`
--

CREATE TABLE `mtipotexto` (
  `IdMensaje` int(11) NOT NULL,
  `Texto` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mtipotexto`
--

INSERT INTO `mtipotexto` (`IdMensaje`, `Texto`) VALUES
(1, 'hola que tal te va todo¿?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `IdPregunta` int(11) NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`IdPregunta`, `Descripcion`) VALUES
(3, '¿Donde vives?'),
(4, '¿Chocolate favorito?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `IdRespuesta` int(11) NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci NOT NULL,
  `IdPregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `respuestas`
--

INSERT INTO `respuestas` (`IdRespuesta`, `Descripcion`, `IdPregunta`) VALUES
(5, 'Blanco', 4),
(6, 'Rosa', 4),
(7, 'Azul', 4),
(8, 'Negro', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tieneafic`
--

CREATE TABLE `tieneafic` (
  `Correo` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `IdAficion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tieneafic`
--

INSERT INTO `tieneafic` (`Correo`, `IdAficion`) VALUES
('Bibliokiller', 5),
('daniel10@ucm.es', 7),
('ivanag01@ucm.es', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Correo` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Password` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `FotoPerfil` blob,
  `Genero` enum('Hombre','Mujer') COLLATE utf8_spanish_ci NOT NULL,
  `Interes` enum('Hombre','Mujer','Ambos') COLLATE utf8_spanish_ci NOT NULL,
  `Latitud` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `Longitud` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Usuarios de la red social';

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Correo`, `Nombre`, `Password`, `Descripcion`, `FechaNacimiento`, `FotoPerfil`, `Genero`, `Interes`, `Latitud`, `Longitud`) VALUES
('Bibliokiller', 'Rosa', '3456314', 'que vas a cobrar!', '1980-01-27', '', 'Mujer', 'Hombre', '10E', '40N'),
('daniel10@ucm.es', 'Daniel', '123456789', ':o', '1995-05-09', '', 'Hombre', 'Mujer', '10E', '30O'),
('ivanag01@ucm.es', 'Iván', '123456789', 'Hola.', '1995-03-10', '', 'Hombre', 'Mujer', '50E', '10N'),
('pepe@gmail.com', 'pepe', '12356', NULL, NULL, NULL, 'Hombre', 'Hombre', '10E', '30N'),
('tere324@gmail.es', 'Teresa', '123456789', 'Hi there, im using whatsapp', '1995-02-28', '', 'Mujer', 'Ambos', '30O', '10S');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aficiones`
--
ALTER TABLE `aficiones`
  ADD PRIMARY KEY (`IdAficcion`) USING BTREE;

--
-- Indices de la tabla `conoce`
--
ALTER TABLE `conoce`
  ADD PRIMARY KEY (`CorreoUsu`,`CorreoAmistad`),
  ADD KEY `ClForAmigo` (`CorreoAmistad`);

--
-- Indices de la tabla `contestan`
--
ALTER TABLE `contestan`
  ADD PRIMARY KEY (`Correo`,`IdRespuesta`),
  ADD KEY `ClaveForRespuesta` (`IdRespuesta`);

--
-- Indices de la tabla `envia`
--
ALTER TABLE `envia`
  ADD PRIMARY KEY (`correoUsuEmisor`,`correoUsuReceptor`,`idMensaje`),
  ADD KEY `ClaveForUsuEnvRecep` (`correoUsuReceptor`),
  ADD KEY `ClaveForIdEnv` (`idMensaje`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`IdMensaje`);

--
-- Indices de la tabla `mtipopetamistad`
--
ALTER TABLE `mtipopetamistad`
  ADD PRIMARY KEY (`IdMensaje`);

--
-- Indices de la tabla `mtipopregunta`
--
ALTER TABLE `mtipopregunta`
  ADD PRIMARY KEY (`IdMensaje`),
  ADD KEY `IdPregunta` (`IdPregunta`);

--
-- Indices de la tabla `mtipotexto`
--
ALTER TABLE `mtipotexto`
  ADD PRIMARY KEY (`IdMensaje`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`IdPregunta`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`IdRespuesta`),
  ADD KEY `IdPregunta` (`IdPregunta`);

--
-- Indices de la tabla `tieneafic`
--
ALTER TABLE `tieneafic`
  ADD PRIMARY KEY (`Correo`,`IdAficion`),
  ADD KEY `ClaveForAfic` (`IdAficion`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aficiones`
--
ALTER TABLE `aficiones`
  MODIFY `IdAficcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `IdMensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `IdPregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  MODIFY `IdRespuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `conoce`
--
ALTER TABLE `conoce`
  ADD CONSTRAINT `ClForAmigo` FOREIGN KEY (`CorreoAmistad`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClForUsu` FOREIGN KEY (`CorreoUsu`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `contestan`
--
ALTER TABLE `contestan`
  ADD CONSTRAINT `ClaveForRespuesta` FOREIGN KEY (`IdRespuesta`) REFERENCES `respuestas` (`IdRespuesta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClaveForUsuPreg` FOREIGN KEY (`Correo`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `envia`
--
ALTER TABLE `envia`
  ADD CONSTRAINT `ClaveForIdEnv` FOREIGN KEY (`idMensaje`) REFERENCES `mensajes` (`IdMensaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClaveForUsuEnvEmis` FOREIGN KEY (`correoUsuEmisor`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClaveForUsuEnvRecep` FOREIGN KEY (`correoUsuReceptor`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mtipopetamistad`
--
ALTER TABLE `mtipopetamistad`
  ADD CONSTRAINT `ClForIdMensajePetAmist` FOREIGN KEY (`IdMensaje`) REFERENCES `mensajes` (`IdMensaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mtipopregunta`
--
ALTER TABLE `mtipopregunta`
  ADD CONSTRAINT `ClForIdPregMens` FOREIGN KEY (`IdMensaje`) REFERENCES `mensajes` (`IdMensaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClForIdPregMens2` FOREIGN KEY (`IdPregunta`) REFERENCES `preguntas` (`IdPregunta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mtipotexto`
--
ALTER TABLE `mtipotexto`
  ADD CONSTRAINT `ClForIdMensajeTxto` FOREIGN KEY (`IdMensaje`) REFERENCES `mensajes` (`IdMensaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `ClForIdPreg` FOREIGN KEY (`IdPregunta`) REFERENCES `preguntas` (`IdPregunta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tieneafic`
--
ALTER TABLE `tieneafic`
  ADD CONSTRAINT `ClaveForAfic` FOREIGN KEY (`IdAficion`) REFERENCES `aficiones` (`IdAficcion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ClaveForUsu` FOREIGN KEY (`Correo`) REFERENCES `usuarios` (`Correo`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
