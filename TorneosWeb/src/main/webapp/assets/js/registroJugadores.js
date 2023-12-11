var fechaActual = new Date().toISOString().split('T')[0];
document.getElementById('fechanacimiento').setAttribute('max', fechaActual);
    
function validarContraseña() {
	var contraseña = document.getElementById('password').value;
	var contraseñaValida = true;
	var mensajeError = '';    
	validar_numero()
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

function validar_numero() {
	const input_num = document.getElementById('numerocuenta');
	const numero = input_num.value.trim();
	
	const regex = /^[0-9]+$/;
	
	if (!regex.test(numero) && numero.length != 7) {
        alert('Ingrese un número de cuenta válido de 7 dígitos.');
    }

}

window.onload = function() {
	if (window.performance && window.performance.navigation.type === window.performance.navigation.TYPE_BACK_FORWARD) {
		window.location.href = "login.jsp";
	}
}














const errorPopup = document.getElementById('errorPopup');

function mostrarPopup() {
    errorPopup.style.display = 'block'; 
}

function cerrarPopup() {
    errorPopup.style.display = 'none'; 
}

window.onclick = function(event) {
    if (event.target === errorPopup) {
        errorPopup.style.display = 'none';
    }
}








