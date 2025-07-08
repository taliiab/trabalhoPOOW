<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página Inicial</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f4f8;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
    }

    h1 {
        color: #333;
        margin-bottom: 10px;
    }

    h3 {
        color: #555;
        margin-bottom: 30px;
    }

    form {
        margin: 10px 0;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
<body>

<h1>Bem-vindo ao Sistema da Biblioteca</h1>

<h3>Escolha uma opção:</h3>

<form action="livro" method="get">
    <input type="submit" value="Livros">
</form>

<br>

<form action="emprestimo" method="get">
    <input type="submit" value="Empréstimos">
</form>

<br>
<form action="usuario" method="get">
    <input type="submit" value="Gerenciamento de Cadastro">
</form>

<br><br>

<form action="index.jsp">
    <input type="submit" value="Sair">
</form>

</body>
</html>
