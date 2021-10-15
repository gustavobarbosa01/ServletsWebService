<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--%
	
	//Scriptlet
	String nomeEmpresa = (String)request.getAttribute("empresa");
    System.out.println(nomeEmpresa);


%-->


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:import url="logout-parcial.jsp" />
	<!--%=nomeEmpresa%-->
	<c:if test="${not empty empresa}">Empresa ${empresa} cadastrada com sucesso!</c:if>
	<c:if test="${empty empresa}">Nenhuma empresa cadastrada!!</c:if>
	
</body>
</html>