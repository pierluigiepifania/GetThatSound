<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>

<%
	String redirectURL = "/index.jsp";
	ClientBean cb = null;
	String broswer = request.getHeader("user-agent");
	String IP = request.getRemoteAddr();
	try {
		System.out.println("\nHOME");
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
<script src="/js/validation.js"></script>
</head>

<body class="homepage is-preload">
	<div id="page-wrapper">
		<nav id="nav">
			<ul>
				<li><a href="home.jsp">Home</a></li>
				<%
					if (cb != null) {
						if(cb.getRuolo().equals("standard")){
				%>
				<li><a href="/standard/account.jsp">Account</a>
					<ul>
						<li><a href="/standard/account.jsp#upload">Carica</a></li>
						<li><a href="/standard/account.jsp#listaBrani">I tuoi brani</a></li>
						<li><a href="logout">Esci</a></li>
					</ul>
				</li>
				<li><a>Ciao <%=cb.getNome()%> =)</a></li>
				<%
						}else if(cb.getRuolo().equals("manager")){
				%>
				<li><a href="/manager/manager.jsp">Account</a>
					<ul>
						<li><a href="/manager/manager.jsp#listaBrani">Approva brani</a></li>
						<li><a href="logout">Esci</a></li>
					</ul>
				</li>
				<li><a>Ciao <%=cb.getNome()%> =)</a></li>
				<%
						}else if(cb.getRuolo().equals("admin")){
				%>
				<li><a href="/admin/admin.jsp">Account</a>
					<ul>
						<li><a href="#">Dashboard</a></li>
						<li><a href="logout">Esci</a></li>
					</ul>
				</li>
				<li><a>Ciao <%=cb.getNome()%> =)</a></li>
				<%
						}
					} else {
				%>
				<li><a>Ciao ospite =) </a></li>
				<li><button id="myBtn">Login</button> 
				<!-- The Modal -->
					<div id="myModal" class="modal">
						<!-- Modal content -->
						<div class="modal-content">
							<span class="close">&times;</span>
							<%@include file="/jsp/login.jsp"%>
						</div>
					</div> <%
 					}
				 %>
			</ul>
		</nav>

		<div class="wrapper style1">
			<article id="main" class="container special">
				<header>
					<h2>
						<a href="#">GetThatSound</a>
					</h2>
					<h6>
						<form name="Search" action="/result.jsp" style="display:-webkit-inline-flex" onsubmit="return SearchFormValidation();">
							<input style="-webkit-appearance:listitem; max-width:10em; display:-webkit-inline-flex; max-block-size: 3em;margin-top: 1em;margin-bottom: 1em" type="text" placeholder="Cerca un brano" name="search" size="30" required></input>
							<button type="submit" style="margin-top: 1em;margin-bottom: 1em"><i class="fa fa-search"></i></button>		
						</form>
					</h6>
				</header>
			</article>
	
			<section id="listaBrani" class="container special"></section>

			<article class="container special">
				<footer>
					<a href="#page-wrapper" class="button">Top</a>
				</footer>
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
	<script src="/js/modal.js"></script>
	<script src="/js/player.js"></script>
	<script>
	//chiamata async alla servlet gethomebrani che restituisce in json tutti i dati dei brani disponibilie
	//ogni brano è in un indice i di data e per ogni indice != null creo la tabella, riempio coi dati e la metto in coda a "listaBrani"
	//il tasto play fa partire la funzione onclick con argomenti l'url dell audio e l'id del brano 
	//tale funzione (playSong) pesca i campi del brano di indice i e li carica nel player e fa partire il play	
	function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	var param = getUrlParameter('search');
		$(document).ready(
				function() {
					$.getJSON("/Search?search="+param, function(data, status) {
						if(data.result=="null") {
						alert("Non è stato trovato nessun brano");
					}
					else {
						var i = 0;			 
						var out = "<article><header><table class=\"branoTable\">";
						while (data[i] != null) {
							var id = data[i].id;
							out+="<tr id=\"brano\">" 
								+"<th><a href=\"/profilo.jsp?utente="+id+"\"><img style=\"width:100%\" src=\"/images/cover.jpg\"></a></th>"
								+"<th id=\"titolo"+id+"\">" + data[i].titolo + "</th>" 
								+"<th id=\"album"+id+"\">" + data[i].album + "</th>" 
								+"<th id=\"artista"+id+"\">" + data[i].artista + "</th>" 
								+"<th><button id="+(data[i].path)+" value="+ id +" type=\"button\" class=\"ss-icon\" onClick=\"playSong(this.id, this.value);\">Play</button></th>" 
								+"<th id=\"save\"><button id="+ id +" class=\"ss-icon\" onClick=\"saveSong(this.id)\"><i class=\"fa fa-heart\"></i></button></tr></th>"
								+"<tr><th><br></th></tr>";
							i++;
						}
						out+="</table></header></article>";
						$("#listaBrani").append(out);
						}
					});
				});
	
				function saveSong(id){
					$.get("SalvaBrano?brano_id="+id, function(data, status){
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