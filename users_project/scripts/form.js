// Contiene una funcion que se llama desde el submit del form cuando demos click y todo este validado
// en el envio del formlario se llama la funcion se genera un evento para capturar los valores del formulario

const URL_API = "http://localhost:8080/personas"

function get_data_form(evt){
    // Indicar al evento que no recargue la pagina
    evt.preventDefault ()
    // Captura de todos los datos, pero como enviamos al sevidor en formato json las peticiones que hacemos desde postman
    // para enviar los datos con post en el cuerpo de la peticion armabamos objeto json
    // armamos objeto de una vez y le vamos asignando los valores
    let persona = {
        nombre: evt.target.nombre.value,
        apellido: evt.target.apellido.value,
        email: evt.target.email.value,
        fecha_nacimiento: evt.target.fecha_nacimiento.value,
        foto: evt.target.foto.value
    }
    console.table(persona)
    send_data(persona)
    clear_input(evt.target)
    //console.log("Hola mundo desde un submit -> ", evt)
}

function clear_input(form){
    form.nombre.value = ""
    form.apellido.value = ""
    form.email.value = ""
    form.fecha_nacimiento.value = ""
    form.foto.value = ""
}

async function send_data(persona){
    // Enviar peticion
    const resp = await fetch(URL_API, {
        method: 'POST',
        headers: { //se agrega la cabecera de la peticion
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(persona) //convierte un objeto js a json string
    })
    const text = await resp.text()
    alert(text)
}

async function update(persona){
    console.table(persona)
    persona.nombre = "Nombre aactualizado"
    persona.apellido = "Apellido actualizado"
    console.table(persona)
    const resp = await fetch(url, {
        method: 'PUT',   
        headers: { //se agrega la cabecera de la peticion
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(persona)
    })
    const text = await resp.text()
    alert (text)
}
