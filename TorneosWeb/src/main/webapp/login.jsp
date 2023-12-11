<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesi&oacute;n</title>
    <style>
        body {
            background-color: #ECEFF4;
            color: #E5E9F0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #3B4252;
            border-radius: 5px;
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        h1 {
            color: #eceff4;
            margin-top: 0;
        }

        label {
            color: #eceff4;
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="password"] {
            background-color: #4C566A;
            color: #E5E9F0;
            border: 1px solid #D8DEE9;
            padding: 10px;
            margin: 5px 0;
            width: 90%;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border: 1px solid #88C0D0;
        }

        .buttons {
            margin-top: 20px;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #5E81AC;
            color: #E5E9F0;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #81A1C1;
        }

        p {
            color: #D8DEE9;
            margin-top: 10px;
        }

        a {
            color: #88C0D0;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Iniciar sesi&oacute;n</h1>
        <form>
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required>

            <label for="contrasena">Contrase&ntilde;a:</label>
            <input type="password" id="contrasena" name="contrasena" required>

            <div class="buttons">
                <input type="submit" value="Iniciar sesi&oacute;n">
                <input type="reset" value="Limpiar">
            </div>
        </form>

        <p>¿No tienes una cuenta? <a href="registro.jsp">Reg&iacute;strate</a></p>
    </div>
</body>
</html>
