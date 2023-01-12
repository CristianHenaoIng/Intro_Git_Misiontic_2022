package com.hibernate.introduction.controller;

import java.sql.Date;

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
}

