// vamos a tener un metodo -funcion que va a hacer la p eticion a la api rest, luego se iterara la respuesta

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
        tbody += `
            <tr>
                <td> 
                    <img src="${users[i].foto}" />
                </td>
                <td>${users[i].nombre}</td>
                <td>${users[i].apellido}</td>
                <td>${users[i].email}</td>
                <td>${arrayDate[0]}</td>
            </tr>
        `
    }
    tbody += "</tbody>"
    table.innerHTML += tbody
}

async function main(){
    const url = "http://localhost:8080/personas"
    const users = await get_users (url)
    show_users(users)
}

main()