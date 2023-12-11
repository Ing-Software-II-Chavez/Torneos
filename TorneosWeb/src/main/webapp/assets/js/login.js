window.onload = function() {
	if (window.performance && window.performance.navigation.type === window.performance.navigation.TYPE_BACK_FORWARD) {
		window.location.href = "index.jsp";
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








