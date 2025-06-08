<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gerenciamento de Empréstimos</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f2f5;
        padding: 20px;
    }

    h2, h3 {
        color: #333;
        text-align: center;
    }

    label {
        display: block;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="number"],
    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    input[type="submit"],
    input[type="button"],
    button {
        background-color: #007bff;
        color: white;
        padding: 10px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type="submit"]:hover,
    button:hover {
        background-color: #0056b3;
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


    .tabelas-container {
        display: flex;
        gap: 20px;
        justify-content: center;
        flex-wrap: wrap;
        margin-bottom: 30px;
    }

    .tabela-box {
        flex: 1;
        min-width: 400px;
        background-color: white;
        padding: 15px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
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

<h2>${emprestimo != null ? "Editar Empréstimo" : "Realizar Empréstimo"}</h2>

<form action="emprestimo" method="post" class="formcad">
  <input type="hidden" name="opcao" value="${emprestimo != null ? 'atualizar' : 'cadastrar'}">
  <c:if test="${emprestimo != null}">
    <input type="hidden" name="id" value="${emprestimo.id}">
  </c:if>

  <label>ID Usuário:</label><br>
  <input type="number" name="id_usuario" required value="${emprestimo != null ? emprestimo.idUsuario : ''}"><br><br>

  <label>ID Livro:</label><br>
  <input type="number" name="id_livro" required value="${emprestimo != null ? emprestimo.idLivro : ''}"><br><br>

  <input type="submit" value="${emprestimo != null ? 'Atualizar' : 'Emprestar'}">
</form>

<hr>
<div class="tabelas-container">
    <div class="tabela-box">
<h3>Lista de Usuários</h3>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Email</th>
    <th>Empréstimo</th>

  </tr>
  <c:forEach var="u" items="${usuarios}">
    <tr>
      <td>${u.id}</td>
      <td>${u.nome}</td>
      <td>${u.email}</td>
      <td>
        <form method="post" style="display:inline;">
          <input type="hidden" name="opcao" value="emprestar">
          <input type="submit" value="Realizar Empréstimo">
        </form>
    </td>
    </tr>
  </c:forEach>
</table>
    </div>

  <div class="tabela-box">
    <h3>Lista de Livros</h3>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Título</th>
      <th>Autor</th>
      <th>Editora</th>
      <th>Ano</th>
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
      </tr>
    </c:forEach>
    </tbody>
  </table>
  </div>

<br>

</div>

<hr>

<h2>Lista de Empréstimos</h2>
<table border="1">
  <tr>
    <th>ID Empréstimo</th>
    <th>Usuário</th>
    <th>Livro</th>
    <th>Ações</th>
  </tr>
  <c:forEach var="e" items="${emprestimos}">
    <tr>
      <td>${e.id}</td>
        <td>${e.nomeUsuario}</td>
        <td>${e.tituloLivro}</td>
        <td>
        <form action="emprestimo" method="post" style="display:inline;">
          <input type="hidden" name="opcao" value="excluir">
          <input type="hidden" name="id" value="${e.id}">
          <input type="submit" value="Devolver">
        </form>

        <form action="emprestimo" method="post" style="display:inline;">
          <input type="hidden" name="opcao" value="buscar">
          <input type="hidden" name="id" value="${e.id}">
          <input type="submit" value="Editar">
        </form>
      </td>
    </tr>
  </c:forEach>
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
