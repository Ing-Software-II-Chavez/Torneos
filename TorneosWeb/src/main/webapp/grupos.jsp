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
    <title>Grupos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>


<%
    int numGruposInt = Integer.parseInt(request.getAttribute("numGrupos").toString());
%>

<form action="formulariogrupos">
    <%
        for (int i = 1; i <= numGruposInt; i++) {
    %>
            <label for="idGrupo<%=i%>">ID Grupo <%=i%>:</label>
            <input type="text" id="idGrupo<%=i%>" name="idGrupo<%=i%>" value=""><br>

            <label for="nombreGrupo<%=i%>">Nombre Grupo <%=i%>:</label>
            <input type="text" id="nombreGrupo<%=i%>" name="nombreGrupo<%=i%>" value=""><br>
    <%
        }
    %>
    <input type="submit" value="Enviar">
    <input type="hidden" name="idtorneo" value="${idtorneo}">
    <input type="hidden" name="numGruposTotal" value="${numGrupos}">
</form>

</body>
</html>
