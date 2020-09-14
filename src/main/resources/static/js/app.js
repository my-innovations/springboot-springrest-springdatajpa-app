function validate() {
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if (username == '') {
		alert('plz provide username');
		document.getElementById('username').focus();
		return false;
	}else if (password == '') {
		alert('plz provide password');
		document.getElementById('password').focus();
		return false;
	} else {
		return true;
	}
}


