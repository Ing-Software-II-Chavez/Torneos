<!--
	@José Juan García Romero
	@Luis Angel Rocha Ronquillo
	@Jesús Alberto Sanchez Mendieta
	@Isaac Misael Vazquez Albor
	@Francisco Gamaliel Alvaro Portillo
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Página</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }

        .contenedor {
            display: flex;
            justify-content: space-around;
        }

        .seccion {
            text-align: center;
            margin: 20px;
            padding: 20px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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

    <div class="contenedor">
        <div class="seccion">
            <h2>Crear Torneo</h2>
            <p>Crear un nuevo torneo, junto con sus grupos y equipos.</p>
            <a href="crearTorneo.jsp" class="boton">Crear Torneo</a>
        </div>

        <div class="seccion">
            <h2>Iniciar Sesión</h2>
            <p>Ingresa a tu perfil (solo para jugadores).</p>
            <a href="login" class="boton">Iniciar Sesión</a>
        </div>
    </div>

</body>
</html>
