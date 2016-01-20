function onSignIn(googleUser) {
	if(getCookie('token_user') == null){
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
		xhr.open('POST', window.location.origin + '/testconnection');
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onload = function() {
		  console.log('Signed in as: ' + xhr.responseText);
		  
		  var obj = JSON.parse(xhr.responseText);
		  
		  var dtExpire = new Date();
		   dtExpire.setTime(dtExpire.getTime() + 3600 * 1000);
		   setCookie('token_user', id_token, dtExpire, '/' );
		   setCookie('name_user', obj.name, dtExpire, '/' );
		   setCookie('email_user', obj.email, dtExpire, '/' );
		   window.location.reload();
		};
		xhr.send('idtoken=' + id_token);
	}
};

function signOut() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signOut().then(function () {
    console.log('User signed out.');
    delete_cookie('token_user');
    delete_cookie('name_user');
    delete_cookie('email_user');
    window.location.reload();
  });
  
}

function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
        var end = document.cookie.indexOf(";", begin);
        if (end == -1) {
        end = dc.length;
        }
    }
    return unescape(dc.substring(begin + prefix.length, end));
} 


function setCookie(nom, valeur, expire, chemin, domaine, securite){
    document.cookie = nom + ' = ' + escape(valeur) + '  ' +
              ((expire == undefined) ? '' : ('; expires = ' + expire.toGMTString())) +
              ((chemin == undefined) ? '' : ('; path = ' + chemin)) +
              ((domaine == undefined) ? '' : ('; domain = ' + domaine)) +
              ((securite == true) ? '; secure' : '');
  }

var delete_cookie = function(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};
