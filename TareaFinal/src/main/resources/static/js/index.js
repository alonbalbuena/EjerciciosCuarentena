document.querySelector("svg").addEventListener("click", event => {
	//envia el formulario
	document.querySelector("form").submit();
});

document.querySelector("button").addEventListener("click", event => {
	//envia al registro
	window.location.href = "/registro";
});
