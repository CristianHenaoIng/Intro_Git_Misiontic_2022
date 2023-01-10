/* 
*javascript es similar a java solo que mas simplificado, se parece a python
*es flexible como python
*/

//constantes
const numero = 5;
// enjs es opcional manejar el punto y coma
let mensaje = "Hola mundo";
mensaje = "Hola mundo desde mision tic"

var suma = 5 + 10
let arreglo = [5, 10, 20, 40, 50]

for (let i = 0; < arreglo.length; i++) {
    let e = arreglo[i]
    // Imprimir en consola
    console.log(e)
}

let n1 = 10
let n2 = 5
let suma = n1 + n2
console.log("Suma = " + suma)

const arreglo = [5, 10, 15, 20]
for (let i = 0; i < arreglo.length; i++) {
    const num = arreglo[i]
    console.log(num)
}

let persona = {
    nombre: "Andrea",
    apellido: "Medina",
    edad: 28,
    telefono: "3205237619"
}

console.log(persona)
//Acceder a valores del objeto
let nombre = persona.nombre
console.log("nombre: " + nombre)
let apellido = persona["apellido"]
console.log("apellido: " + apellido)

//funciones
function saludar() {
    console.log("Hola mundo desde una función")
}

function mostrar_info(persona) {
    //obtener los valores del objeto
    let values = Object.values(persona)
    console.log(values)
    for (let i = 0; i < values.length; i++) {
        console.log(values[i])
    }
}



mostrar_info(persona)
saludar()

function multiplicar(n1, n2) {
    return n1 * n2
}

let result = multiplicar(5, 10)
console.log("Resultado: " + result)