$(document).ready(function () {
});

async function resgisterUser() {
    let datos = {};
    const request = await fetch("http://localhost:8080/api/usuarios", {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(datos)
    })
    const data = await request.json();

}