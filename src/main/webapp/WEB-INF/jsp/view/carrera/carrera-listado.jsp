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
	 <nav class="navbar fixed-top navbar-expand-lg  navbar-light bg-light ">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">INFORMACION CFT TU-PODI</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              	<li class="nav-item">
                	 <a class="nav-link active" aria-current="page" href="/cft-web/AlumnoController?accion=listar"> Ver Alumnos</a>
              	</li>
              	<li class="nav-item">
                	<a class="nav-link" href="/cft-web/AlumnoController?accion=form"> Crear Alumno</a>
              	</li>
              	<li class="nav-item">
                	<a class="nav-link" href="/cft-web/CarreraController?accion=listar"> Ver Carreras</a></a>
              	</li>
              	<li class="nav-item">
                	<a class="nav-link" href="/cft-web/CarreraController?accion=form"> Crear Carreras</a></a>
              	</li>
              </ul>
          </div>
        </div>
		</nav>
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