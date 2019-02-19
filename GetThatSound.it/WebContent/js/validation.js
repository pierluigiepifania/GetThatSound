function RegistrationFormValidation(form)
{

	var uname = document.registration.nome;
	var surname = document.registration.cognome;
	var uemail = document.registration.email;
	var uadd = document.registration.utente;
	var passid = document.registration.pwd;
	var passid2 = document.registration.pwd2;

	if(alphanumericUser(uname))
	{
		if(alphanumericUser(surname))
		{
			if(alphanumericUser(uadd))
			{	
				if(ValidateEmail(uemail))
				{
					if(passid_validation(passid,passid2,4,15))
					{
						document.registration.submit();
					}		
				}  
			}
		}
	}
	return false;
} 

function RegistrationManagerFormValidation(form)
{

	var uname = document.registrationManager.nome;
	var surname = document.registrationManager.cognome;
	var uemail = document.registrationManager.email;
	var uadd = document.registrationManager.utente;
	var passid = document.registrationManager.pwd;
	var passid2 = document.registrationManager.pwd2;

	if(alphanumericUser(uname))
	{
		if(alphanumericUser(surname))
		{
			if(alphanumericUser(uadd))
			{	
				if(ValidateEmail(uemail))
				{
					if(passid_validation(passid,passid2,4,15))
					{
						document.registration.submit();
					}		
				}  
			}
		}
	}
	return false;
} 

function UploadFormValidation(form)
{
	var titolo = document.UploadBrano.titolo;
	var artista = document.UploadBrano.artista;
	var album = document.UploadBrano.album;

		if(alphanumeric(titolo))
		{
			if(alphanumeric(artista))
			{
				if(alphanumeric(album))
				{ 			
					document.UploadBrano.submit();
				}
			}
		}
	return false;
} 

function SearchFormValidation(form)
{
	var param = document.Search.search;
	
		if(alphanumeric(param))
		{ 			
			document.Search.submit();
		}
	return false;
} 

function LoginFormValidation(form)
{
	var usr = document.login.usr;
	var pwd = document.login.pwd;

		if(alphanumeric(usr))
		{ 	
			if(alphanumeric(pwd))
			{ 
				document.login.submit();
		
			}
		}
	return false;
} 

function passid_validation(passid,passid2,mx,my)
{
	var passid_len = passid.value.length;
	var passid2_len = passid2.value.lenght;
	if (passid_len == 0 ||passid_len >= my || passid_len < mx)
	{
		alert("La password deve contenere tra le "+mx+" e le "+my+" cifre");
		passid.focus();
		return false;
	}
	if(passid2_len == 0 ||passid2_len >= my || passid2_len < mx)
	{
		alert("La password deve contenere tra le "+mx+" e le "+my+" cifre");
		passid2.focus();
		return false;		
	}
	if(passid.value===passid2.value)
	{
		alert('Iscrizione avvenuta');
		window.location.reload();
		return true;
	}
	else
	{
		alert("Le password non corrispondono");
		return false;
	}

}
function allLetter(uname)
{ 
	var letters = /^[A-Za-z]$/;
	if(uname.value.match(letters))
	{
		return true;
	}
	else
	{
		alert('Solo caratteri (a-Z)');
		return false;
	}
}
function alphanumeric(uadd)
{ 
	var letters = /^[0-9a-zA-Z' ']{1,30}$/;
	if(uadd.value.match(letters))
	{
		return true;
	}
	else
	{
		alert('Max 30 caratteri del formato [0-9a-zA-Z\' \']');
		return false;
	}
}
function alphanumericAlone(name)
{ 
	var letters = /^[0-9a-zA-Z' ']{1,30}$/;
	if(name.match(letters))
	{
		return true;
	}
	else
	{
		alert('Max 30 caratteri del formato [0-9a-zA-Z\' \']');
		return false;
	}
}
function alphanumericUser(uadd)
{ 
	var letters = /^[0-9a-zA-Z' ']{1,30}$/;
	if(uadd.value.match(letters))
	{
		return true;
	}
	else
	{
		alert('Max 30 caratteri del formato [0-9a-zA-Z\' \']');
		uadd.focus();
		return false;
	}
}
function ValidateEmail(uemail)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})$/;
	if(uemail.value.match(mailformat))
	{
		return true;
	}
	else
	{
		alert("Indirizzo e-mail non valido");
		uemail.focus();
		return false;
	}
function smellsLikeIban(str){
		 return /^([A-Z]{2}[ \-]?[0-9]{2})(?=(?:[ \-]?[A-Z0-9]){9,30}$)((?:[ \-]?[A-Z0-9]{3,5}){2,7})([ \-]?[A-Z0-9]{1,3})?$/.test(str);
		}

function validateIbanChecksum(iban) {       
		  const ibanStripped = iban.replace(/[^A-Z0-9]+/gi,'') //keep numbers and letters only
		                           .toUpperCase(); //calculation expects upper-case
		  const m = ibanStripped.match(/^([A-Z]{2})([0-9]{2})([A-Z0-9]{9,30})$/);
		  if(!m) return false;
		  
		  const numbericed = (m[3] + m[1] + m[2]).replace(/[A-Z]/g,function(ch){
		                        //replace upper-case characters by numbers 10 to 35
		                        return (ch.charCodeAt(0)-55); 
		                    });
		  //The resulting number would be to long for javascript to handle without loosing precision.
		  //So the trick is to chop the string up in smaller parts.
		  const mod97 = numbericed.match(/\d{1,7}/g)
		                          .reduce(function(total, curr){ return Number(total + curr)%97},'');

		  return (mod97 === 1);
		};

		var arr = [
		 'DE89 3704 0044 0532 0130 00', // ok
		 'AT61 1904 3002 3457 3201', // ok
		 'FR14 2004 1010 0505 0001 3', // wrong checksum
		 'GB82-WEST-1234-5698-7654-32', // ok
		 'NL20INGB0001234567', // ok
		 'XX00 1234 5678 9012 3456 7890 1234 5678 90', // smells ok
		 'YY00123456789012345678901234567890', // smells ok
		 'NL20-ING-B0-00-12-34-567', // wrong format, but valid checksum
		 'XX22YYY1234567890123', // wrong checksum
		 'foo@i.ban' // Not even smells like IBAN
		];
		arr.forEach(function (str) {
		  console.log('['+ str +'] Smells Like IBAN:    '+ smellsLikeIban(str));
		  console.log('['+ str +'] Valid IBAN Checksum: '+ validateIbanChecksum(str))
		});
}

function ControllaPIVA(pi)
{
	if( pi == '' )  return false;
	if( ! /^[0-9]{11}$/.test(pi) ){
		alert("La partita IVA deve contenere 11 cifre.");
		return false;
	}
	var s = 0;
	for( i = 0; i <= 9; i += 2 )
		s += pi.charCodeAt(i) - '0'.charCodeAt(0);
	for(var i = 1; i <= 9; i += 2 ){
		var c = 2*( pi.charCodeAt(i) - '0'.charCodeAt(0) );
		if( c > 9 )  c = c - 9;
		s += c;
	}
	var atteso = ( 10 - s%10 )%10;
	if( atteso != pi.charCodeAt(10) - '0'.charCodeAt(0) ){
		alert("La partita IVA non è valida:\n il codice di controllo non corrisponde.\n");
		return false;
	}
	return true;
}