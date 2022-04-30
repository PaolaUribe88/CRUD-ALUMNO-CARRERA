<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FORM. ALUMNO</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
	<div class="container">
			<h1>Alumno</h1>
			<form method="POST" action="/cft-web/AlumnoController">
			<input type="hidden" name="id" value="${alumno.id}" />
			<div class="mb-3">
	  			<label for="nombre" class="form-label">Nombre:</label>
	  			<input type="text" class="form-control" value="${alumno.nombre}" id="nombre" name="nombre" />
			</div>
			<div class="mb-3">
	  			<label for="carrera" class="form-label">Carrera:</label>
	  			<input type="text" class="form-control" value="${alumno.carrera}" id="carrera" name="carrera" />
			</div>
			<button class="btn btn-primary" type="submit">Guardar</button>
		</form>
		</div>	
</html>