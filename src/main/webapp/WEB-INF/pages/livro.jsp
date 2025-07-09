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
            background: linear-gradient(135deg, #dbeafe, #bfdbfe);
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #1e3a8a;
            text-align: center;
            margin-bottom: 30px;
        }

        .formliv {
            background-color: #ffffff;
            padding: 25px 30px;
            margin: 0 auto 30px auto;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            text-align: center;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        button, input[type="submit"] {
            background-color: #3b82f6;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin: 6px 4px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #2563eb;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            margin-bottom: 30px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #1e40af;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f1f5f9;
        }

        tr:hover {
            background-color: #e2e8f0;
        }

        p {
            text-align: center;
            font-weight: bold;
            color: #1e3a8a;
        }

        .voltar-btn {
            display: flex;
            justify-content: center;
        }

        .disponivel span {
            font-size: 18px;
        }
    </style>
</head>
<body>

<h2>Livros</h2>

<c:if test="${usuarioLogado != null && (usuarioLogado.id == u.id || papel == 'admin')}">
    <form action="livro" method="post" class="formliv">
        <input type="hidden" name="opcao" value="${livro != null ? 'atualizar' : 'cadastrar'}" />
        <input type="hidden" name="id" value="${livro != null ? livro.id : ''}" />

        <input type="text" name="titulo" placeholder="Título" value="${livro != null ? livro.titulo : ''}" required />
        <input type="text" name="autor" placeholder="Autor" value="${livro != null ? livro.autor : ''}" required />
        <input type="text" name="editora" placeholder="Editora" value="${livro != null ? livro.editora : ''}" required />
        <input type="number" name="ano" placeholder="Ano" value="${livro != null ? livro.ano : ''}" required />

        <button type="submit">${livro != null ? 'Atualizar' : 'Cadastrar'}</button>
    </form>
    <hr/>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Autor</th>
        <th>Editora</th>
        <th>Ano</th>
        <c:if test="${usuarioLogado != null && (usuarioLogado.id == u.id || papel == 'admin')}">
            <th>Ações</th>
        </c:if>
        <th>Disponível</th>
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
            <c:if test="${usuarioLogado != null && (usuarioLogado.id == u.id || papel == 'admin')}">
                <td>
                    <form action="livro" method="post" style="display:inline;">
                        <input type="hidden" name="opcao" value="buscar" />
                        <input type="hidden" name="id" value="${l.id}" />
                        <button type="submit">Editar</button>
                    </form>

                    <form action="livro" method="post" style="display:inline;">
                        <input type="hidden" name="opcao" value="excluir" />
                        <input type="hidden" name="id" value="${l.id}" />
                        <button type="submit">Excluir</button>
                    </form>
                </td>
            </c:if>
            <td class="disponivel">
                <c:if test="${l.disponivel}">
                    <span style="color: green;">✔</span>
                </c:if>
                <c:if test="${!l.disponivel}">
                    <span style="color: red;">✘</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="voltar-btn">
    <form action="paginicial" method="get">
        <input type="submit" value="Voltar para página inicial">
    </form>
</div>

<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>
