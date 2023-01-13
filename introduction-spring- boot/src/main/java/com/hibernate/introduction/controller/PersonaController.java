package com.hibernate.introduction.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.introduction.model.Persona;

@RestController
@RequestMapping("/personas")

public class PersonaController {
    //ATRIBUTOS
    private SessionFactory factory;

    // CONSTRUCTOR
    public PersonaController(){     
        // Crear objeto que permita fabricar sesiones
        factory = new Configuration().configure("cfg.xml").addAnnotatedClass(Persona.class).buildSessionFactory();
    }

    /* @GetMapping
    public String holaMundo(){
        return "Hola mundo utilizando Spring Boot";
    } */

    // METODO PARA ABRIR Y CERRAR SESION
    private Session crearSession (){
        Session session = factory.openSession();
        session.beginTransaction();
        return session;
    }

    // ACCIONES
    //LISTAR PERSONAS
    @GetMapping
    public List<Persona> obtenerPersonas(){
        List<Persona> personas = new ArrayList<>(); 
        Session session = crearSession();
        try {
            personas = session.createQuery("from Persona", Persona.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personas;
    }

    @PostMapping
    //public String crearPersona (@RequestBody String nombre, String apellido, String email, Calendar fecha_nacimiento, String foto){
    public String crearPersona (@RequestBody Persona persona){
        String message = "";

        Session session = crearSession();

        try {
            //Persona persona = new Persona(nombre, apellido, email, fecha_nacimiento, foto);
            session.persist(persona);
            session.getTransaction().commit();
            message= "Persona creada con éxito";
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    @PutMapping
    // ACTUALIZAR PERSONA
    // Necesitamos capturar  los datos de la persona
    //public String actualizarPersona(@RequestParam int id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam Calendar fecha_naci, @RequestParam String foto){
    public String actualizarPersona(@RequestBody Persona persona){
        Session session = crearSession();
        String message = "";
        try {
            /* Persona persona = session.find(Persona.class, id);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setEmail(email);
            persona.setFecha_nacimiento(fecha_naci);
            persona.setFoto(foto); */

            session.merge(persona);
            session.getTransaction().commit();
            session.close();
            message = "Persona actualizada con éxito";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    public String eliminarPersona (@RequestBody Persona persona){
        String message = "";
        Session session = crearSession();
        try {
            session.remove(persona);
            session.getTransaction().commit();
            session.close();
            message = "Persona eliminada con éxito";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }



    //OBTENER PERSONAS POR ID
    public String obtenerPersonaXId (int id){
        String personaStr = "";
        Session session = crearSession();

        try {
            Persona persona = session.find(Persona.class, id);
            personaStr = persona.toString();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personaStr;
    }
    
    public List<String> objtoString (List<Persona> personas){
        List<String> personasStr = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++){
            personasStr.add(personas.get(i).toString());
        }
        return personasStr;
    }

    public Calendar stringtoCalendar(String fecha){
        String[] dateString = fecha.split("/");
        int year = Integer.parseInt(dateString[2]);
        int month = Integer.parseInt(dateString[1]) - 1;
        int date = Integer.parseInt(dateString[0]);
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.set(year, month, date);
        return fechaCalendar;
    }

}

