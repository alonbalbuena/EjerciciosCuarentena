fetch("http://localhost:8080/proveedores")
  .then(respuesta => respuesta.json())
  .then(json => console.log(json));