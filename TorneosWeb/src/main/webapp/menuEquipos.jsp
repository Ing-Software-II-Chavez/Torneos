<!--
	@José Juan García Romero
	@Luis Angel Rocha Ronquillo
	@Jesús Alberto Sanchez Mendieta
	@Isaac Misael Vazquez Albor
	@Francisco Gamaliel Alvaro Portillo
-->

<%@ page import="mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Equipo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seleccion Equipos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 20px;
        }

        h1 {
            color: #007bff;
        }

        p {
            margin: 10px 0;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }
        
        .boton {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            color: #fff;
            background-color: #28a745;
            border: 2px solid #28a745; 
            transition: background-color 0.3s;
        }

        .boton:hover {
            background-color: #218838; 
            border-color: #218838; 
        }
    </style>
</head>
<body>

    <p>Nombre: ${sessionScope.jugador.nombre}</p>
    <p>Usuario:  ${sessionScope.jugador.usuario} </p>
    
    <h1>SELECCIONAR EQUIPO</h1>
    
<%
    List<Equipo> equipo2 = (List<Equipo>) session.getAttribute("equipo2");

        for (Equipo equipo : equipo2) {
%>
           <form action="equipoelegido">
           <p>Nombre del equipo: <%= equipo.getNombre() %></p>
            <input type="submit" class="boton" value="Registrarse">
            <input type="hidden" name="id" value="<%= equipo.getId() %>">
             <input type="hidden" name="nombre" value="${sessionScope.jugador.nombre}">
             <input type="hidden" name="usuario" value="${sessionScope.jugador.usuario}">
           </form>

<%
        }
%>
   
</body>
</html>