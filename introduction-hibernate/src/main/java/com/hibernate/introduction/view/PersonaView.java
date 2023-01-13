package com.hibernate.introduction.view;

import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import com.hibernate.introduction.controller.PersonaController;

public class PersonaView {

    private PersonaController controller;

    public PersonaView(PersonaController controller) {
        this.controller = controller;
    }

    public void crearPersona() {
        // solicitar datos
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido: ");
        String email = JOptionPane.showInputDialog(null, "Ingrese email: ");
        String fecha = JOptionPane.showInputDialog(null, "Ingrese Fecha nacimiento (dd/mm/aaaa): ");
        String foto = JOptionPane.showInputDialog(null, "Url foto: ");

/*         String[] dateString = fecha.split("/");
        int year = Integer.parseInt(dateString[2]);
        int month = Integer.parseInt(dateString[1]);
        int date = Integer.parseInt(dateString[0]) - 1;
        Calendar fecha_nacimiento = Calendar.getInstance();
        fecha_nacimiento.set(year, month, date); */

        Calendar fecha_nacimiento = controller.stringtoCalendar(fecha);
        // Crear persona
        boolean create = controller.crearPersona(nombre, apellido, email, fecha_nacimiento, foto);
        if (create) {
            JOptionPane.showConfirmDialog(null, "Persona creada con éxito");
        } else {
            JOptionPane.showConfirmDialog(null, "Por favor intenta más tarde");
        }
    }

    public void mostrarPersonas() {
        List<String> personas = controller.obtenerPersonas();
        String info = "-------------PERSONAS--------------\n";
        for (int i = 0; i < personas.size(); i++) {
            info += personas.get(i);
        }
        JOptionPane.showConfirmDialog(null, info);
    }

    public void buscarPersona() {

    }

    public void eliminarPersona() {

    }

    public void actualizarPersona() {
        // solicitar datos
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Por favor ingrese el identificador de la persona"));
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido: ");
        String email = JOptionPane.showInputDialog(null, "Ingrese email: ");
        String fecha = JOptionPane.showInputDialog(null, "Ingrese Fecha nacimiento (dd/mm/aaaa): ");
        String foto = JOptionPane.showInputDialog(null, "Url foto: ");


/*         String[] dateString = fecha.split("/");
        int year = Integer.parseInt(dateString[2]);
        int month = Integer.parseInt(dateString[1]);
        int date = Integer.parseInt(dateString[0]) - 1;
        Calendar fecha_nacimiento = Calendar.getInstance();
        fecha_nacimiento.set(year, month, date); */
        
        Calendar fecha_nacimiento = controller.stringtoCalendar(fecha);
        // Crear persona
        String message = controller.actualizarPersona(id,  nombre, apellido, email, fecha_nacimiento, foto);
        JOptionPane.showMessageDialog(null, message);
    }
}