<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>App Matrícula CFT</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
		<h1>INFORMACION</h1>
		<ul>
			<li>
				<a href="/cft-web/AlumnoController?accion=listar"> Ver Alumnos</a>
			</li>
			<li>
				<a href="/cft-web/AlumnoController?accion=form"> Crear Alumno</a>
			</li>
		</ul>
		<ul>
			<li>
				<a href="/cft-web/CarreraController?accion=listar"> Ver Carreras</a>
			</li>
			<li>
				<a href="/cft-web/CarreraController?accion=form"> Crear Carreras</a>
			</li>
		</ul>
	</body>
</html>