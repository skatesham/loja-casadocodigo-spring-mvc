<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Import da taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<div class="col-8 ml-auto mr-auto">
			<h1 class="text-dark mt-3">Casa do Código</h1>
			<a class=" mt-2 mb-2 btn btn-dark btn-block"
				href="${s:mvcUrl('PC#listar').build()}" role="button">Lista de
				Produtos</a>
			<form:form action="${s:mvcUrl('PC#gravar').build()}" method="post"
				commandName="produto" enctype="multipart/form-data">
				<div class="form-group">
					<label>Título</label>
					<form:input class="form-control" path="titulo" />
					<div class="mt-3">
						<form:errors class="alert alert-danger" path="titulo" />
					</div>
				</div>
				<div class="form-group">
					<label>Descrição</label>
					<form:textarea class="form-control" rows="2" cols="10"
						path="descricao"></form:textarea>
					<div class="mt-3">
						<form:errors class="alert alert-danger" path="descricao" />
					</div>
				</div>
				<div class="form-group">
					<label>Páginas</label>
					<form:input class="form-control" path="paginas" />
					<div class="mt-3">
						<form:errors class="alert alert-danger" path="paginas" />
					</div>
				</div>
				<div class="form-group">
					<label>Data de Lançamento</label>
					<form:input class="form-control" path="dataLancamento" />
					<div class="mt-3">
						<form:errors path="dataLancamento" />
					</div>
				</div>
				<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
					<div class="form-group">
						<label>${tipoPreco}</label>
						<form:input class="form-control"
							path="precos[${status.index}].valor" />
						<form:hidden path="precos[${status.index}].tipo"
							value="${tipoPreco}" />
					</div>
				</c:forEach>
				<div>
					<label>Sumário</label> <input class="form-control-file"
						name="sumario" type="file" />
				</div>
				<button class="mb-2 btn btn-lg btn-dark" type="submit">Cadastrar</button>
			</form:form>
		</div>
	</div>
	<%@ include file="/base/foot.html"%>
</body>
</html>