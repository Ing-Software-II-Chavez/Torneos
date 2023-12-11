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
		    <input type="text" id="nombre" name="nombre" value="${nombreGC}" required>
		
		    <label for="apellido">Primer Apellido:</label>
		    <input type="text" id="apellido" name="apellido" value="${primerApellidoGC}" required>
		
		    <label for="segundoapellido">Segundo Apellido:</label>
		    <input type="text" id="segundoapellido" name="segundoapellido" value="${segundoApellidoGC}" required>
		
		    <label for="fechanacimiento">Fecha de Nacimiento:</label>
		    <input type="date" id="fechanacimiento" name="fechanacimiento" value="${fechaNacimientoGC}" required>
		
		    <label for="numerocuenta">Número de Cuenta:</label>
		    <input type="number" id="numerocuenta" name="numerocuenta" value="${numeroCuentaGC}" required maxlength="7">
		
		    <label for="correo">Correo Electrónico:</label>
		    <input type="email" id="correo" name="correo" value="${correoGC}" required>
		
		    <label for="login">Nombre de Usuario:</label>
		    <input type="text" id="login" name="login" value="${usuarioGC}" required>
		
		    <label for="password">Contraseña:</label>
		    <input type="password" id="password" name="password" value="${passwordGC}" required>
		    <span id="passwordError" style="color: red;"></span>
		
		    <input type="submit" value="Enviar">
		</form>
	</div>
	
	<%
	    // Verificar si hay un mensaje de error y mostrarlo en caso afirmativo
	    String errorMessage = (String)request.getAttribute("error");
	    if (errorMessage != null) {
			%>
			<div class="error-message">
				<%= errorMessage %>
			</div>
			<%
	    }
	%>

    <script type="text/javascript" src="assets/js/registroJugadores.js"></script>
</body>
</html>
