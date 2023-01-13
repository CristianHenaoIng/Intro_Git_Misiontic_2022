package com.hibernate.introduction.controller;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.introduction.model.Persona;

public class PersonaController {
    //ATRIBUTOS
    private SessionFactory factory;

    // CONSTRUCTOR
    public PersonaController(){     
        // Crear objeto que permita fabricar sesiones
        factory = new Configuration().configure("cfg.xml").addAnnotatedClass(Persona.class).buildSessionFactory();
    }

    // METODO PARA ABRIR Y CERRAR SESION
    private Session crearSession (){
        Session session = factory.openSession();
        session.beginTransaction();
        return session;
    }

    // ACCIONES
    public boolean crearPersona (String nombre, String apellido, String email, java.util.Date fecha_nacimiento, String foto){
        boolean create = false;
        Session session = factory.openSession();
        session.beginTransaction();

        try {
            Persona persona = new Persona(nombre, apellido, email, fecha_nacimiento, foto);
            session.persist(persona);
            session.getTransaction().commit();
            create = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return create;
    }

    //LISTAR PERSONAS
    public List<String> obtenerPersonas(){
        List<Persona> personas = new ArrayList<>(); 
        Session session = crearSession();
        try {
            personas = session.createQuery("from Persona", Persona.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objtoString(personas);
    }

    public List<String> objtoString (List<Persona> personas){
        List<String> personasStr = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++){
            personasStr.add(personas.get(i).toString());
        }
        return personasStr;
    }
}

