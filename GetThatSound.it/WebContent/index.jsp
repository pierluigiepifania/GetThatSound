<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>
<%
	try {
		ClientBean cb = ((ClientBean) session.getAttribute("client"));
		if (cb != null) {
			String redirectURL = "home.jsp";
			response.sendRedirect(redirectURL);
		}
	} catch (Exception e) {

	}
%>
<!doctype html>

<html lang="it">
<head>
<meta charset="utf-8">

<title>GetThatSound</title>
<meta name="description" content="Get That Sound">
<meta name="author" content="Adriano Molli e Luigi Epifania">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="/css/modal.css" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script src="js/validation.js"></script>
<style>
	form label {
		margin:0.5em 0 0.5em 0;
	}
</style>
</head>

<body class="homepage is-preload">
	<div id="page-wrapper">

		<!-- Header -->
		<div id="header" style="background: cadetblue;">
			<!-- Inner -->
			<div class="inner">
				<header>
					<h1>
						<a href="home.jsp" id="logo">GetThatSound</a>
					</h1>
					<hr />
					<p>Espandi la tua esperienza musicale</p>
				</header>
				<footer>
					<a href="#features" class="button circled scrolly">Start</a>
				</footer>
			</div>
		</div>

		<div class="wrapper style1">

			<section id="features" class="container special">
				<header>
					<h2>Entra nella piattaforma</h2>
					<%@include file="/jsp/registration.jsp"%>
				</header>

			</section>

		</div>

		<%@include file="/jsp/footer.jsp"%>

	</div>

	<!-- Scripts -->
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.dropotron.min.js"></script>
	<script src="js/jquery.scrolly.min.js"></script>
	<script src="js/jquery.scrollex.min.js"></script>
	<script src="js/browser.min.js"></script>
	<script src="js/breakpoints.min.js"></script>
	<script src="js/main.js"></script>
	<script src="/js/modal.js"></script>

</body>
</html>