<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Livros</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #dbeafe, #93c5fd);
            margin: 0;
            padding: 30px;
        }

        h2 {
            color: #1e3a8a;
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background-color: #1d4ed8;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f8fafc;
        }

        tr:hover {
            background-color: #e0f2fe;
        }

        button, input[type="submit"] {
            background-color: #3b82f6;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-weight: bold;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #2563eb;
        }

        .indisponivel {
            color: red;
            font-weight: bold;
        }

        .voltar {
            display: flex;
            justify-content: center;
            margin-top: 30px;
        }

        p {
            text-align: center;
            font-weight: bold;
            color: #1e3a8a;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h2>Selecione o livro desejado:</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Autor</th>
        <th>Editora</th>
        <th>Ano</th>
        <th>Ações</th>
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
                    <span class="indisponivel">Indisponível</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="voltar">
    <form action="paginicial" method="get">
        <input type="submit" value="Voltar para página inicial">
    </form>
</div>

<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>
