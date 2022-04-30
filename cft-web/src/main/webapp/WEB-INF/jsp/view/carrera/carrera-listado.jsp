<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CARRERA</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
		<h1>Carreras</h1>
		
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Codigo</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="carrera" items="${carreras}">
				<tr>
					<td><c:out value="${carrera.id}" /></td>
					<td><c:out value="${carrera.nombre}" /></td>
					<td><c:out value="${carrera.codigo}" /></td>
					<td>
						<a href="${pageContext.request.contextPath}/CarreraController?accion=editar&amp;id=${carrera.id}">Editar</a> <%-- contextPath en este caso es /cft-web --%>
						<a href="${pageContext.request.contextPath}/CarreraController?accion=eliminar&amp;id=${carrera.id}">Eliminar</a> <%-- contextPath en este caso es /cft-web --%>
					</td>
				</tr>
			</c:forEach>			
			</tbody>
		</table>
		</div>
	</body>
</html>