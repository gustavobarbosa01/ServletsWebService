<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,br.com.grbarbosa.servlet.modelo.Empresa"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Standard Taglib</title>
</head>
<body>
    <c:import url="logout-parcial.jsp" />
	Usuario Logado: ${usuarioLogado.login } </br></br></br>
	
	<c:if test="${not empty empresa}">
		Empresa ${empresa} cadastrada com sucesso!
	</c:if>
	Lista de Empresas:
	
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			
			<li> ${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/> </li>
			<!-- <a href="/gerenciador/mostraEmpresa?id=${empresa.id}">Editar</a><br>
			<a href="/gerenciador/removeEmpresa?id=${empresa.id}">Remover</a> -->
			
			<a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}">Editar</a><br>
			<a href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}">Remover</a>
		</c:forEach>
		
	</ul>
	
	
	
<%-- 	<ul>
		<% 
		List<Empresa> lista = (List<Empresa>)request.getAttribute("empresas");
		for (Empresa empresa : lista) {
		%>
		<li> <%=empresa.getNome()%> </li>
		<%
		}
		%>
	</ul> --%>

</body>
</html>