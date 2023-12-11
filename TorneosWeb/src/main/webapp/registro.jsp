<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="assets/css/registroJugadores.css">
    <title>Registro</title>
</head>
<body>
	<div class="div-form">
		<form action="registroJugador" method="post">
		    <label class="reg">REGISTRARSE</label> <br>
		    <label for="nombre">Nombre Completo:</label>
		    <input type="text" id="nombre" name="nombre" required>
		
		    <label for="apellido">Primer Apellido:</label>
		    <input type="text" id="apellido" name="apellido" required>
		
		    <label for="segundoapellido">Segundo Apellido:</label>
		    <input type="text" id="segundoapellido" name="segundoapellido">
		
		    <label for="fechanacimiento">Fecha de Nacimiento:</label>
		    <input type="date" id="fechanacimiento" name="fechanacimiento" required max="">
		
		    <label for="numerocuenta">Número de Cuenta:</label>
		    <input type="text" id="numerocuenta" name="numerocuenta" required>
		
		    <label for="correo">Correo Electrónico:</label>
		    <input type="email" id="correo" name="correo" required>
		
		    <label for="login">Nombre de Usuario:</label>
		    <input type="text" id="login" name="login" required>
		
		    <label for="password">Contraseña:</label>
		    <input type="password" id="password" name="password" required>
		    <span id="passwordError" style="color: red;"></span>
		
		    <input type="submit" value="Enviar">
		</form>
	</div>

    <script type="text/javascript" src="assets/js/registroJugadores.js"></script>
</body>
</html>
