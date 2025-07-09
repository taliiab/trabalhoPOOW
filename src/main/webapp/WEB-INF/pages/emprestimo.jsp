<%@ page session="true" %>
<%
  br.csi.model.Usuario usuarioLogado = (br.csi.model.Usuario) session.getAttribute("usuarioLogado");
  String papel = (String) session.getAttribute("papel");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gerenciamento de Empréstimos</title>
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #e0f2fe, #bae6fd);
      padding: 30px;
      margin: 0;
    }

    h2, h3 {
      text-align: center;
      color: #1e3a8a;
      margin-bottom: 20px;
    }

    form {
      margin-bottom: 20px;
      text-align: center;
    }

    .tabelas-container {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      gap: 24px;
      margin-bottom: 40px;
    }

    .tabela-box {
      flex: 1;
      min-width: 380px;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 12px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 10px;
    }

    th, td {
      padding: 12px;
      border: 1px solid #ccc;
      text-align: center;
    }

    th {
      background-color: #1d4ed8;
      color: #fff;
    }

    tr:nth-child(even) {
      background-color: #f1f5f9;
    }

    tr:hover {
      background-color: #e2e8f0;
    }

    input[type="submit"],
    input[type="button"],
    button {
      background-color: #3b82f6;
      color: white;
      padding: 8px 16px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-weight: bold;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover,
    button:hover {
      background-color: #2563eb;
    }

    .voltar-btn {
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

    hr {
      border: none;
      border-top: 1px solid #ccc;
      margin: 40px 0;
    }
  </style>
</head>
<body>

<h2>${emprestimo != null ? "Editar Empréstimo" : "Realizar Empréstimo"}</h2>

<div class="tabelas-container">
  <div class="tabela-box">
    <h3>Lista de Usuários</h3>
    <table>
      <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Empréstimo</th>
      </tr>
      <c:forEach var="u" items="${usuarios}">
        <c:if test="${usuarioLogado != null && (usuarioLogado.id == u.id || papel == 'admin')}">
          <tr>
            <td>${u.id}</td>
            <td>${u.nome}</td>
            <td>${u.email}</td>
            <td>
              <form method="post" action="elivro" style="display:inline;">
                <input type="hidden" name="usuarioId" value="${u.id}">
                <input type="submit" value="Realizar Empréstimo">
              </form>
            </td>
          </tr>
        </c:if>
      </c:forEach>
    </table>
  </div>
</div>

<hr>

<c:if test="${usuarioLogado != null && (usuarioLogado.id == u.id || papel == 'admin')}">
  <h2>Lista de Empréstimos</h2>
  <div class="tabela-box" style="max-width: 1000px; margin: 0 auto;">
    <table>
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
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</c:if>

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
