function onSignIn(googleUser) {
	// Useful data for your client-side scripts:
	var profile = googleUser.getBasicProfile();
	console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	console.log("Name: " + profile.getName());
	console.log("Image URL: " + profile.getImageUrl());
	console.log("Email: " + profile.getEmail());
	
	// The ID token you need to pass to your backend:
	var id_token = googleUser.getAuthResponse().id_token;
	console.log("ID Token: " + id_token);
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8888/testconnection');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
	  console.log('Signed in as: ' + xhr.responseText);
	  
	  var obj = JSON.parse(xhr.responseText);
	  alert(obj.email);
	  
	  var dtExpire = new Date();
	   dtExpire.setTime(dtExpire.getTime() + 3600 * 1000);
	   setCookie('token_user', id_token, dtExpire, '/' );
	   setCookie('name_user', obj.name, dtExpire, '/' );
	   setCookie('email_user', obj.email, dtExpire, '/' );
	};
	xhr.send('idtoken=' + id_token);
};

function signOut() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signOut().then(function () {
    console.log('User signed out.');
  });
}

function setCookie(nom, valeur, expire, chemin, domaine, securite){
    document.cookie = nom + ' = ' + escape(valeur) + '  ' +
              ((expire == undefined) ? '' : ('; expires = ' + expire.toGMTString())) +
              ((chemin == undefined) ? '' : ('; path = ' + chemin)) +
              ((domaine == undefined) ? '' : ('; domain = ' + domaine)) +
              ((securite == true) ? '; secure' : '');
  }
