<!--
	@José Juan García Romero
	@Luis Angel Rocha Ronquillo
	@Jesús Alberto Sanchez Mendieta
	@Isaac Misael Vazquez Albor
	@Francisco Gamaliel Alvaro Portillo
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro Exitoso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            text-align: center;
            margin-top: 50px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #008080;
        }

        p {
            color: #333;
        }

        button {
            padding: 10px 20px;
            background-color: #008080;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Registrado con Éxito</h1>
        <p>¡Se te ha registrado en el equipo seleccionado!</p>

        <a href="respuesta.jsp">
            <button type="button">Volver a perfil </button>
        </a>
    </div>

</body>
</html>
