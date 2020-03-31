document.querySelector("svg").addEventListener("click", event => {
  fetch("./controlador.html", {
    method: "POST",
    body: new FormData(document.querySelector("form"))
  });
});
