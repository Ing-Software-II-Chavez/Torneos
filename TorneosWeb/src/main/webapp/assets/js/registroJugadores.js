var fechaActual = new Date().toISOString().split('T')[0];
document.getElementById('fechanacimiento').setAttribute('max', fechaActual);
    
function validarContraseña() {
	var contraseña = document.getElementById('password').value;
	var contraseñaValida = true;
	var mensajeError = '';    
	
	if (contraseña.length < 6) {
		contraseñaValida = false;
		mensajeError += 'La contraseña debe tener al menos 6 caracteres.<br>';
	}

	if (!/[a-z]/.test(contraseña)) {
		contraseñaValida = false;
		mensajeError += 'La contraseña debe contener al menos una letra minúscula.<br>';
	}

	if (!/[A-Z]/.test(contraseña)) {
		contraseñaValida = false;
		mensajeError += 'La contraseña debe contener al menos una letra mayúscula.<br>';
	}

	if (!/\d/.test(contraseña)) {
		contraseñaValida = false;
		mensajeError += 'La contraseña debe contener al menos un número.<br>';
	}

	if (!/[!@#\$%^&*()_+{}\[\]:;<>,.?~\\]/.test(contraseña)) {
		contraseñaValida = false;
		mensajeError += 'La contraseña debe contener al menos un carácter especial.<br>';
	}

	if (!contraseñaValida) {
		document.getElementById('passwordError').innerHTML = mensajeError;
		return false;
	}
	return true;
}
document.querySelector('form').onsubmit = validarContraseña;