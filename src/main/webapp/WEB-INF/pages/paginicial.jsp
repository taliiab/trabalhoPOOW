<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página Inicial</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #dbeafe, #bfdbfe);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background-color: white;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }

        h1 {
            color: #1e3a8a;
            margin-bottom: 10px;
        }

        h3 {
            color: #374151;
            margin-bottom: 30px;
        }

        form {
            margin-bottom: 15px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 10px;
            background-color: #3b82f6;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #2563eb;
        }

        @media (max-width: 480px) {
            .container {
                padding: 30px 20px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Bem-vindo ao Sistema da Biblioteca</h1>
    <h3>Escolha uma opção:</h3>

    <form action="livro" method="get">
        <input type="submit" value="Livros">
    </form>

    <form action="emprestimo" method="get">
        <input type="submit" value="Empréstimos">
    </form>

    <form action="usuario" method="get">
        <input type="submit" value="Gerenciamento de Cadastro">
    </form>

    <form action="/logout">
        <input type="submit" value="Sair">
    </form>
</div>

</body>
</html>
