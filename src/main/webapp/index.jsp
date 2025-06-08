<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Biblioteca - Login e Cadastro</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    h2 {
        color: #333;
    }

    form {
        background-color: #fff;
        padding: 20px 30px;
        margin: 20px 0;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }

    label {
        font-weight: bold;
    }

    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-top: 4px;
        margin-bottom: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    hr {
        border: none;
        border-top: 1px solid #ccc;
        width: 80%;
    }

    p {
        font-weight: bold;
    }
</style>
<body>

<h2>Login</h2>
<form action="usuario" method="post">
    <input type="hidden" name="opcao" value="login">

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Senha:</label><br>
    <input type="password" name="senha" required><br><br>

    <input type="submit" value="Entrar">
</form>

<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>
