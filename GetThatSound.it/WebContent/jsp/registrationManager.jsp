<form name="registrationManager" action="/CreaManager" method="post" onsubmit="return RegistrationManagerFormValidation();">
	<div class="imgcontainer">
		<!-- <img src="img_avatar2.png" alt="Avatar" class="avatar">  -->
	</div>

	<div class="container" style="text-align: -webkit-left; text-align: -moz-left;">
		<label for="nome"><b>Nome</b></label> 
			<input type="text" placeholder="Enter nome" name="nome" required> 
		<label for="cognome"><b>Cognome</b></label> 
			<input type="text" placeholder="Enter cognome" name="cognome" required> 
		<label for="email"><b>Email</b></label> 
			<input type="text" placeholder="Enter Email" name="email" onChange="checkEmail();" required> 
		<label for="utente"><b>Utente</b></label> 
			<input type="text" placeholder="Enter Username" name="utente"  onChange="checkUsr();" required> 
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

		<button id="confermagestore" type="submit">Conferma</button>
	</div>
</form>
