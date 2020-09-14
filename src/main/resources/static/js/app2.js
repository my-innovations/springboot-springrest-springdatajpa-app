function validate() {
	var name = document.getElementById("name").value;
	var password = document.getElementById("password").value;
	if (name == '') {
		alert('Please enter a valid name.');
		return false;
	} else if (password == '') {
		alert('Please enter a valid password.');
		return false;
	}
	else {
		return true;
	}

}