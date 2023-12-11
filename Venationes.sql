CREATE TABLE torneos (
  id INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(100)  NOT NULL,
  fecha_inico DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  disciplina VARCHAR(20) NOT NULL,
  numero_equipos INT NOT NULL,
  numero_grupos INT NOT NULL
);

CREATE TABLE jugadores (
	NUMERO_CUENTA		INT		 NOT NULL,
	NOMBRE				VARCHAR(100) NOT NULL,
	PRIMER_APELLIDO		VARCHAR(100) NOT NULL,
	SEGUNDO_APELLIDO	VARCHAR(100) NOT NULL,
	FECHA_NACIMIENTO	DATE	     NOT NULL,
	USUARIO				VARCHAR(100) NOT NULL,
	CORREO              VARCHAR(100) NOT NULL,
	CONTRASENA			VARCHAR(500) NOT NULL,
	CONSTRAINT PRIMARY_KEY PRIMARY KEY (NUMERO_CUENTA)
);

CREATE TABLE grupos (
  id      INT          NOT NULL,
  nombre  VARCHAR(100) NOT NULL,
  torneo  INT  	       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (torneo) REFERENCES torneos (id)
);

CREATE TABLE equipos (
  id      INT          NOT NULL,
  nombre  VARCHAR(100) NOT NULL,
  grupo   INT 	       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (grupo)  REFERENCES grupos (id)
);

CREATE TABLE jugadores_equipo (
  jugador INT   NOT NULL,
  equipo  INT 	NOT NULL,
  FOREIGN KEY (jugador)  REFERENCES jugadores (NUMERO_CUENTA),
  FOREIGN KEY (equipo)   REFERENCES equipos (id)
);

CREATE TABLE jornadas (
  id        INT  NOT NULL,
  local     INT  NOT NULL,
  visitante INT  NOT NULL,
  fecha     DATE NOT NULL,
  hora      TIME NOT NULL,
  FOREIGN KEY (local)     REFERENCES equipos (id),
  FOREIGN KEY (visitante) REFERENCES equipos (id)
);