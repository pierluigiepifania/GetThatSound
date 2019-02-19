<form name="login" action="/login" method="post" onsubmit="return LoginFormValidation();">
	<div class="imgcontainer" style="text-align:center;">
		<!-- <img src="img_avatar2.png" alt="Avatar" class="avatar">  -->
	</div>

	<div class="container">
		<label for="usr" ><b>Utente</b></label>
		<input type="text"
			placeholder="Enter Username" name="usr" required> 
			
		
			
		<label for="pwd"><b>Password</b></label>
		<input type="password"
			placeholder="Enter Password" name="pwdLogin" required>

		

		<button id="submitLogin" type="submit">Login</button>
	</div>

	<div class="container" style="background-color: #f1f1f1">
		<label class="psw">Non sei ancora dei nostri? <a href="/#features" target="_self">Iscriviti
				ora!</a></label>
	</div>
</form>