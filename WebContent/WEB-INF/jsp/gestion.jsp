<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="refresh" content="30"; url="./ControladorGestionIngreso?borrar=0">
<title>Gestión</title>
</head>
<body>
<div class="container">
<a href="index.html"><img src="images/logo.png"/></a>
<h2>Listado de espera:</h2>
	<table class="table table-hover">
	<thead>
		<tr bgcolor="#CCCCCC">
			<th>ID</th>
			<th>Nombre Apellido</th>
			<th>Trámite</th>
			<th>Fecha y hora</th>
			<th>Borrar</th>
		</tr>
		</thead>
		 <tbody>
		<c:forEach var="gestion" items="${gestiones}">
			<tr>
				<td>${gestion.id}</td>
				<td>${gestion.nom_apel}</td>
				<td>${gestion.nom_tramite}</td>
				<td>${gestion.fecha_y_hora}</td>
				<td><a class="btn btn-primary btn-lg"
					href="ControladorGestionIngreso?borrar=${gestion.id}" role="button">BORRAR</a></td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
		</div>
		
		<table>
		<tr>
		<td>
		   <a class="btn btn-danger btn-lg"
					href="ControladorLogOut" role="button">Log Out</a>
		</td>
		</tr>
		</table>
</body>
</html>