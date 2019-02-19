<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"
	import="it.model.*"%>
	
<%
	String redirectURL = "/home.jsp";
	ClientBean cb = null;
	String broswer = request.getHeader("user-agent");
	String IP = request.getRemoteAddr();
	try {
		System.out.println("\nACCOUNT - STANDARD");
		cb = ((ClientBean) session.getAttribute("client"));
		if (cb == null) {
			System.out.println("{ Broswer: "+broswer+" IP: "+IP+" }\n{ NON REGISTRATO }\n");
			response.sendRedirect(redirectURL);
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
					<li><a href="/home.jsp">Home</a></li>
					<li><a href="#">Account</a>
						<ul>
							<li><a href="#upload">Carica</a></li>
							<li><a href="#listaBrani">I tuoi brani</a></li>
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
			<article id="upload" class="container special">
				<!-- <a href="#" class="image featured"><img src="images/pic06.jpg" alt="" /></a> -->
				<header>
					<h2>
						<a>Upload</a>
					</h2>
					<p>Carica il tuo brano</p>
				</header>
				<form name="UploadBrano" action="UploadBrano" method="post" onsubmit="return UploadFormValidation();" enctype="multipart/form-data">
					<label for="titolo"><b>Titolo</b></label>
					<input type="text" placeholder="Inserisci il titolo del brano" name="titolo" size="30" required>
					
					<label for="artista"><b>Artista</b></label>
					<input type="text" placeholder="Inserisci l'artista del brano" name="artista" id="artista" size="30" required>
					
					<label for="album"><b>Album</b></label>
					<input type="text" placeholder="Inserisci l'album del brano" name="album" id="album" size="30" required>
					
					<input type="hidden" value=<%=cb.getId()%> name="usr" required>
					
					<input id="file" type="file" name="file" accept=".mp3" required>
					<footer>
						<input type="submit" class="button" id="addSong" value="Upload" />
					</footer>
				</form>
			</article>
		</div>
		
		<div class="wrapper style2">
			<article id="change" class="container special">
				<header>
					<h2>
						<a>I tuoi brani</a>
					</h2>
					<p>Da qui puoi modificarli</p>
				</header>
				<section id="listaBrani" class="container special"></section>
				<section id="listaBraniAttesa" class="container special"></section>
			</article>
		</div>
		
		<div class="wrapper style2">
			<article id="saved" class="container special">
				<header>
					<h2>
						<a>I tuoi brani preferiti</a>
					</h2>
					<p>Ascoltali o eliminali :)</p>
				</header>
				<section id="listaBraniSalvati" class="container special"></section>
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
	
	//chiamata async alla servlet gethomebrani che restituisce in json tutti i dati dei brani disponibilie
	//ogni brano è in un indice i di data e per ogni indice != null creo la tabella, riempio coi dati e la metto in coda a "listaBrani"
	//il tasto play fa partire la funzione onclick con argomenti l'url dell audio e l'id del brano 
	//tale funzione (playSong) pesca i campi del brano di indice i e li carica nel player e fa partire il play	
	
				$(document).ready(
					function() {
						$.getJSON("/GetBraniUtente?utente=0", function(data, status) {
							var i = 0;			 
							var out = "<article><header><table class=\"branoTable\">";
							while (data[i] != null) {
								var id = data[i].id;
								out+="<tr id=\"brano\">" 
									+"<th width=\"30%\"><a href=\"/profilo.jsp?utente="+data[i].user_id+"\"><img style=\"width:30%\" src=\"/images/cover.jpg\"></a></th>"
									+"<th hidden id=\"numero"+id+"\">" + data[i].numero + "</th>"
									+"<th id=\"titolo"+id+"\">" + data[i].titolo + "</th>" 
									+"<th id=\"artista"+id+"\">" + data[i].artista + "</th>" 
									+"<th id=\"album"+id+"\">" + data[i].album + "</th>" 
									+"<th hidden id=\"user"+id+"\">" + data[i].user_id + "</th>"
									+"<th><button id="+(data[i].path)+" value="+ id +" type=\"button\" class=\"ss-icon\" onClick=\"playSong(this.id, this.value);\">Play</button></th>" 
								 	+"<th><button id=modifica"+id+" value=" + id +" type=\"button\" class=\"ss-icon\" onClick=\"modificaSong(this.value);\">Modifica</button>" 
								 	+"<br><span id=termina"+id+"></span></th>"
								 	+"</tr>"
									+"<tr><th><br></th></tr>";
								i++;
							}
							out+="</table></header></article>";
							$("#listaBrani").append(out);
						});
						
						$.getJSON("/GetBraniSalvati", function(data, status) {
							var i = 0;			 
							var out = "<article><header><table class=\"branoTable\">";
							while (data[i] != null) {
								var id = data[i].id;
								out+="<tr id=\"brano\">" 
									+"<th width=\"30%\"><a href=\"/profilo.jsp?utente="+data[i].user_id+"\"><img style=\"width:30%\" src=\"/images/cover.jpg\"></a></th>"
									+"<th id=\"titolo"+id+"\">" + data[i].titolo + "</th>" 
									+"<th id=\"artista"+id+"\">" + data[i].artista + "</th>" 
									+"<th id=\"album"+id+"\">" + data[i].album + "</th>" 
									+"<th hidden id=\"user"+id+"\">" + data[i].user_id + "</th>"
									+"<th><button id="+(data[i].path)+" value="+ id +" type=\"button\" class=\"ss-icon\" onClick=\"playSong(this.id, this.value);\">Play</button></th>" 
								 	+"<th><button id=elimina"+id+" value=" + id +" type=\"button\" class=\"ss-icon\" onClick=\"eliminaSalvato(this.value);\">Rimuovi</button>" 
									+"<br><span id=termina"+id+"></span></th>"
								 	+"</tr>"
									+"<tr><th><br></th></tr>";
								i++;
							}
							out+="</table></header></article>";
							$("#listaBraniSalvati").append(out);
						});
				});
	
				function eliminaSalvato(id){
					$.get("/RimuoviBranoSalvato?brano_id="+id, function(data, status){
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
				
				function modificaSong(id){
					if($("#completa"+id).val()!=null){
						
					}
					else {
						$("#titolo"+id).attr("contenteditable", "true");
						$("#artista"+id).attr("contenteditable", "true");
						$("#album"+id).attr("contenteditable", "true");
						$("#termina"+id).append("<button id=completa"+id+" value="+id+" type=\"button\" class=\"ss-icon\" onClick=\"terminaSong(this.value);\">Termina</button>");
					}
				}
				
				function terminaSong(id){
					var song_id = id;
					var titolo = $("#titolo"+id).text();
					var artista = $("#artista"+id).text();
					var album = $("#album"+id).text();
					var user_id = $("#user"+id).text();
					if(alphanumericAlone(titolo) && alphanumericAlone(album) && alphanumericAlone(artista)){
					$.get("/ModificaBrano?user_id="+user_id+"&song_id="+song_id+"&titolo="+titolo+"&artista="+artista+"&album="+album, function(data, status){
							$("#termina"+id).remove();
							window.location.reload();
						});
					}
					else {
						window.location.reload();
					}
				}
	</script>

</body>
</html>