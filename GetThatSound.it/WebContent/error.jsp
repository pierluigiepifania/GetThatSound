<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>

<%
String redirectURL = "/index.jsp";
ClientBean cb = null;
String broswer = request.getHeader("user-agent");
String IP = request.getRemoteAddr();
try {
	System.out.println("\nERROR PAGE");
	cb = ((ClientBean) session.getAttribute("client"));
	if (cb == null) {
		System.out.println("{ Broswer: "+broswer+"IP: "+IP+" }\n{ NON REGISTRATO }\n");
	}
	else System.out.println("{ Broswer: "+broswer+"IP: "+IP+" }\n{ Utente: "+cb.getUtente()+" Ruolo: "+cb.getRuolo() + " }\n");

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
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet" href="/css/player.css" />
<link rel="stylesheet" href="/css/modal.css" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<meta http-equiv="refresh" content="3;home.jsp">
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
					<p>Si è verificato un errore.</p>
				</header>
			</article>

		<%@include file="/jsp/footer.jsp"%>
	</div>

	<!-- Scripts -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.dropotron.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/jquery.scrollex.min.js"></script>
	<script src="/js/browser.min.js"></script>
	<script src="/js/breakpoints.min.js"></script>
	<script src="/js/main.js"></script>
	<script src="/js/modal.js"></script>
	<script src="/js/player.js"></script>

</body>
</html>