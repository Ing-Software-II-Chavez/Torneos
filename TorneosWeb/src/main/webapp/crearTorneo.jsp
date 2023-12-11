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
  <title>Crear Torneo</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
      background-color: #f0f0f0;
    }

    form {
      width: 300px;
      padding: 20px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    label {
      display: block;
      margin-bottom: 8px;
    }

    input,
    select {
      width: 100%;
      padding: 8px;
      margin-bottom: 16px;
      box-sizing: border-box;
    }

    button {
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
      cursor: pointer;
    }

    button:hover {
      background-color: #218838;
      border-color: #218838;
    }
  </style>
</head>
<body>

  <form action="registroTorneo" method="post">

    <h1 style="text-align: center;">Crear Torneo</h1>
    
    <label for="idtorneo">ID del Torneo:</label>
    <input type="number" id="idtorneo" name="idtorneo" required>
    
    <label for="nombre">Nombre del Torneo:</label>
    <input type="text" id="nombre" name="nombre" required>

    <label for="disciplina">Disciplina:</label>
    <select id="disciplina" name="disciplina" required>
      <option value="futbol">Futbol</option>
      <option value="basquetbol">Basquetbol</option>
      <option value="voleybol">Voleybol</option>
    </select>

    <label for="numEquipos">Número de Equipos:</label>
    <select id="numEquipos" name="numEquipos" required>
      <option value="8">8</option>
      <option value="16">16</option>
      <option value="32">32</option>
    </select>

    <label for="fechaInicio">Fecha de Inicio:</label>
    <input type="date" id="fechaInicio" name="fechaInicio" required>

    <label for="fechaFin">Fecha de Fin:</label>
    <input type="date" id="fechaFin" name="fechaFin" required>

    <label for="numGrupos">Número de Grupos:</label>
    <input type="number" id="numGrupos" name="numGrupos" required>

    <input type="submit" value="Enviar">

  </form>

  <script>
    document.getElementById('fechaInicio').addEventListener('change', function() {
      var today = new Date().toISOString().split('T')[0];
      if (this.value < today) {
        alert('La fecha de inicio no puede ser anterior a la fecha actual.');
        this.value = today;
      }
    });

    document.getElementById('fechaFin').addEventListener('change', function() {
      var startDate = document.getElementById('fechaInicio').value;
      if (this.value < startDate) {
        alert('La fecha de fin no puede ser anterior a la fecha de inicio.');
        this.value = startDate;
      }
    });
  </script>

</body>
</html>
