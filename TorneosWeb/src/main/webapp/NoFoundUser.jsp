<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mensaje de Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .error-message {
            color: #ff0000;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .login-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4caf50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .login-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="container">
        <p class="error-message">Usuario no encontrado.</p>
        <a href="login.jsp" class="login-button">Intentar de nuevo</a>
    </div>

</body>
</html>
