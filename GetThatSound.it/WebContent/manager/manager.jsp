<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>

<%
	String redirectURL = "/home.jsp";
	ClientBean cb = null;
	String broswer = request.getHeader("user-agent");
	String IP = request.getRemoteAddr();
	try {
		System.out.println("\nACCOUNT - MANAGER");
		cb = ((ClientBean) session.getAttribute("client"));
		if (cb == null || !cb.getRuolo().equals("manager")) {
			System.out.println("infiltrato");
			response.sendRedirect(redirectURL);
		}
		else System.out.println("{ Broswer: "+broswer+"IP: "+IP+" }\n{ Utente: "+cb.getUtente()+" Ruolo: "+cb.getRuolo() + " }\n");
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
<link rel="stylesheet" href="/css/player.css" />
<link rel="stylesheet" href="/css/modal.css" />
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
							<li><a href="#listaBrani">Approva brani</a></li>
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
				<article id="change" class="container special">
					<header>
						<h2>
							<a>I brani</a>
						</h2>
						<p>Da qui puoi approvarli</p>
					</header>
					<section id="listaBrani" class="container special"></section>
				</article>
			</div>
			
			<section id="features" class="container special"
				style="background: rgba(255, 255, 255, 0.9); z-index: 999; position: fixed; bottom: 0; left: 0; right: 0">
				<%@include file="/html/player.html"%>
			</section>
	
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
	<script src="/js/player.js"></script>
	<script>
	//chiamata async alla servlet gethomebrani che restituisce in json tutti i dati dei brani disponibilie
	//ogni brano è in un indice i di data e per ogni indice != null creo la tabella, riempio coi dati e la metto in coda a "listaBrani"
	//il tasto play fa partire la funzione onclick con argomenti l'url dell audio e l'id del brano 
	//tale funzione (playSong) pesca i campi del brano di indice i e li carica nel player e fa partire il play	
	
		$(document).ready(
				function() {
					$.getJSON("/GetBraniStallo", function(data, status) {
						var i = 0;			 
						var out = "<article><header><table class=\"branoTable\">";
						while (data[i] != null) {
							var id = data[i].id;
							out+="<tr id=\"brano\">" 
								+"<th width=\"30%\"><a href=\"/profilo.jsp?utente="+data[i].user_id+"\"><img style=\"width:30%\" src=\"/images/cover.jpg\"></a></th>"
								+"<th id=\"titolo"+id+"\">" + data[i].titolo + "</th>" 
								+"<th id=\"artista"+id+"\">" + data[i].artista + "</th>" 
								+"<th id=\"album"+id+"\">" + data[i].album + "</th>" 
								+"<th><button id="+(data[i].path)+" value="+ data[i].id +" type=\"button\" class=\"ss-icon\" onClick=\"playSong(this.id, this.value);\">Play</button></th>" 
								+"<th><button value="+(data[i].id)+" id=apporova"+(data[i].id)+" type=\"button\" class=\"ss-icon\" onClick=\approva(this.value)>Approva</button></th>"
								+"<th><button value="+(data[i].id)+" id=elimina"+(data[i].id)+" type=\"button\" class=\"ss-icon\" onClick=\elimina(this.value)>Elimina</button></th>"
								+"</tr>"
								+"<tr><th><br></th></tr>";
							i++;
						}
						out+="</table></header></article>";
						$("#listaBrani").append(out);
					});
				});
				
		function approva(id){
			$.get("/ApprovaBrano?brano_id="+id, function(data, status){
				alert(data);
				window.location.reload();
			});
		}
		
		function elimina(id){
			$.get("/EliminaBranoInStallo?brano_stallo_id="+id, function(data, status){
				alert(data);
				window.location.reload();
			});
		}
		
		function playSong(path, id){
			$("#audiotrack").attr("src", "<%=request.getContextPath()%>/GetAudio?path="+path);
			$("#songName").text($("#titolo"+id).text());
			$("#inAlbum").text($("#album"+id).text());
			$("#byArtist").text($("#artista"+id).text());
			$("#playDatMusic").click();
		}

	</script>


</body>
</html>