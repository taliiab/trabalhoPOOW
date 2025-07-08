<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Livros</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f4f8;
        margin: 0;
        padding: 20px;
    }

    h2 {
        color: #333;
        text-align: center;
    }

    form {
        margin-bottom: 20px;
        text-align: center;
    }

    .formliv {
        background-color: #fff;
        padding: 20px 30px;
        margin: auto auto;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
    }

    input[type="text"],
    input[type="number"] {
        padding: 8px;
        margin: 5px;
        border: 1px solid #ccc;
        border-radius: 6px;
        width: 200px;
    }

    button, input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 8px 16px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        margin: 3px;
        transition: background-color 0.3s ease;
    }

    button:hover, input[type="submit"]:hover {
        background-color: #0056b3;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: white;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #007bff;
        color: white;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #f1f1f1;
    }

    p {
        text-align: center;
        font-weight: bold;
    }
</style>
<body>

<h2>Selecione o livro desejado:</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID</th><th>Título</th><th>Autor</th><th>Editora</th><th>Ano</th><th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="l" items="${livros}">
        <tr>
            <td>${l.id}</td>
            <td>${l.titulo}</td>
            <td>${l.autor}</td>
            <td>${l.editora}</td>
            <td>${l.ano}</td>
            <td>
                <c:if test="${l.disponivel}">
                <form action="emprestimo" method="post" style="display:inline;">
                    <input type="hidden" name="opcao" value="cadastrar" />
                    <input type="hidden" name="id_usuario" value="${usuarioId}" />
                    <input type="hidden" name="id_livro" value="${l.id}" />
                    <button type="submit">Selecionar</button>
                </form>
                </c:if>
                <c:if test="${!l.disponivel}">
                    <span style="color: red;">Indisponível</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<form action="paginicial" method="get">
    <input type="submit" value="Voltar para página inicial">
</form>

<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>

