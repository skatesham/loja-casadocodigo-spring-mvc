<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
<%@ include file="/base/head.html"%>
</head>
<body>
	<div class="container text-center">
		<h1 class="mt-3 text-light" >Lista de Produtos</h1>
		<p class="alert alert-success">${sucesso}</p>
		<div>
		<a class=" mt-2 mb-2 btn btn-dark btn-block" href="${s:mvcUrl('PC#form').build()}" role="button">Criar Produto</a>
			<table class="table table-bordered table-striped table-hover">
				<tr>
					<th>Título</th>
					<th>Descrição</th>
					<th>Páginas</th>
					<th>Detalhes</th>
				</tr>

				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td>${produto.titulo}</td>
						<td>${produto.descricao}</td>
						<td>${produto.paginas}</td>
						<!-- <td><a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">${produto.titulo}</a></td>  -->
						<td><a class="btn btn-block btn-dark" href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">${produto.titulo}</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
<%@ include file="/base/foot.html"%>
</body>
</html>