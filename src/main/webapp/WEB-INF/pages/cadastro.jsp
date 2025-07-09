<%
    String papel = (String) session.getAttribute("papel");
    if (!"admin".equals(papel)) {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/exclusiva.jsp");
        rd.forward(request, response);
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro - Sistema de Biblioteca</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #dbeafe, #bfdbfe);
            margin: 0;
            padding: 40px 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2, h3 {
            color: #1e3a8a;
            margin-bottom: 20px;
        }

        form {
            text-align: center;
            margin-bottom: 30px;
        }

        .formcad {
            background-color: white;
            padding: 30px 40px;
            border-radius: 16px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin-top: 10px;
            color: #374151;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        input[type="submit"], button {
            background-color: #3b82f6;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover, button:hover {
            background-color: #2563eb;
        }

        table {
            width: 100%;
            max-width: 900px;
            border-collapse: collapse;
            background-color: white;
            margin-top: 30px;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 6px 12px rgba(0,0,0,0.05);
        }

        th, td {
            border: 1px solid #e2e8f0;
            padding: 12px;
            text-align: center;
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

        .actions form {
            display: inline-block;
            margin: 2px;
        }

        .voltar {
            margin-top: 30px;
        }

        p {
            font-weight: bold;
            color: #1e3a8a;
            margin-top: 20px;
        }

        hr {
            margin: 40px auto;
            border: none;
            border-top: 1px solid #ccc;
            width: 90%;
        }
    </style>
</head>
<body>

<h2>${usuario != null ? "Editar Usuário" : "Cadastro de Usuário"}</h2>

<form action="usuario" method="post" class="formcad">
    <input type="hidden" name="opcao" value="${usuario != null ? 'editar' : 'cadastrar'}">
    <c:if test="${usuario != null}">
        <input type="hidden" name="id" value="${usuario.id}" />
    </c:if>

    <label>Nome:</label>
    <input type="text" name="nome" value="${usuario != null ? usuario.nome : ''}" required>

    <label>Email:</label>
    <input type="email" name="email" value="${usuario != null ? usuario.email : ''}" required>

    <label>Senha:</label>
    <input type="password" name="senha" value="${usuario != null ? usuario.senha : ''}" required>

    <input type="submit" value="${usuario != null ? 'Salvar Alterações' : 'Cadastrar'}">
</form>

<hr>

<h3>Lista de Usuários</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Opções</th>
    </tr>
    <c:forEach var="u" items="${usuarios}">
        <tr>
            <td>${u.id}</td>
            <td>${u.nome}</td>
            <td>${u.email}</td>
            <td class="actions">
                <form action="usuario" method="post">
                    <input type="hidden" name="opcao" value="buscar" />
                    <input type="hidden" name="id" value="${u.id}" />
                    <button type="submit">Editar</button>
                </form>
                <form action="usuario" method="post">
                    <input type="hidden" name="opcao" value="excluir" />
                    <input type="hidden" name="id" value="${u.id}" />
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="voltar">
    <form action="paginicial">
        <input type="submit" value="Voltar para tela inicial">
    </form>
</div>

<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>
