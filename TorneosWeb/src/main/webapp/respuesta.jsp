<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos</title>
</head>
<body>

    <p>Nombre: ${sessionScope.jugador.nombre}</p>
    <p>Usuario:  ${sessionScope.jugador.usuario} </p>
    
    <h1>TU EQUIPO</h1>

    
    <a href="index.jsp">Cerrar Sesion</a>
</body>
</html>