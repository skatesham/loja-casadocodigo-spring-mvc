<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/" var="contextPath" />

<header id="layout-header">

	<div class="clearfix container">
		<a href="${contextPath}" id="logo"> </a>
		<div id="header-content">
			<nav id="main-nav">
				<ul class="nav navbar-nav navbar-left">
					<security:authorize access="isAuthenticated()">
						<li><a href="${s:mvcUrl('PC#listar').build() }"
							rel="nofollow"><fmt:message key="produtos.lista" /></a></li>
						<li><a href="${s:mvcUrl('PC#form').build() }" rel="nofollow"><fmt:message
									key="produtos.cadastro" /></a></li>
					</security:authorize>
					<li><a href="${s:mvcUrl('CCC#itens').build() }" rel="nofollow">
							<!--  MODELO 1 - Mais Escrito 
							<fmt:message key="menu.carrinho">
								<fmt:param value="${carrinhoCompras.quantidade }" />
							</fmt:message>
							--> <s:message code="menu.carrinho"
								arguments="${carrinhoCompras.quantidade}" />
					</a></li>
					<li><a href="?locale=pt" rel="nofollow"> <fmt:message
								key="menu.pt" />
					</a></li>

					<li><a href="?locale=en_US" rel="nofollow"> <fmt:message
								key="menu.en" />
					</a></li>
					<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow"><fmt:message
								key="menu.sobre" /></a></li>
					<security:authorize access="isAuthenticated()">
						<li><a href="<c:url value="/logout" />"><fmt:message
									key="exit" /></a></li>
					</security:authorize>
					<security:authorize access="!isAuthenticated()">
						<li><a href="<c:url value="/login" />"><fmt:message
									key="login" /></a></li>
					</security:authorize>
				</ul>
			</nav>
		</div>
	</div>
	<div style="text-align: right;">
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal" var="usuario" />${usuario.username }
			</security:authorize>
	</div>
</header>

<nav class="categories-nav">
	<ul class="container">
		<li class="category"><a href="/casadocodigo"> <fmt:message
					key="navegacao.categoria.home" />
		</a></li>
		<li class="category"><a href="/collections/livros-de-agile">
				<fmt:message key="navegacao.categoria.agile" />
		</a></li>
		<li class="category"><a href="/collections/livros-de-front-end">
				<fmt:message key="navegacao.categoria.front_end" />
		</a></li>
		<li class="category"><a href="/collections/livros-de-games">
				<fmt:message key="navegacao.categoria.games" />
		</a></li>
		<li class="category"><a href="/collections/livros-de-java"> <fmt:message
					key="navegacao.categoria.java" />
		</a></li>
		<li class="category"><a href="/collections/livros-de-mobile">
				<fmt:message key="navegacao.categoria.mobile" />
		</a></li>
		<li class="category"><a
			href="/collections/livros-desenvolvimento-web"> <fmt:message
					key="navegacao.categoria.web" />
		</a></li>
		<li class="category"><a href="/collections/outros"> <fmt:message
					key="navegacao.categoria.outros" />
		</a></li>
	</ul>
</nav>