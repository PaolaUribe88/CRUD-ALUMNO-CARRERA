<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FORM. ALUMNO</title>
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
			<h1>Alumno</h1>
			<form method="POST" action="/cft-web/AlumnoController">
			<input type="hidden" name="id" value="${alumno.id}" />
			<div class="mb-3">
	  			<label for="nombre" class="form-label">Nombre:</label>
	  			<input type="text" class="form-control" value="${alumno.nombre}" id="nombre" name="nombre" />
			</div>
			<div class="mb-3">
	  			<label for="carrera_id" class="form-label">Carrera:</label>
	  			<select name="carrera_id" id="carrera_id">
	  				<c:forEach var="carrera" items="${carreras}">
	  				<c:choose>
					<c:when test="${carrera.id == alumno.carrera.id}">
						<option selected="selected" value="${carrera.id}">${carrera.nombre}</option>
					</c:when>
					<c:otherwise>
						<option value="${carrera.id}">${carrera.nombre}</option>
					</c:otherwise>
					</c:choose>
					
					</c:forEach>
	  			</select>

			</div>
			<div class="mb-3">
				<label for="nacimiento" class="form-label">Fecha de Nacimiento:</label>
				<input type="date" class="form-control" value="${alumno.fechaNacimiento}" id="nacimiento" name="nacimiento" />
				</div>
			<button class="btn btn-primary" type="submit">Guardar</button>
		</form>
		</div>	
</html>