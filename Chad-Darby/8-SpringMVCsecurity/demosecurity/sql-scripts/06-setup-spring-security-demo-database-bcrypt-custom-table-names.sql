USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `miembros`;

--
-- Table structure for table `members`
--

CREATE TABLE `usuarios` (
  `ID` int NOT NULL,
  `NOMBRE` varchar(50),
  `PRIMERAPELLIDO` varchar(50),
  `SEGUNDOAPELLIDO` varchar(50),
  `EDAD` int,
  `CURP` varchar(50),
  `CP` int,
  `RFC` varchar(50),
  `TELEFONO` varchar(50),
  `FECHA` varchar(50),
  `PASSWORD` char(68) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `miembros` (
  `id_usuario` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `activo` tinyint NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `miembros`
VALUES
('john','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('susan','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `id_usuario` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`id_usuario`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `miembros` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
