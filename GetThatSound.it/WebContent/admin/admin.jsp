<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>

<%
String redirectURL = "/home.jsp";
ClientBean cb = null;
String broswer = request.getHeader("user-agent");
String IP = request.getRemoteAddr();
try {
	System.out.println("\nACCOUNT - ADMIN");
	cb = ((ClientBean) session.getAttribute("client"));
	if (cb == null) {
		System.out.println("{ Broswer: "+broswer+" IP: "+IP+" }\n{ NON REGISTRATO }\n");
	}
	else System.out.println("{ Broswer: "+broswer+" IP: "+IP+" }\n{ Utente: "+cb.getUtente()+" Ruolo: "+cb.getRuolo() + " }\n");

} catch (Exception e) {
	response.sendRedirect(redirectURL);
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
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>

<body class="homepage is-preload">
	<div id="page-wrapper">
		<nav id="nav">
			<ul>
				<li><a href="/home.jsp">Home</a></li>
				<li><a href="#">Account</a>
					<ul>
						<li><a href="#creaManager">Crea un manager</a></li>
						<li><a href="#listaUtenti">Gestisci utenti</a></li>
						<li><a href="/logout">Esci</a></li>
					</ul>
				</li>
				<li><a>Ciao <%=cb.getNome()%> =)</a></li>
			</ul>
		</nav>

		<div class="wrapper style1">
			<article id="dati" class="container special">	
				<header>
					<h2>
						<a>I tuoi dati</a>
					</h2>
				</header>
				<table>
				<tr><th><a href="#" class="image featured"><img src="/images/cover.jpg" alt="" /></a> </th></tr>
					<tr>
						<td><h1>Nome:</h1></td>
						<td><a><%=cb.getNome()%></a></td>
					</tr>
					<tr>
						<td><h1>Cognome:</h1></td>
						<td><a><%=cb.getCognome()%></a></td>
					</tr>
					<tr>
						<td><h1>Email:</h1></td>
						<td><a><%=cb.getEmail()%></a></td>
					</tr>
					<tr>
						<td><h1>Utente:</h1></td>
						<td><a><%=cb.getUtente()%></a></td>
					</tr>
					<tr>
						<td><h1>Ruolo:</h1></td>
						<td><a><%=cb.getRuolo()%></a></td>
					</tr>
				</table>
			</article>
		</div>
		
		<div class="wrapper style2">
			<article id="creaManager" class="container special">
				<header>
					<h2>
						<a>Crea un manager</a>
					</h2>
					<p>Da qui puoi creare un manager</p>
				</header>
				<%@include file="/jsp/registrationManager.jsp"%>
			</article>
		</div>
		
		<div class="wrapper style2">
			<article id="utenti" class="container special">
				<header>
					<h2>
						<a>Gli utenti del sito</a>
					</h2>
					<p>Da qui puoi modificarli</p>
				</header>
				<section id="listaUtenti" class="container special"></section>
			</article>
		</div>

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
	<script>
			$(document).ready(
				function() {
					$.getJSON("/GetUtentiAdmin", function(data, status) {
						var i = 0;			 
						var out = "<article><header><table class=\"utentiTable\">";
						while (data[i] != null) {
							var id = data[i].id;
							out+="<tr id=\"utente\">" 
								+"<th width=\"30%\"><a href=\"/profilo.jsp?utente="+id+"\"><img style=\"width:30%\" src=\"/images/cover.jpg\"></a></th>"
								+"<th hidden id=\"userid"+id+"\">" + id + "</th>"
								+"<th hidden id=\"nome"+id+"\">" + data[i].nome + "</th>" 
								+"<th hidden id=\"cognome"+id+"\">" + data[i].cognome + "</th>" 
								+"<th hidden id=\"email"+id+"\">" + data[i].email + "</th>" 
								+"<th id=\"utente"+id+"\">" + data[i].utente + "</th>" 
								+"<th id=\"ruolo"+id+"\">" + data[i].ruolo + "</th>" 
							 	+"<th><button id=modifica"+id+" value=" + id +" type=\"button\" class=\"ss-icon\" onClick=\"modificaUtente(this.value);\">Modifica</button>" 
							 	+"<br><span id=termina"+id+"></span></th>"
							 	+"</tr>"
								+"<tr><th><br></th></tr>";
							i++;
						}
						out+="</table></header></article>";
						$("#listaUtenti").append(out);
					});
			});
			
			function modificaUtente(id){
				if($("#completa"+id).val()!=null){
					
				}
				else {
					$("#ruolo"+id).attr("contenteditable", "true");
					$("#termina"+id).append("<button id=completa"+id+" value="+id+" type=\"button\" class=\"ss-icon\" onClick=\"terminaUtente(this.value);\">Termina</button>");
					$("#termina"+id).append("<button id=elimina"+id+" value="+id+" type=\"button\" class=\"ss-icon\" onClick=\"eliminaUtente(this.value);\">Elimina</button>");
				
				}
			}
			
			function terminaUtente(id){
				var ruolo = $("#ruolo"+id).text();
				if(ruolo=="manager" || ruolo=="standard") {
					$.get("/ModificaUtente?id="+id+"&ruolo="+ruolo, function(data, status){
						$("#termina"+id).remove();
						$("#delete"+id).remove();
						window.location.reload();
					});
				}
				else {
					alert("Si accetta solo \"standard\" o \"manager\"");
					 $("#ruolo"+id).text("");
				}
			}
			
			function eliminaUtente(id){
				$.get("/EliminaUtente?id="+id, function(data, status){
					$("#termina"+id).remove();
					$("#delete"+id).remove();
					window.location.reload();
				});
			}
	</script>

</body>
</html>