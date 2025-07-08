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
        margin-bottom: 20px;
        text-align: center;
    }

    .formcad {
        background-color: #fff;
        padding: 20px 30px;
        margin: auto auto;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 300px;
    }

    label {
        font-weight: bold;
    }

    input[type="text"],
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

    hr {
        border: none;
        border-top: 1px solid #ccc;
        width: 80%;
    }

    p {
        font-weight: bold;
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
</style>
<body>

<h2>Cadastro de Usuário:</h2>
<form action="usuario" method="post" class="formcad">
    <input type="hidden" name="opcao" value="${usuario != null ? 'editar' : 'cadastrar'}">

    <c:if test="${usuario != null}">
        <input type="hidden" name="id" value="${usuario.id}" />
    </c:if>

    <label>Nome:</label><br>
    <input type="text" name="nome" value="${usuario != null ? usuario.nome : ''}" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" value="${usuario != null ? usuario.email : ''}" required><br><br>

    <label>Senha:</label><br>
    <input type="password" name="senha" value="${usuario != null ? usuario.senha : ''}" required><br><br>

    <input type="submit" value="${usuario != null ? 'Salvar Alterações' : 'Cadastrar'}">
</form>

<hr>

<h3>Lista de Usuários</h3>
<table border="1">
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
            <td>
                <form action="usuario" method="post" style="display:inline;">
                    <input type="hidden" name="opcao" value="buscar" />
                    <input type="hidden" name="id" value="${u.id}" />
                    <button type="submit">Editar</button>
                </form>

                <form action="usuario" method="post" style="display:inline;">
                    <input type="hidden" name="opcao" value="excluir" />
                    <input type="hidden" name="id" value="${u.id}" />
                    <button type="submit">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<form action="paginicial">
    <input type="submit" value="Voltar para tela inicial">
</form>

<br>
<% if (request.getAttribute("msg") != null) { %>
<p><strong><%= request.getAttribute("msg") %></strong></p>
<% } %>

</body>
</html>
