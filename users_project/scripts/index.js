// vamos a tener un metodo -funcion que va a hacer la p eticion a la api rest, luego se iterara la respuesta
const url = "http://localhost:8080/personas" 
// La recomendacion es tener un archivo js que contenga todos los endpoints de nuestros sevidores
// y lo llamamos desde cualquier archivo que se cree

async function get_users(url){
    // Enviar peticion
    const resp = await fetch (url, {
        method: 'GET'
    })
    const users = await resp.json();
    return users;
}

function show_users (users){
    // Referenciar tabla
    const table = document.getElementById("table") // conn el id que tiene en el html la variable si puede ser llamada diferente
    let tbody = "<tbody>"
    //Iterar useres
    for (let i = 0; i < users.length; i++){
        const arrayDate  = users[i].fecha_nacimiento.split("T")
        const obj = JSON.stringify(users[i])
        tbody += `
            <tr>
                <td> 
                    <img src="${users[i].foto}" />
                </td>
                <td>${users[i].nombre}</td>
                <td>${users[i].apellido}</td>
                <td>${users[i].email}</td>
                <td>${arrayDate[0]}</td>
                <td>
                    <button class="btn btn-warning" onclick = 'update (${obj})'>Actualizar</button>
                    <button class="btn btn-danger">Eliminar</button>
                </td>
            </tr>
        `
    }
    tbody += "</tbody>"
    table.innerHTML += tbody
}

async function update(persona){
    window.location.href = `form.html?persona=${JSON.stringify(persona)}` //permite referenciar ell navegador  la pestaña en la que estamos ubicados
}

async function main(){

    const users = await get_users (url)
    show_users(users)
}

main()