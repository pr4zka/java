// Call the dataTables jQuery plugin
$(document).ready(function () {
    cargarUsuarios();
    $('#users').DataTable();
});

async function cargarUsuarios() {
    const request = await fetch("http://localhost:8080/api/usuarios", {
        method: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
    const data = await request.json();
    let listadoHtml = '';

    for (let usuario of data) {
        let botonEliminar = `<a href=\"#\" onclick="${deleteUser(`${usuario.id}`)}" class=\"btn btn-danger btn-circle btn-sm\"><i class=\"fas fa-trash\"></i></a>`
        let usuariosHtml = '<tr> <td>' + usuario.name + '</td>\<td>' + usuario.lastname + '</td><td>' + usuario.email + '</td>\<td>' + usuario.telefono + '</td><td>' + botonEliminar + '</td></tr>'
        listadoHtml += usuariosHtml;
    }

    async function deleteUser(id) {
        if (confirm("Desea eliminar el usuaruio?")) {
            return;
        }

        const request = await fetch("http://localhost:8080/api/usuarios/" + id, {
            method: 'DELETE',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        })
    }

    document.querySelector('#users tbody').outerHTML = listadoHtml;
}
