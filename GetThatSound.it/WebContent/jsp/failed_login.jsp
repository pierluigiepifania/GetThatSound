<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>

<html lang="it">
<head>
<meta charset="utf-8">

<title>GetThatSound</title>
<meta name="description" content="Get That Sound">
<meta name="author" content="Adriano Molli e Luigi Epifania">

<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/css/main.css" />

</head>

<body class="homepage is-preload">
	<div id="page-wrapper">
		<nav id="nav">
			<ul>
				<li><a href="/home.jsp">Home</a></li>
			</ul>
		</nav>
	</div>

	<div class="wrapper style1">
		<article id="main" class="container special">
			<header>
				<h2>
					<a href="#">GetThatSound</a>
				</h2>
				<p>Credenziali errate, ripeti il login</p>
			</header>
		</article>

		<%@include file="/jsp/login.jsp"%>

		<%@include file="/jsp/footer.jsp"%>
	</div>
</body>
</html>