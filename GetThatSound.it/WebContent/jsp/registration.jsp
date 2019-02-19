<form name="registration" action="/Registration" method="post" onsubmit="return RegistrationFormValidation();">
	<div class="imgcontainer">
		<!-- <img src="img_avatar2.png" alt="Avatar" class="avatar">  -->
	</div>

	<div class="container" style="text-align: -webkit-center; text-align: -moz-center;">
		<label for="nome"><b>Nome</b></label> 
			<input type="text" placeholder="Enter nome" name="nome" required> 
		<label for="cognome"><b>Cognome</b></label> 
			<input type="text" placeholder="Enter cognome" name="cognome" required> 
		<label for="email"><b>Email</b></label> 
			<input type="text" id="email" placeholder="Enter Email" name="email" onChange="checkEmail();" required> 
		<label for="utente"><b>Utente</b></label> 
			<input type="text" id="utente" placeholder="Enter Username" name="utente"  onChange="checkUsr();" required> 
		<label for="pwd"><b>Password</b></label> 
			<input type="password" id="pwd" placeholder="Enter Password" name="pwd" required>
		<label for="pwd2"><b>Ripeti la Password</b></label> 
			<input type="password" id="pwd2" onChange="checkPasswordMatch();" placeholder="Repeat the Password" name="pwd2"  required>
		<input type="hidden" value="manager" name="ruolo" required>

<script type="text/javascript">
function checkPasswordMatch() {
    var password = $("#pwd").val();
    var confirmPassword = $("#pwd2").val();
    if (password != confirmPassword){
        alert("Le password non corrispondono");
    	$("#pwd").val("");
    	$("#pwd2").val("");
    }
}
function checkUsr() {
   var usr = $("#utente").val();
   $.getJSON("\CheckUserMatch?utente="+usr, function(data, status){
	   if(data.esito==1){
		   alert("Utente già esistente");
		   $("#utente").val("");
	   }
   });   
}
function checkEmail() {
   var email = $("#email").val();
   $.getJSON("\CheckEmailMatch?email="+email, function(data, status){
 		if(data.esito==1){
 		   alert("Email già esistente");
 		   $("#email").val("");
	   }
   });
}
</script>

		<button type="submit">Conferma</button>
	</div>
</form>

<a href="/home.jsp">Continua come ospite</a>

<div class="container" style="background-color: #f1f1f1; text-align: -webkit-center; text-align: -moz-center;">
		<label class="psw">Sei già iscritto? Effettua il
			<button id="myBtn">Login</button> 
		</label>
</div>

<!-- The Modal -->
				<div id="myModal" class="modal">

					<!-- Modal content -->
					<div class="modal-content">
						<span class="close">&times;</span>
						<%@include file="/jsp/login.jsp"%>
					</div>

				</div>